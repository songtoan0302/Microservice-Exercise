package org.aibles.carservice.repository;

import org.aibles.carservice.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

  Page<Car> findAll(Pageable pageable);

  @Query(value = "Select * from cars  c where c.brand_car LIKE %?1% ", nativeQuery = true)
  Page<Car> findByBrand(String brandCar, Pageable pageable);

  @Query(value = "Select * from cars  c where c.color LIKE %?1% ", nativeQuery = true)
  Page<Car> findByColor(String color, Pageable pageable);

  @Query(value = "Select * from cars  c where c.engine_type LIKE %?1%", nativeQuery = true)
  Page<Car> findByEngineType(String engineType, Pageable pageable);

  @Query(value = "Select * from cars  c where c.name LIKE %?1% ", nativeQuery = true)
  Page<Car> findByName(@Param("name") String name, Pageable pageable);

  @Query(value = "Select * from cars  c where c.price = :price ", nativeQuery = true)
  Page<Car> findByPrice(@Param("price") long price, Pageable pageable);

}
