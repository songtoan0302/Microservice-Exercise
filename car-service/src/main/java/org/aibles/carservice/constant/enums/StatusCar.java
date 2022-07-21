package org.aibles.carservice.constant.enums;

/**
 * @author toanns
 */
public enum StatusCar {
  STOCKING(1, "Stocking"),
  OUT_OF_STOCK(0, "Out of stock"),
  ;

  private int code;
  private String description;

  StatusCar(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int code() {
    return code;
  }

  public String description() {
    return description;
  }
}
