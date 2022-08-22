package org.aibles.carservice.utils;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.aibles.carservice.constants.Operation;
import org.aibles.carservice.dto.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author ToanNS
 */
public class BaseCriteria<T> {

  public Specification<T> toSpecification(List<SearchCriteria> param) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      param.forEach(
          element -> {
            if (element.getOperation().equals(Operation.EQUALS)) {
              predicates.add(criteriaBuilder.equal(root.get(element.getKey()), element.getValue()));
            } else if (element.getOperation().equals(Operation.LIKE)) {
              predicates.add(
                  criteriaBuilder.like(
                      root.get(element.getKey()),
                      StringUtils.wrap(element.getValue().toString(), '%')));
            } else if (element.getOperation().equals(Operation.LESS_THEN)) {
              predicates.add(
                  criteriaBuilder.lessThan(
                      root.get(element.getKey()), element.getValue().toString()));
            } else if (element.getOperation().equals(Operation.GREATER_THEN)) {
              predicates.add(
                  criteriaBuilder.greaterThan(
                      root.get(element.getKey()), element.getValue().toString()));
            }
          });
      return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    };
  }
}
