package org.aibles.paymentservice.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author toanns
 */
@Entity
@Table(name = "carts")
public class Cart {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "list_car")
  private List<Object> listCar;

  @Column(name = "total_car_order")
  private long totalCarOrder;

  @Column(name = "list_voucher")
  private List<Object> listVoucher;

  @Column(name = "username")
  private String username;

  public Cart() {
    this.id= UUID.randomUUID().toString();
  }

  public Cart(
      String id,
      List<Object> listCar,
      long totalCarOrder,
      List<Object> listVoucher,
      String username) {
    this.id = id;
    this.listCar = listCar;
    this.totalCarOrder = totalCarOrder;
    this.listVoucher = listVoucher;
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Object> getListCar() {
    return listCar;
  }

  public void setListCar(List<Object> listCar) {
    this.listCar = listCar;
  }

  public long getTotalCarOrder() {
    return totalCarOrder;
  }

  public void setTotalCarOrder(long totalCarOrder) {
    this.totalCarOrder = totalCarOrder;
  }

  public List<Object> getListVoucher() {
    return listVoucher;
  }

  public void setListVoucher(List<Object> listVoucher) {
    this.listVoucher = listVoucher;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return totalCarOrder == cart.totalCarOrder
        && Objects.equals(id, cart.id)
        && Objects.equals(listCar, cart.listCar)
        && Objects.equals(listVoucher, cart.listVoucher)
        && Objects.equals(username, cart.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, listCar, totalCarOrder, listVoucher, username);
  }

  @Override
  public String toString() {
    return "Cart{"
        + "id='"
        + id
        + '\''
        + ", listCar="
        + listCar
        + ", totalCarOrder="
        + totalCarOrder
        + ", listVoucher="
        + listVoucher
        + ", username='"
        + username
        + '\''
        + '}';
  }
}
