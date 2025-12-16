package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderDto extends BaseDto {
  @NotNull(message = "用戶ID不能為空")
  private Long userId;

  @NotNull(message = "商品ID不能為空")
  private Long goodId;

  @NotNull(message = "商品數量不能為空")
  private Integer amount;

  @NotBlank(message = "地址不能為空")
  private String address;
}
