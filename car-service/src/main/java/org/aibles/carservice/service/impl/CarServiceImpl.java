package org.aibles.carservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.constants.Operation;
import org.aibles.carservice.dto.request.CarCreate;
import org.aibles.carservice.dto.request.CarFilterRequest;
import org.aibles.carservice.dto.request.CarUpdate;
import org.aibles.carservice.dto.request.SearchCriteria;
import org.aibles.carservice.dto.response.CarResponse;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exceptions.InternalServerException;
import org.aibles.carservice.exceptions.NotFoundException;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.components.BaseCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
   * @param carCreate
   * @return
   */
  @Override
  @Transactional
  public CarResponse create(CarCreate carCreate) {
    log.info("(create)CarDTO: {}", carCreate);
    Car car = modelMapper.map(carCreate, Car.class);
    if (Objects.isNull(car)) throw new InternalServerException("Mapping fails!");
    return modelMapper.map(carRepository.save(car), CarResponse.class);
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
    log.info("(delete)");
    carRepository.deleteById(id);
  }

  /** delete all car */
  @CacheEvict
  @Override
  @Transactional
  public void deleteAll() {
    log.info("(deleteAll)");
    carRepository.deleteAll();
  }

  /**
   * update a car
   *
   * @param carCreate
   * @param id
   * @return
   */
  @CachePut(value = "car", key = "#id")
  @Override
  @Transactional
  public CarResponse update(CarUpdate carUpdate, String id) {
    log.info("(update)carDTO: {}, id: {} ", carUpdate, id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new NotFoundException(id);
                });
    Car carUpdated = modelMapper.map(carUpdate, Car.class);
    if (Objects.isNull(carUpdated)) throw new InternalServerException("Mapping fails");
    carUpdated.setId(car.getId());
    carUpdated = carRepository.save(carUpdated);
    return modelMapper.map(carUpdated, CarResponse.class);
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
  public CarResponse get(String id) {
    log.info("(get)id: {} ", id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new NotFoundException(id);
                });
    return modelMapper.map(car, CarResponse.class);
  }

  /**
   * get all car
   *
   * @param pageable
   * @return
   */
  @Override
  @Transactional(readOnly = true)
  public Page<CarResponse> list(Pageable pageable) {
    log.info("(list)page: {}", pageable);
    return carRepository.findAll(pageable).map(car -> modelMapper.map(car,CarResponse.class));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Car> filter(CarFilterRequest carFilterRequest) {
    log.info("(filter)carFilterDTO: {}", carFilterRequest);
    BaseCriteria<Car> baseCriteria = new BaseCriteria<>();
    List<SearchCriteria> searchCriteriaList = buildListSearchCriteria(carFilterRequest);
    return carRepository.findAll(baseCriteria.toSpecification(searchCriteriaList));
  }

  private List<SearchCriteria> buildListSearchCriteria(CarFilterRequest carFilterRequest) {
    List<SearchCriteria> searchCriteriaList = new ArrayList<>();
    if (Objects.nonNull(carFilterRequest.getName())) {
      searchCriteriaList.add(new SearchCriteria("name", Operation.LIKE, carFilterRequest.getName()));
    }
    if (Objects.nonNull(carFilterRequest.getAmount())) {
      searchCriteriaList.add(
          new SearchCriteria("amount", Operation.EQUALS, carFilterRequest.getAmount()));
    }
    if (Objects.nonNull(carFilterRequest.getBrand())) {
      searchCriteriaList.add(new SearchCriteria("brand", Operation.LIKE, carFilterRequest.getBrand()));
    }
    if (Objects.nonNull(carFilterRequest.getColor())) {
      searchCriteriaList.add(new SearchCriteria("color", Operation.LIKE, carFilterRequest.getColor()));
    }
    if (Objects.nonNull(carFilterRequest.getEngineType())) {
      searchCriteriaList.add(
          new SearchCriteria("engineType", Operation.LIKE, carFilterRequest.getEngineType()));
    }
    if (Objects.nonNull(carFilterRequest.getPrice())) {
      searchCriteriaList.add(
          new SearchCriteria("price", Operation.EQUALS, carFilterRequest.getPrice()));
    }
    return searchCriteriaList;
  }
}
