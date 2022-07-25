package org.aibles.carservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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

  public CarDTO() {}

  public CarDTO(String name, String brandCar, String engineType, String color, long price) {
    this.name = name;
    this.brandCar = brandCar;
    this.engineType = engineType;
    this.color = color;
    this.price = price;
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
        + '}';
  }
}
