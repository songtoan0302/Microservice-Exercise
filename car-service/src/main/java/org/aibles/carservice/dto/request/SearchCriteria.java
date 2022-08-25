package org.aibles.carservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aibles.carservice.constants.Operation;

/**
 * @author ToanNS
 */
@Data
@AllArgsConstructor
public class SearchCriteria {
  private String key;
  private Operation operation;
  private Object value;
}
