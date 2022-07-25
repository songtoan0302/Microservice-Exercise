package org.aibles.carservice.controller;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.exception.BadRequestException;
import org.aibles.carservice.model.Car;
import org.aibles.carservice.paging.PagingReq;
import org.aibles.carservice.paging.PagingRes;
import org.aibles.carservice.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author ToanNS
 */
@RestController
@RequestMapping("/api/v1/car")
public class CarController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

  @Autowired private CarService carService;

  @GetMapping(path = "{id}")
  public ResponseEntity getCar(@PathVariable("id") String id) {
    LOGGER.info("Get car with id: " + id);
    if (id == null) throw new BadRequestException("Invalid id");
    CarDTO car = carService.getCar(id);
    return ResponseEntity.ok(car);
  }

  @GetMapping(params = "name")
  public ResponseEntity searchByName(
      @RequestParam("name") String name, @Validated() final PagingReq pagingReq) {
    LOGGER.info("Find by name :" + name);
    LOGGER.info("Information input page: " + pagingReq);
    if (name == null) throw new BadRequestException("Invalid name!");
    Page<Car> cars = carService.findCarByName(name, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "color")
  public ResponseEntity searchByColor(
      @RequestParam("color") String color, @Validated() final PagingReq pagingReq) {
    LOGGER.info("Find by color :" + color);
    LOGGER.info("Information input page: " + pagingReq);
    if (color == null) throw new BadRequestException("Invalid color!");
    Page<Car> cars = carService.findCarByColor(color, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "engine_type")
  public ResponseEntity searchByEngineType(
      @RequestParam("engine_type") String engineType, @Validated() final PagingReq pagingReq) {
    LOGGER.info("Find by engine type :" + engineType);
    LOGGER.info("Information input page: " + pagingReq);
    if (engineType == null) throw new BadRequestException("Invalid engine type!");
    Page<Car> cars = carService.findCarByEngineType(engineType, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "price")
  public ResponseEntity searchByPrice(
      @RequestParam("price") Long price, @Validated() final PagingReq pagingReq) {
    LOGGER.info("Find by price :" + price);
    LOGGER.info("Information input page: " + pagingReq);
    if (price == null) throw new BadRequestException("Invalid price!");
    Page<Car> cars = carService.findCarByPrice(price, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping(params = "brand")
  public ResponseEntity searchByBrandCar(
      @RequestParam("brand") String brandCar, @Validated() final PagingReq pagingReq) {
    LOGGER.info("Find by brand car :" + brandCar);
    LOGGER.info("Information input page: " + pagingReq);
    if (brandCar == null) throw new BadRequestException("Invalid Brand Car!");
    Page<Car> cars = carService.findCarByBrand(brandCar, pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @GetMapping
  public ResponseEntity getCars(@Validated() final PagingReq pagingReq) {
    LOGGER.info("Information input page: " + pagingReq);
    Page cars = carService.getCars(pagingReq.makePageable());
    return ResponseEntity.ok(PagingRes.of(cars));
  }

  @PostMapping
  public ResponseEntity addCar(@RequestBody @Validated() CarDTO carDTO) {
    LOGGER.info("Create car with information: " + carDTO.toString());
    if (Objects.isNull(carDTO)) {
      throw new BadRequestException("Invalid data input!");
    }
    Car car = carService.createCar(carDTO);
    return ResponseEntity.ok(car);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity updateCar(
      @RequestBody @Valid CarDTO carDTO, @PathVariable("id") String id) {
    LOGGER.info("Create car with information: " + carDTO.toString() + " and with id :" + id);
    if (Objects.isNull(carDTO) || id == null) {
      throw new BadRequestException("Invalid data input!");
    }
    Car car = carService.updateCar(carDTO, id);
    return ResponseEntity.ok(car);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity deleteCar(@PathVariable("id") String id) {
    LOGGER.info("Delete car by id: " + id);
    if (id == null) throw new BadRequestException("Invalid id");
    carService.deleteCar(id);
    return ResponseEntity.ok("Delete Car Successful!");
  }

  @DeleteMapping
  public ResponseEntity deleteCars() {
    LOGGER.info("Deleting......!");
    carService.deleteCars();
    return ResponseEntity.ok("Delete Cars Successful! ");
  }
}
