package org.aibles.carservice.model;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

  @Id
  @Column(name = "id")
  private UUID id;
  @Column(name = "name")
  private String name;// tên
  @Column(name = "brand-car")
  private String brandCar;// thương hiệu
  @Column(name = "engine-type")
  private String engineType;//loại động cơ
  @Column(name = "color")
  private String color;//màu xe
  @Column(name = "price")
  private long price;
  @Column(name = "status")
  private boolean status;// con hang laf true

  public Car() {
    this.id=UUID.randomUUID();
  }

  public Car(String name, String brandCar, String engineType, String color, long price,boolean status) {
    this.name = name;
    this.brandCar = brandCar;
    this.engineType = engineType;
    this.color = color;
    this.price = price;
    this.status=status;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return price == car.price && status == car.status && Objects.equals(id, car.id)
        && Objects.equals(name, car.name) && Objects.equals(brandCar,
        car.brandCar) && Objects.equals(engineType, car.engineType)
        && Objects.equals(color, car.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brandCar, engineType, color, price, status);
  }
}
