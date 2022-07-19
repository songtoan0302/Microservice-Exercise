package org.aibles.carservice.service;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

  Car createCar(CarDTO carDTO);

  Car updateCar(CarDTO carDTO, String id);

  Car getCar(String id);

  Page<Car> getCars();

  List<Car> findCarByName(String name);

  List<Car> findCarByBrand(String brandCar);

  List<Car> findCarByColor(String color);

  List<Car> findCarByPrice(long price);

  List<Car> findCarByEngineType(String engineType);

  void deleteCar(String id);

  void deleteCars();
}
