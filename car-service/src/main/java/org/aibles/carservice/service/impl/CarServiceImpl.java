package org.aibles.carservice.service.impl;

import org.aibles.carservice.constant.enums.StatusCar;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.exception.NotFoundException;
import org.aibles.carservice.exception.ServerInternalException;
import org.aibles.carservice.model.Car;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

  @Autowired
  private CarRepository carRepository;
  @Autowired
  private ModelMapper modelMapper;


  @Override
  public Car createCar(CarDTO carDTO) {
    Car car = new Car();
    car.setName(carDTO.getName());
    car.setBrandCar(carDTO.getBrandCar());
    car.setStatus(StatusCar.STOCKING.code());
    car.setColor(carDTO.getColor());
    car.setPrice(carDTO.getPrice());
    car.setEngineType(carDTO.getEngineType());
    Car carCreated = carRepository.save(car);
    LOGGER.info("Created car: " + carCreated.toString());
    Optional.ofNullable(carCreated)
            .orElseThrow(
                    () -> {
                      throw new ServerInternalException("Create car failed!Please try again!");
                    });
    return carCreated;
  }

  @CachePut(value = "car", key = "#id")
  @Override
  public Car updateCar(CarDTO carDTO, String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new NotFoundException("Car not found! ");
    });
    car.setName(carDTO.getName());
    car.setBrandCar(carDTO.getBrandCar());
    car.setStatus(StatusCar.STOCKING.code());
    car.setColor(carDTO.getColor());
    car.setPrice(carDTO.getPrice());
    car.setEngineType(carDTO.getEngineType());
    Car carUpdate = carRepository.save(car);
    LOGGER.info("Updated car: " + carUpdate.toString());
    Optional.of(carUpdate).orElseThrow(() -> {
      throw new ServerInternalException("Create car failed!Please try again!");
    });
    return carUpdate;
  }

  @Cacheable(value = "car", key = "#id")
  @Override
  public CarDTO getCar(String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new NotFoundException("Car not found! ");
    });
    LOGGER.info("Get car by id with result:" + car.toString());
    return modelMapper.map(car, CarDTO.class);
  }

  @Override
  public Page<Car> getCars(Pageable pageable) {
    Page<Car> listCar = carRepository.findAll(pageable);
    Optional.ofNullable(listCar).orElseThrow(() -> {
      throw new NotFoundException("Cars not found!");
    });
    LOGGER.info("Get list car: " + listCar.stream().toList());
    return listCar;
  }

  @Override
  public Page<Car> findCarByName(String name, Pageable pageable) {
    Page<Car> carPage = carRepository.findCarByName(name, pageable);
    Optional.ofNullable(carRepository.findCarByName(name, pageable)).orElseThrow(() -> {
      throw new NotFoundException("Name not existed!");
    });
    LOGGER.info("GET BY NAME: " + carPage.getContent());
    return carRepository.findCarByName(name, pageable);
  }

  @Override
  public Page<Car> findCarByBrand(String brandCar, Pageable pageable) {
    Page<Car> carsPage = carRepository.findCarByBrand(brandCar, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Brand Car not existed!");
    });
    LOGGER.info("GET BY NAME: " + carsPage.getContent());
    return carsPage;
  }

  @Override
  public Page<Car> findCarByColor(String color, Pageable pageable) {
    Page<Car> carsPage = carRepository.findCarByColor(color, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Color not existed!");
    });
    LOGGER.info("GET BY COLOR: " + carsPage.getContent());
    return carsPage;
  }

  @Override
  public Page<Car> findCarByPrice(long price, Pageable pageable) {
    Page<Car> carsPage = carRepository.findCarByPrice(price, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Color not existed!");
    });
    LOGGER.info("GET BY PRICE: " + carsPage.getContent());
    return carsPage;
  }

  @Override
  public Page<Car> findCarByEngineType(String engineType, Pageable pageable) {
    Page<Car> carsPage = carRepository.findCarByEngineType(engineType, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Engine type not existed!");
    });
    LOGGER.info("GET BY ENGINE TYPE : " + carsPage.getContent());
    return null;
  }

  @Cacheable(value = "car", key = "#id")
  @Override
  public void deleteCar(String id) {
    boolean checkExistCar = carRepository.existsById(id);
    if (!checkExistCar)
      throw new NotFoundException("Car not found!");
    carRepository.deleteById(id);
    boolean checkExistCarDelete = carRepository.existsById(id);
    if (checkExistCarDelete)
      throw new ServerInternalException("Delete car failed!");
    LOGGER.info("Delete car successful");
  }

  @CacheEvict
  @Override
  public void deleteCars() {
    carRepository.deleteAll();
    List<Car> listCar = carRepository.findAll();
    if (!listCar.isEmpty())
      throw new ServerInternalException("Delete all car failed!");
    LOGGER.info("Delete all car successful");
  }

}
//paging
//cache
//format code
//handler exception