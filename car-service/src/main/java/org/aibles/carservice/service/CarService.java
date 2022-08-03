package org.aibles.carservice.service;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

  CarDTO create(CarDTO carDTO);

  void delete(String id);

  void deleteAll();

  Page<Car> findByName(String name, Pageable pageable);

  Page<Car> findByBrand(String brandCar, Pageable pageable);

  Page<Car> findByColor(String color, Pageable pageable);

  Page<Car> findByPrice(Long price, Pageable pageable);

  Page<Car> findByEngineType(String engineType, Pageable pageable);

  CarDTO get(String id);

  Page<Car> getAll(Pageable pageable);

  CarDTO update(CarDTO carDTO, String id);

}
