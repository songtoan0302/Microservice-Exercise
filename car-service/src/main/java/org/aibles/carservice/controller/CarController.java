package org.aibles.carservice.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Valid;
import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;
import org.aibles.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ToanNS
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

  @Autowired private CarService carService;

  @GetMapping("/{id}")
  public ResponseEntity getCar(@RequestParam("id") String id) {
    if (id == null) throw new RuntimeException("Bad Request");
    Car car = carService.getCar(UUID.fromString(id));
    return ResponseEntity.ok(car);
  }

  @GetMapping
  public ResponseEntity getCars() {
    List cars = carService.getCars();
    return ResponseEntity.ok(cars);
  }

  @PostMapping
  public ResponseEntity addCar(@RequestBody @Valid CarDTO carDTO) {
    if (Objects.isNull(carDTO)) {
      throw new RuntimeException();
    }
    Car car = carService.createCar(carDTO);
    return ResponseEntity.ok(car);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateCar(
      @RequestBody @Valid CarDTO carDTO, @RequestParam("id") String id) {
    if (Objects.isNull(carDTO) || id == null) {
      throw new RuntimeException();
    }
    Car car = carService.updateCar(carDTO, UUID.fromString(id));
    return ResponseEntity.ok(car);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCar(@RequestParam("id") String id) {
    if (id == null) throw new RuntimeException("Bad Request");
    carService.deleteCar(UUID.fromString(id));
    return ResponseEntity.ok("Delete Car Successful!");
  }

  @DeleteMapping
  public ResponseEntity deleteCars() {
    carService.deleteCars();
    return ResponseEntity.ok("Delete Cars Successful! ");
  }
}
