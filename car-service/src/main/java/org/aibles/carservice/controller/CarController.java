package org.aibles.carservice.controller;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.paging.PagingReq;
import org.aibles.carservice.paging.PagingRes;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.utils.ListCarsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ToanNS
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarDTO add(@RequestBody @Validated() CarDTO carDTO) {
    LOGGER.info("(Create) Car: {}", carDTO);
    return carService.create(carDTO);
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") String id) {
    LOGGER.info("(Delete) id: {} ", id);
    carService.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    LOGGER.info("(DeleteAll)");
    carService.deleteAll();
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public CarDTO get(@PathVariable("id") String id) {
    LOGGER.info("(get) id: {}",id);
    return carService.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public PagingRes<Car> list(@RequestBody ListCarsCriteria listCarsCriteria,@Validated() final PagingReq pagingReq) {
    LOGGER.info("(list) PagingReq: {}", pagingReq);
    return PagingRes.of(carService.list(listCarsCriteria,pagingReq.makePageable()));
  }

  @PutMapping(path = "{id}")
  public CarDTO update(@RequestBody @Valid CarDTO carDTO, @PathVariable("id") String id) {
    LOGGER.info("(Update) CarDTO: {} , id: {}", carDTO, id);
    return carService.update(carDTO, id);
  }

}
