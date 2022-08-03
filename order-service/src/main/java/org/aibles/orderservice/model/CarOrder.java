package org.aibles.orderservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "car_order")
public class CarOrder {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "quantity")
  private int quantity;

  @OneToOne
  @JoinColumn(name = "car_id")
  private Car car;

  @ManyToOne
  @JoinColumn(name = "shopping_cart_id",referencedColumnName = "id")
  private ShoppingCart shoppingCart;

}
