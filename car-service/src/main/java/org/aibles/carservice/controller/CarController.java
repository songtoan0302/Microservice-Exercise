package org.aibles.carservice.controller;

import org.aibles.carservice.dto.CarDTO;
import org.aibles.carservice.model.Car;
import org.aibles.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
    Car car = carService.getCar(id);
    return ResponseEntity.ok(car);
  }

  @GetMapping("/search/{name}")
  public ResponseEntity searchByName(@RequestParam("name") String name) {
    if (name == null) throw new RuntimeException("Bad Request");
    List<Car> cars = carService.findCarByName(name);
    return ResponseEntity.ok(cars);
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
    Car car = carService.updateCar(carDTO, id);
    return ResponseEntity.ok(car);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCar(@RequestParam("id") String id) {
    if (id == null) throw new RuntimeException("Bad Request");
    carService.deleteCar(id);
    return ResponseEntity.ok("Delete Car Successful!");
  }

  @DeleteMapping
  public ResponseEntity deleteCars() {
    carService.deleteCars();
    return ResponseEntity.ok("Delete Cars Successful! ");
  }
}
