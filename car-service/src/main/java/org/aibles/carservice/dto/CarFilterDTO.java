package org.aibles.carservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CarFilterDTO {
  private String name;

  private String brand;

  private String engineType;

  private String color;

  private Long price;

  private Integer amount;
}
