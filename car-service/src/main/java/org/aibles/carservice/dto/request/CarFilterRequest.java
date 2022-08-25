package org.aibles.carservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CarFilterRequest {
  private String name;

  private String brand;

  private String engineType;

  private String color;

  private Long price;

  private Integer amount;
}
