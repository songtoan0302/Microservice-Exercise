package org.aibles.carservice.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

public class CarDTO {
  @NotBlank
  private String name; // tên
  @NotBlank
  private String brandCar; // thương hiệu
  @NotBlank
  private String engineType; // loại động cơ
  @NotBlank
  private String color; // màu xe
  private long price;
  private int amount;

  public CarDTO() {}

  public CarDTO(
      String name, String brandCar, String engineType, String color, long price, int amount) {
    this.name = name;
    this.brandCar = brandCar;
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

  public String getBrandCar() {
    return brandCar;
  }

  public void setBrandCar(String brandCar) {
    this.brandCar = brandCar;
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

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
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
        && Objects.equals(brandCar, carDTO.brandCar)
        && Objects.equals(engineType, carDTO.engineType)
        && Objects.equals(color, carDTO.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, brandCar, engineType, color, price, amount);
  }

  @Override
  public String toString() {
    return "CarDTO{"
        + "name='"
        + name
        + '\''
        + ", brandCar='"
        + brandCar
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
