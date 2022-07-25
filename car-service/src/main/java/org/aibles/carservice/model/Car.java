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
  private String id;

  @Column(name = "name")
  private String name; // tên

  @Column(name = "brand_car")
  private String brandCar; // thương hiệu

  @Column(name = "engine_type")
  private String engineType; // loại động cơ

  @Column(name = "color")
  private String color; // màu xe

  @Column(name = "price")
  private long price;

  @Column(name = "amount")
  private int amount;

  public Car() {
    this.id = UUID.randomUUID().toString();
  }

  public Car(String id,
      String name, String brandCar, String engineType, String color, long price, int amount) {
    this.id=UUID.randomUUID().toString();
    this.name = name;
    this.brandCar = brandCar;
    this.engineType = engineType;
    this.color = color;
    this.price = price;
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
    Car car = (Car) o;
    return price == car.price
        && amount == car.amount
        && Objects.equals(id, car.id)
        && Objects.equals(name, car.name)
        && Objects.equals(brandCar, car.brandCar)
        && Objects.equals(engineType, car.engineType)
        && Objects.equals(color, car.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brandCar, engineType, color, price, amount);
  }

  @Override
  public String toString() {
    return "Car{"
        + "id='"
        + id
        + '\''
        + ", name='"
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
