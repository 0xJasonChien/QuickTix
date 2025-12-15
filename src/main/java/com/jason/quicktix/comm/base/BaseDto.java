package com.jason.quicktix.comm.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** The base DTO class with common fields. */
@Data
@SuperBuilder // <--- 關鍵：父類別也要加
@NoArgsConstructor // SuperBuilder 通常建議搭配這兩個建構子註解以避免錯誤
@AllArgsConstructor
public class BaseDto implements Serializable {

  private Long id;

  /** Creation timestamp. */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  /** Last update timestamp. */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;
}
