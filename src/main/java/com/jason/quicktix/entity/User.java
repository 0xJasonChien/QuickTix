package com.jason.quicktix.entity;

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
  private List<OrderEntity> orders;

  @OneToMany(mappedBy = "user")
  private List<SecKillOrderEntity> secKillOrders;
}
