package org.aibles.carservice.service.impl;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

  @Autowired private CarRepository carRepository;

  @Override
  public Car createCar(CarDTO carDTO) {
    Car car = new Car();
    car.setName(carDTO.getName());
    car.setBrandCar(carDTO.getBrandCar());
    car.setStatus(carDTO.getStatus());
    car.setColor(carDTO.getColor());
    car.setPrice(carDTO.getPrice());
    car.setEngineType(carDTO.getEngineType());
    Car carCreated = carRepository.save(car);
    Optional.of(carCreated)
        .orElseThrow(
            () -> {
              throw new RuntimeException();
            });
    return carCreated;
  }

  @Override
  public Car updateCar(CarDTO carDTO, String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new RuntimeException();
    });
    car.setName(carDTO.getName());
    car.setBrandCar(carDTO.getBrandCar());
    car.setStatus(carDTO.getStatus());
    car.setColor(carDTO.getColor());
    car.setPrice(carDTO.getPrice());
    car.setEngineType(carDTO.getEngineType());
    Car carUpdate = carRepository.save(car);
    Optional.of(carUpdate)
            .orElseThrow(
                    () -> {
                      throw new RuntimeException();
                    });
    return carUpdate;
  }

  @Override
  public Car getCar(String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new RuntimeException();
    });
    return car;
  }

  @Override
  public Page<Car> getCars() {
    Page<Car> listCar = carRepository.findAll(PageRequest.of(1, 1));
    if (listCar.isEmpty())
      throw new RuntimeException();

    return listCar;
  }

  @Override
  public List<Car> findCarByName(String name) {
    return (List<Car>) carRepository.findCarByName(name).orElseThrow(() -> {
      throw new RuntimeException();
    });
  }

  @Override
  public List<Car> findCarByBrand(String brandCar) {
    return null;
  }

  @Override
  public List<Car> findCarByColor(String color) {
    return null;
  }

  @Override
  public List<Car> findCarByPrice(long price) {
    return null;
  }

  @Override
  public List<Car> findCarByEngineType(String engineType) {
    return null;
  }

  @Override
  public void deleteCar(String id) {
    boolean checkExistCar = carRepository.existsById(id);
    if (!checkExistCar)
      throw new RuntimeException("Car not existed!");
    carRepository.deleteById(id);

  }

  @Override
  public void deleteCars() {
    carRepository.deleteAll();
    List<Car> listCar=carRepository.findAll();
    if(!listCar.isEmpty())
      throw new RuntimeException("Internal Server Error");
  }
}
//paging
//cache
//format code