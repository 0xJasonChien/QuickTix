package com.jason.quicktix.comm.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// 1. 標記為 MappedSuperclass，表示這不是一個完整的 Table，而是讓子類別繼承欄位用
@MappedSuperclass
// 2. 啟動 JPA Auditing 監聽器，這樣 @CreatedDate 才會生效
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 3. 自動填入創建時間，updatable = false 防止更新時被修改
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  // 4. 自動填入最後修改時間
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
