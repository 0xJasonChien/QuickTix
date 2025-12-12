package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDto;
import jakarta.validation.constraints.NotNull;
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
  @NotNull private String name;

  @NotNull private BigDecimal price;

  @NotNull private Integer stock;
}
