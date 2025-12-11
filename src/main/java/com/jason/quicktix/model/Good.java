package com.jason.quicktix.model;

import com.jason.quicktix.comm.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Good extends BaseEntity {

  private String name;

  private BigDecimal price;

  private Integer stock;

  @OneToMany(mappedBy = "good")
  private List<Order> orders;

  @OneToMany(mappedBy = "good")
  private List<SecKillOrder> secKillOrders;
}
