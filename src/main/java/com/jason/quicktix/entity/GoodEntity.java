package com.jason.quicktix.entity;

import com.jason.quicktix.comm.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "goods")
public class GoodEntity extends BaseEntity {

  private String name;

  private BigDecimal price;

  private Integer stock;

  @OneToMany(mappedBy = "good")
  private List<OrderEntity> orders;
}
