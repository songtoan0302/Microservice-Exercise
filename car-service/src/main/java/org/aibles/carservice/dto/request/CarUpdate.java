package org.aibles.carservice.dto.request;

import lombok.Data;

/**
 * @author ToanNS
 */
@Data
public class CarUpdate extends CarCreate{

  private String id;

  public CarUpdate() {
  }

  public CarUpdate(String name, String brand, String engineType, String color, Long price,
      Integer amount, String id) {
    super(name, brand, engineType, color, price, amount);
    this.id = id;
  }
}
