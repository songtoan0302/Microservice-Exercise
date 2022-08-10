package org.aibles.carservice.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

public class CarDTO {
  @NotBlank
  private String name; // tên
  @NotBlank
  private String brand; // thương hiệu
  @NotBlank
  private String engineType; // loại động cơ
  @NotBlank
  private String color; // màu xe
  private Long price;
  private Integer amount;

  public CarDTO() {}

  public CarDTO(
      String name, String brand, String engineType, String color, Long price, Integer amount) {
    this.name = name;
    this.brand = brand;
    this.engineType = engineType;
    this.color = color;
    this.price = price;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getbrand() {
    return brand;
  }

  public void setbrand(String brand) {
    this.brand = brand;
  }

  public String getEngineType() {
    return engineType;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
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
    CarDTO carDTO = (CarDTO) o;
    return price == carDTO.price
        && amount == carDTO.amount
        && Objects.equals(name, carDTO.name)
        && Objects.equals(brand, carDTO.brand)
        && Objects.equals(engineType, carDTO.engineType)
        && Objects.equals(color, carDTO.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, brand, engineType, color, price, amount);
  }

  @Override
  public String toString() {
    return "CarDTO{"
        + "name='"
        + name
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", engineType='"
        + engineType
        + '\''
        + ", color='"
        + color
        + '\''
        + ", price="
        + price
        + ", amount="
        + amount
        + '}';
  }
}
