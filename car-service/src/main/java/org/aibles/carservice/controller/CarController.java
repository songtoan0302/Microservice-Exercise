package org.aibles.carservice.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.dto.CarFilterDTO;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.paging.PagingReq;
import org.aibles.carservice.paging.PagingRes;
import org.aibles.carservice.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ToanNS
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarDTO add(@RequestBody CarDTO carDTO) {
    log.info("(Create) Car: {}", carDTO);
    return carService.create(carDTO);
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") String id) {
    log.info("(Delete) id: {} ", id);
    carService.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    log.info("(DeleteAll)");
    carService.deleteAll();
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public CarDTO get(@PathVariable("id") String id) {
    log.info("(get) id: {}", id);
    return carService.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public PagingRes<Car> list(@Validated() final PagingReq pagingReq) {
    log.info("(list) PagingReq: {}", pagingReq);
    return PagingRes.of(carService.list(pagingReq.makePageable()));
  }

  @PutMapping(path = "{id}")
  public CarDTO update(@RequestBody @Valid CarDTO carDTO, @PathVariable("id") String id) {
    log.info("(Update) CarDTO: {} , id: {}", carDTO, id);
    return carService.update(carDTO, id);
  }

  @PostMapping("/filter")
  @ResponseStatus(HttpStatus.OK)
  public List<Car> filter(@RequestBody CarFilterDTO carFilterDTO) {
    log.info("(filter) carFilterDTO: {}", carFilterDTO);
    return carService.filter(carFilterDTO);
  }
}
