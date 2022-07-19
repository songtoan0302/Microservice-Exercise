package org.aibles.carservice.repository;


import java.util.Optional;

import org.aibles.carservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query(value = "Select * from cars  c when c.name= :name ",nativeQuery = true)
    Optional<Car> findCarByName(@Param("name") String name);

//    Optional<Car> findCarByBrand(String brandCar);
//
//    Optional<Car> findCarByColor(String color);
//
//    Optional<Car> findCarByPrice(long price);
//
//    Optional<Car> findCarByEngineType(String engineType);
    Page<Car> findAll(Pageable pageable);

}
