package org.aibles.carservice.dto;

import javax.validation.constraints.NotBlank;

public class CarDTO {
  @NotBlank
  private String name;// tên
  @NotBlank
  private String brandCar;// thương hiệu
  @NotBlank
  private String engineType;//loại động cơ
  @NotBlank
  private String color;//màu xe
  @NotBlank
  private long price;
  @NotBlank
  private boolean status;//trạng thai con hang

  public CarDTO() {
  }

  public CarDTO(String name, String brandCar, String engineType, String color, long price,
      boolean status) {
    this.name = name;
    this.brandCar = brandCar;
    this.engineType = engineType;
    this.color = color;
    this.price = price;
    this.status = status;
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

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
