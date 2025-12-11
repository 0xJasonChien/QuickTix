package com.jason.quicktix.model;

import com.jason.quicktix.comm.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends BaseEntity {

  private String username;

  private String password;

  private String email;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  @OneToMany(mappedBy = "user")
  private List<SecKillOrder> secKillOrders;
}
