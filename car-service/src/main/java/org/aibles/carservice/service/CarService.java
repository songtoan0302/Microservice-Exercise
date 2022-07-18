package org.aibles.carservice.service;

import java.util.List;
import java.util.UUID;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;

public interface CarService {

  Car createCar(CarDTO carDTO);

  Car updateCar(CarDTO carDTO, UUID id);

  Car getCar(UUID id);

  List<Car> getCars();

  void deleteCar(UUID id);

  void deleteCars();
}
