package org.aibles.carservice.service;

import java.util.List;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.dto.CarFilterDTO;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.utils.BaseCriteria;
import org.aibles.carservice.utils.ListCarsCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

  CarDTO create(CarDTO carDTO);

  void delete(String id);

  void deleteAll();

  CarDTO get(String id);

  Page<Car> list(Pageable pageable);

  CarDTO update(CarDTO carDTO, String id);

  List<Car> filter(CarFilterDTO carFilterDTO);

}
