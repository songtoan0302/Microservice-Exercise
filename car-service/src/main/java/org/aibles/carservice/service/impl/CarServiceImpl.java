package org.aibles.carservice.service.impl;

import java.util.Objects;
import javax.transaction.Transactional;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.exception.NotFoundException;
import org.aibles.carservice.exception.InternalServerException;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.utils.ListCarsCriteria;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Transactional
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
  public CarDTO create(CarDTO carDTO) {
    Car car = modelMapper.map(carDTO, Car.class);
    if(Objects.isNull(car))
      throw new InternalServerException("Mapping fails!");
    CarDTO carDTOUpdated = modelMapper.map(carRepository.save(car), CarDTO.class);
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
  public void delete(String id) {
    carRepository.deleteById(id);
    LOGGER.info("(Delete)");
  }

  /**
   * delete all car
   */
  @CacheEvict
  @Override
  public void deleteAll() {
    carRepository.deleteAll();
    LOGGER.info("(DeleteAll)");
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
  public CarDTO update(CarDTO carDTO, String id) {
    Car car = carRepository.findById(id).orElseThrow(() -> {
      throw new NotFoundException("Car not found!");
    });
    Car carUpdate=modelMapper.map(carDTO,Car.class);
    if (Objects.isNull(carUpdate))
      throw new InternalServerException("Mapping fails");
    carUpdate.setId(car.getId());
     carUpdate = carRepository.save(carUpdate);
    LOGGER.info("(Update) car: {} ", carUpdate);
    return modelMapper.map(carUpdate, CarDTO.class);
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
  public Page<Car> list(ListCarsCriteria listCarsCriteria,Pageable pageable) {
    Page<Car> listCarPage = carRepository.findAll(listCarsCriteria.toSpecification(),pageable);
    LOGGER.info("(list) page :{}", listCarPage);
    return listCarPage;
  }
}
