package org.aibles.carservice.service.impl;

import javax.transaction.Transactional;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.exception.NotFoundException;
import org.aibles.carservice.exception.ServerInternalException;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

  private final CarRepository carRepository;
  private final ModelMapper modelMapper;

  public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
    this.carRepository = carRepository;
    this.modelMapper = modelMapper;
  }

  /**
   * create  a car
   *
   * @param carDTO
   * @return
   */
  @Override
  @Transactional
  public CarDTO create(CarDTO carDTO) {
    Car car = modelMapper.map(carDTO, Car.class);
    Car carCreated = carRepository.save(car);
    Optional.ofNullable(carCreated).orElseThrow(() -> {
      throw new ServerInternalException("Create car failed!Please try again!");
    });
    CarDTO carDTOUpdated = modelMapper.map(carCreated, CarDTO.class);
    LOGGER.info("(Create) CarDTO: {}", carDTOUpdated);
    return carDTOUpdated;
  }

  /**
   * delete car by id
   *
   * @param id
   */
  @Cacheable(value = "car", key = "#id")
  @Override
  @Transactional
  public void delete(String id) {
    boolean checkExistCar = carRepository.existsById(id);
    if (!checkExistCar) {
      throw new NotFoundException("Car not found!");
    }
    carRepository.deleteById(id);
    boolean checkExistCarDelete = carRepository.existsById(id);
    if (checkExistCarDelete) {
      throw new ServerInternalException("Delete car failed!");
    }
    LOGGER.info("(Delete)");
  }

  /**
   * delete all car
   */
  @CacheEvict
  @Override
  @Transactional
  public void deleteAll() {
    carRepository.deleteAll();
    List<Car> listCar = carRepository.findAll();
    if (!listCar.isEmpty()) {
      throw new ServerInternalException("Delete all car failed!");
    }
    LOGGER.info("(DeleteAll)");
  }

  /**
   * find car by name paging
   *
   * @param name
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> findByName(String name, Pageable pageable) {
    Page<Car> carPage = carRepository.findByName(name, pageable);
    Optional.ofNullable(carRepository.findByName(name, pageable)).orElseThrow(() -> {
      throw new NotFoundException("Name not existed!");
    });
    LOGGER.info("(FindByName) page :{}", carPage);
    return carPage;
  }

  /**
   * find car by brand paging
   *
   * @param brandCar
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> findByBrand(String brandCar, Pageable pageable) {
    Page<Car> carsPage = carRepository.findByBrand(brandCar, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Brand Car not existed!");
    });
    LOGGER.info("(FindByBrand) page :{}", carsPage);
    return carsPage;
  }

  /**
   * find car by color paging
   *
   * @param color
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> findByColor(String color, Pageable pageable) {
    Page<Car> carsPage = carRepository.findByColor(color, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Color not existed!");
    });
    LOGGER.info("(FindByColor) page :{}", carsPage);
    return carsPage;
  }


  /**
   * find car by price paging
   *
   * @param price
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> findByPrice(Long price, Pageable pageable) {
    Page<Car> carsPage = carRepository.findByPrice(price, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Color not existed!");
    });
    LOGGER.info("(FindByPrice) page :{}", carsPage);
    return carsPage;
  }


  /**
   * find car by engine type paging
   *
   * @param engineType
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> findByEngineType(String engineType, Pageable pageable) {
    Page<Car> carsPage = carRepository.findByEngineType(engineType, pageable);
    Optional.ofNullable(carsPage).orElseThrow(() -> {
      throw new NotFoundException("Engine type not existed!");
    });
    LOGGER.info("(FindByEngineType) page :{}", carsPage);
    return carsPage;
  }

  /**
   * update a car
   *
   * @param carDTO
   * @param id
   * @return
   */
  @CachePut(value = "car", key = "#id")
  @Override
  @Transactional
  public CarDTO update(CarDTO carDTO, String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new NotFoundException("Car not found! ");
    });
    car = modelMapper.map(carDTO, Car.class);
    Car carUpdate = carRepository.save(car);
    Optional.of(carUpdate).orElseThrow(() -> {
      throw new ServerInternalException("Create car failed!Please try again!");
    });
    CarDTO carDTOUpdated = modelMapper.map(carUpdate, CarDTO.class);
    LOGGER.info("(Update) car: {} ", carDTOUpdated);
    return carDTOUpdated;
  }

  /**
   * get a car by id
   *
   * @param id
   * @return
   */
  @Cacheable(value = "car", key = "#id")
  @Override
  public CarDTO get(String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new NotFoundException("Car not found! ");
    });
    CarDTO carDTO = modelMapper.map(car, CarDTO.class);
    LOGGER.info("(Get) carDTO: {} ", carDTO);
    return carDTO;
  }

  /**
   * get all car
   *
   * @param pageable
   * @return
   */
  @Override
  public Page<Car> getAll(Pageable pageable) {
    Page<Car> listCarPage = carRepository.findAll(pageable);
    Optional.ofNullable(listCarPage).orElseThrow(() -> {
      throw new NotFoundException("Cars not found!");
    });
    LOGGER.info("(Find) page :{}", listCarPage);
    return listCarPage;
  }
}
