package org.aibles.paymentservice.dto;

import java.util.Objects;
import javax.persistence.Column;

/**
 * @author toanns
 */
public class VoucherDTO {
  private String name;

  private String typeVoucher;

  private String condition;

  private String description;

  private String amount;

  public VoucherDTO() {
  }

  public VoucherDTO(String name, String typeVoucher, String condition, String description,
      String amount) {
    this.name = name;
    this.typeVoucher = typeVoucher;
    this.condition = condition;
    this.description = description;
    this.amount = amount;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VoucherDTO that = (VoucherDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(typeVoucher,
        that.typeVoucher) && Objects.equals(condition, that.condition)
        && Objects.equals(description, that.description) && Objects.equals(amount,
        that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, typeVoucher, condition, description, amount);
  }

  @Override
  public String toString() {
    return "VoucherDTO{" +
        "name='" + name + '\'' +
        ", typeVoucher='" + typeVoucher + '\'' +
        ", condition='" + condition + '\'' +
        ", description='" + description + '\'' +
        ", amount='" + amount + '\'' +
        '}';
  }
}
