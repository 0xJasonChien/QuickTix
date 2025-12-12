package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDto;
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
  private String name;
  private BigDecimal price;
  private Integer stock;
}
