# Spring Boot 高併發秒殺系統 (High-Concurrency Seckill System)

## 📖 專案概述 (Project Overview)

本專案旨在實作一個高效能的電子商務秒殺系統。採用漸進式架構設計 (Incremental Architecture)，從基礎的單體 MVP 逐步演進至具備快取機制與訊息隊列的高併發微服務架構。專案重點在於解決「超賣 (Overselling)」、「高並發讀寫 (High Concurrency I/O)」以及「流量削峰 (Traffic Peak Shaving)」等分散式系統核心問題。

## 🛠 技術堆疊 (Tech Stack)

  * **Backend:** Java, Spring Boot
  * **Database:** MySQL
  * **Cache:** Redis
  * **Message Queue:** RabbitMQ (or Kafka)
  * **Testing:** JMeter (Stress Testing)

-----

## 🚀 架構演進路線圖 (Implementation Roadmap)

### 階段一：MVP 最小可行性產品 (Phase 1: Core Business Logic)

**目標**：建立基礎業務流程，確保功能正確性。
**架構描述**：
基於 Spring Boot 的標準 MVC 架構，直接與 MySQL 資料庫交互。

  * **實作功能**：
      * 商品列表展示。
      * 基礎下單流程：查詢庫存 -\> 扣減庫存 -\> 建立訂單。
  * **已知限制**：
      * 在高併發場景（JMeter 壓測）下，因缺乏鎖機制，將導致 Race Condition，產生「超賣」現象（庫存呈現負數）。
      * 資料庫直接承受所有流量，存在單點故障風險。

### 階段二：並發控制與效能優化 (Phase 2: Concurrency & Caching Strategy)

**目標**：解決超賣問題，提升系統吞吐量 (QPS)。
**核心優化技術**：

1.  **Redis 緩存預熱 (Cache Pre-heating)**：
      * 系統啟動時，將商品庫存預先載入 Redis。
      * **Redis 預減庫存**：利用 Redis 的原子操作 `DECR` 進行庫存扣減。若回傳值 `< 0`，則視為秒殺失敗，直接攔截請求，大幅減少 DB 訪問。
2.  **資料庫樂觀鎖 (Database Optimistic Locking)**：
      * 捨棄效能較差的 `SELECT ... FOR UPDATE` (悲觀鎖)。
      * 利用 SQL 更新條件確保原子性與數據一致性，防止負數庫存寫入：
        ```sql
        UPDATE goods SET stock = stock - 1 WHERE id = ? AND stock > 0;
        ```

### 階段三：非同步處理與流量削峰 (Phase 3: Asynchronous Processing)

**目標**：保護後端服務與資料庫，防止瞬間流量擊穿系統。
**架構描述**：
引入 Message Queue 進行生產者-消費者模式 (Producer-Consumer Pattern) 設計。

  * **流量削峰 (Peak Shaving)**：
      * **生產者 (Web 層)**：當 Redis 預減庫存成功後，將秒殺請求封裝為訊息發送至 MQ，並立即回傳 `排隊中 (Queuing)` 狀態給前端，釋放連線。
      * **消費者 (Service 層)**：監聽 MQ，依據系統處理能力，平滑地從隊列中取出請求，執行真正的 DB 下單操作。
  * **使用者體驗優化**：
      * 前端實作 **輪詢機制 (Polling)**，定時向後端查詢訂單處理結果。

-----

## 💾 資料庫設計 (Database Schema)

### 1\. 用戶表 (`users`)

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | BIGINT | Primary Key |
| `username` | VARCHAR | 用戶名 |
| `password` | VARCHAR | 加密後的密碼 |

### 2\. 商品表 (`goods`)

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | BIGINT | Primary Key |
| `goods_name` | VARCHAR | 商品名稱 |
| `stock_count` | INT | **庫存數量** (核心欄位) |
| `price` | DECIMAL | 商品價格 |

### 3\. 訂單表 (`orders`)

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | BIGINT | Primary Key |
| `user_id` | BIGINT | 用戶 ID |
| `goods_id` | BIGINT | 商品 ID |
| `status` | INT | 訂單狀態 (0:新建, 1:已支付, 2:已發貨) |

### 4\. 秒殺訂單表 (`seckill_orders`)

*用於防止重複搶單的唯一性約束表。*

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | BIGINT | Primary Key |
| `user_id` | BIGINT | 用戶 ID |
| `goods_id` | BIGINT | 商品 ID |
| `order_id` | BIGINT | 關聯的原始訂單 ID |

> **Constraint**: Unique Index on (`user_id`, `goods_id`) to prevent duplicate orders from the same user.

-----

## 🔌 核心 API 規格 (Core API Specification)

### 1\. 獲取商品列表

  * **Endpoint**: `GET /list`
  * **Description**: 回傳所有參與秒殺的商品資訊與剩餘庫存。

### 2\. 執行秒殺 (Do Seckill)

  * **Endpoint**: `POST /do_seckill`
  * **Params**: `goodsId`
  * **Logic**:
    1.  Check Redis pre-decremented stock.
    2.  Check for duplicate orders (Redis Set or DB Unique Index).
    3.  Queue request to RabbitMQ.
    4.  Return "Queuing" status immediately.

### 3\. 查詢秒殺結果 (Polling)

  * **Endpoint**: `GET /seckill/result`
  * **Params**: `goodsId`
  * **Description**: 前端輪詢此接口以獲取非同步下單的結果（成功/失敗/排隊中）。

-----

## 🧪 效能測試與優化 (Performance & Optimization)

*(此區塊可於實作後補充具體數據)*

  * **Baseline (Phase 1)**: QPS (Queries Per Second) 約為 `X`，出現超賣現象。
  * **Optimized (Phase 3)**: QPS 提升至 `Y`，超賣問題解決，DB CPU 負載穩定。

-----

### 下一步建議

您可以直接將上述內容複製到專案根目錄的 `README.md` 檔案中。

**是否需要我針對「階段二」中的 Redis 預減庫存邏輯，先幫您寫一段 Java 的虛擬碼 (Pseudo code) 範例，讓您放在 Spec 裡面看起來更豐富？**