package org.aibles.carservice.service;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

  Car createCar(CarDTO carDTO);

  Car updateCar(CarDTO carDTO, String id);

  CarDTO getCar(String id);

  Page<Car> getCars(Pageable pageable);

  Page<Car> findCarByName(String name, Pageable pageable);

  Page<Car> findCarByBrand(String brandCar, Pageable pageable);

  Page<Car> findCarByColor(String color, Pageable pageable);

  Page<Car> findCarByPrice(long price, Pageable pageable);

  Page<Car> findCarByEngineType(String engineType, Pageable pageable);

  void deleteCar(String id);

  void deleteCars();
}
