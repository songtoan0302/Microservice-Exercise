package org.aibles.carservice.utils;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import org.aibles.carservice.entity.Car;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ListCarsCriteria {

  private String name;
  private String brandCar;
  private String engineType;
  private String color;
  private Long price;


  public Specification<Car> toSpecification() {
    return (root, query, criteriaBuilder)
        -> {
      List<Predicate> predicate = new ArrayList<>();
      if (StringUtils.isNotBlank(name)) {
        predicate.add(criteriaBuilder.like(root.get("name"), StringUtils.wrap(name, '%')));
      }
      if (StringUtils.isNotBlank(brandCar)) {
        predicate.add(criteriaBuilder.like(root.get("brandCar"), StringUtils.wrap(brandCar, '%')));
      }
      if (StringUtils.isNotBlank(engineType)) {
        predicate.add(criteriaBuilder.like(root.get("engineType"), StringUtils.wrap(engineType, '%')));
      }
      if (StringUtils.isNotBlank(color)) {
        predicate.add(criteriaBuilder.like(root.get("color"), StringUtils.wrap(color, '%')));
      }
      if (price != null) {
        predicate.add(criteriaBuilder.equal(root.get("price"), price));
      }

      return criteriaBuilder.and(predicate.toArray(Predicate[]::new));
    };
  }
}


