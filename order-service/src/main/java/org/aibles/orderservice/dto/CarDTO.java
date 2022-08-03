package org.aibles.orderservice.dto;

public class CarDTO {
    private String id;

    private String name;

    private String brandCar;
    /**
     * loại động cơ
     */
    private String engineType;
    private String color;

    private long price;
    /**
     * số lượng còn lại của xe
     */
    private int amount;
}
