package org.aibles.paymentservice.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author toanns
 */
@Entity
@Table(name = "vouchers")
public class Voucher {
  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "type_voucher")
  private String typeVoucher;

  @Column(name = "condition")
  private String condition;

  @Column(name = "description")
  private String description;

  @Column(name = "amount")
  private String amount;

  public Voucher() {
    this.id= UUID.randomUUID().toString();
  }

  public Voucher(String id, String name, String typeVoucher, String condition,
      String description, String amount) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.typeVoucher = typeVoucher;
    this.condition = condition;
    this.description = description;
    this.amount = amount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTypeVoucher() {
    return typeVoucher;
  }

  public void setTypeVoucher(String typeVoucher) {
    this.typeVoucher = typeVoucher;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}
