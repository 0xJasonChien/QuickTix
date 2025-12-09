package com.jason.quicktix.entity;

import java.math.BigDecimal;

import com.jason.quicktix.comm.base.BaseEntity;

public class Good extends BaseEntity{

    private String name;

    private BigDecimal price;

    private Integer stock;
}
