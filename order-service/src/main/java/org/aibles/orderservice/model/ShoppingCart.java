package org.aibles.orderservice.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_cart")
public class ShoppingCart {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "username")
  private String username;

  @OneToMany(mappedBy = "shopping_cart",cascade = CascadeType.ALL)
  private CarOrder carOrder;

}
