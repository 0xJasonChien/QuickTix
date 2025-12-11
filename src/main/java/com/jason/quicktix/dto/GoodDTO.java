package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDTO;
import java.math.BigDecimal;

public class GoodDTO extends BaseDTO {
  private String name;
  private BigDecimal price;
  private Integer stock;
}
