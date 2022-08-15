package org.aibles.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "brand")
  private String brand;

  @Column(name = "engine_type")
  private String engineType;

  @Column(name = "color")
  private String color;

  @Column(name = "price")
  private long price;

  @Column(name = "amount")
  private int amount;

}
