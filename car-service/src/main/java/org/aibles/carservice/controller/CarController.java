package org.aibles.carservice.controller;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.exception.BadRequestException;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.paging.PagingReq;
import org.aibles.carservice.paging.PagingRes;
import org.aibles.carservice.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

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
  public ResponseEntity add(@RequestBody @Validated() CarDTO carDTO) {
    LOGGER.info("(Create) Car: {}", carDTO);
    if (Objects.isNull(carDTO)) {
      throw new BadRequestException("Invalid data input!");
    }
    CarDTO car = carService.create(carDTO);
    return ResponseEntity.ok(car);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity delete(@PathVariable("id") String id) {
    LOGGER.info("(Delete) id: {} ", id);
    if (id == null) {
      throw new BadRequestException("Invalid id");
    }
    carService.delete(id);
    return ResponseEntity.ok("Delete Car Successful!");
  }

  @DeleteMapping
  public ResponseEntity deleteAll() {
    LOGGER.info("(DeleteAll)");
    carService.deleteAll();
    return ResponseEntity.ok("Delete Cars Successful! ");
  }

  @GetMapping(path = "{id}")
  public ResponseEntity get(@PathVariable("id") String id) {
    LOGGER.info("(Get) id: {} ", id);
    if (id == null) {
      throw new BadRequestException("Invalid id");
    }
    CarDTO car = carService.get(id);
    return ResponseEntity.ok(car);
  }

  @GetMapping
  public ResponseEntity getAll(@Validated() final PagingReq pagingReq) {
    LOGGER.info("(GetAll) PagingReq: {}", pagingReq);
    Page cars = carService.getAll(pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "name")
  public ResponseEntity searchByName(
      @RequestParam("name") String name, @Validated() final PagingReq pagingReq) {
    LOGGER.info("(Search) name: {},PagingReq: {} ", name, pagingReq);
    if (name == null) {
      throw new BadRequestException("Invalid name!");
    }
    Page<Car> cars = carService.findByName(name, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "color")
  public ResponseEntity searchByColor(
      @RequestParam("color") String color, @Validated() final PagingReq pagingReq) {
    LOGGER.info("(Search) color: {} ,PagingReq: {} ", color, pagingReq);
    if (color == null) {
      throw new BadRequestException("Invalid color!");
    }
    Page<Car> cars = carService.findByColor(color, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "engine_type")
  public ResponseEntity searchByEngineType(
      @RequestParam("engine_type") String engineType, @Validated() final PagingReq pagingReq) {
    LOGGER.info("(Search) engine type: {} ,PagingReq: {} ", engineType, pagingReq);
    if (engineType == null) {
      throw new BadRequestException("Invalid engine type!");
    }
    Page<Car> cars = carService.findByEngineType(engineType, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }


  @GetMapping(params = "price")
  public ResponseEntity searchByPrice(
      @RequestParam("price") Long price, @Validated() final PagingReq pagingReq) {
    LOGGER.info("(Search) price: {} ,PagingReq: {}", price, pagingReq);
    if (price == null) {
      throw new BadRequestException("Invalid price!");
    }
    Page<Car> cars = carService.findByPrice(price, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "brand")
  public ResponseEntity searchByBrandCar(
      @RequestParam("brand") String brandCar, @Validated() final PagingReq pagingReq) {
    LOGGER.info("(Search) brand: {},PagingReq: {}", brandCar, pagingReq);
    if (brandCar == null) {
      throw new BadRequestException("Invalid Brand Car!");
    }
    Page<Car> cars = carService.findByBrand(brandCar, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity update(
      @RequestBody @Valid CarDTO carDTO, @PathVariable("id") String id) {
    LOGGER.info("(Update) CarDTO: {} , id: {}", carDTO, id);
    if (Objects.isNull(carDTO) || id == null) {
      throw new BadRequestException("Invalid data input!");
    }
    CarDTO car = carService.update(carDTO, id);
    return ResponseEntity.ok(car);
  }

}
