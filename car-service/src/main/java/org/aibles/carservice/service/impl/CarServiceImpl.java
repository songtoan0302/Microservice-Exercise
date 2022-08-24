package org.aibles.carservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.constants.Operation;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.dto.CarFilterDTO;
import org.aibles.carservice.dto.SearchCriteria;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exception.SystemException;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.utils.BaseCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final ModelMapper modelMapper;

  public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
    this.carRepository = carRepository;
    this.modelMapper = modelMapper;
  }

  /**
   * create a car
   *
   * @param carDTO
   * @return
   */
  @Override
  @Transactional
  public CarDTO create(CarDTO carDTO) {
    log.info("(Create) CarDTO: {}", carDTO);
    Car car = modelMapper.map(carDTO, Car.class);
    if (Objects.isNull(car))
      throw new SystemException("Mapping fails!", HttpStatus.INTERNAL_SERVER_ERROR);
    return modelMapper.map(carRepository.save(car), CarDTO.class);
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
    log.info("(Delete)");
    carRepository.deleteById(id);
  }

  /** delete all car */
  @CacheEvict
  @Override
  @Transactional
  public void deleteAll() {
    log.info("(DeleteAll)");
    carRepository.deleteAll();
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
    log.info("(Update) carDTO: {},id: {} ", carDTO, id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new SystemException("Car not found!", HttpStatus.NOT_FOUND);
                });
    Car carUpdate = modelMapper.map(carDTO, Car.class);
    if (Objects.isNull(carUpdate))
      throw new SystemException("Mapping fails", HttpStatus.INTERNAL_SERVER_ERROR);
    carUpdate.setId(car.getId());
    carUpdate = carRepository.save(carUpdate);
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
  @Transactional(readOnly = true)
  public CarDTO get(String id) {
    log.info("(Get) id: {} ", id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new SystemException("Car not found", HttpStatus.NOT_FOUND);
                });
    return modelMapper.map(car, CarDTO.class);
  }

  /**
   * get all car
   *
   * @param pageable
   * @return
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Car> list(Pageable pageable) {
    log.info("(list) page");
    return carRepository.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Car> filter(CarFilterDTO carFilterDTO) {
    log.info("(filter) carFilterDTO: {}", carFilterDTO);
    BaseCriteria<Car> baseCriteria = new BaseCriteria<>();
    List<SearchCriteria> searchCriteriaList = buildListSearchCriteria(carFilterDTO);
    return carRepository.findAll(baseCriteria.toSpecification(searchCriteriaList));
  }

  private List<SearchCriteria> buildListSearchCriteria(CarFilterDTO carFilterDTO) {
    List<SearchCriteria> searchCriteriaList = new ArrayList<>();
    if (Objects.nonNull(carFilterDTO.getName())) {
      searchCriteriaList.add(new SearchCriteria("name", Operation.LIKE, carFilterDTO.getName()));
    }
    if (Objects.nonNull(carFilterDTO.getAmount())) {
      searchCriteriaList.add(
          new SearchCriteria("amount", Operation.EQUALS, carFilterDTO.getAmount()));
    }
    if (Objects.nonNull(carFilterDTO.getBrand())) {
      searchCriteriaList.add(new SearchCriteria("brand", Operation.LIKE, carFilterDTO.getBrand()));
    }
    if (Objects.nonNull(carFilterDTO.getColor())) {
      searchCriteriaList.add(new SearchCriteria("color", Operation.LIKE, carFilterDTO.getColor()));
    }
    if (Objects.nonNull(carFilterDTO.getEngineType())) {
      searchCriteriaList.add(
          new SearchCriteria("engineType", Operation.LIKE, carFilterDTO.getEngineType()));
    }
    if (Objects.nonNull(carFilterDTO.getPrice())) {
      searchCriteriaList.add(
          new SearchCriteria("price", Operation.EQUALS, carFilterDTO.getPrice()));
    }
    return searchCriteriaList;
  }
}
