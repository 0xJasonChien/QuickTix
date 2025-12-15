package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GoodDto extends BaseDto {
  @NotBlank(message = "名稱不能為空")
  private String name;

  @NotNull(message = "價格不能為空")
  // ✅ 建議加上數值檢查 (例如：價格必須 >= 0)
  @PositiveOrZero(message = "價格不能為負數")
  private BigDecimal price;

  @NotNull(message = "庫存不能為空")
  private Integer stock;
}
