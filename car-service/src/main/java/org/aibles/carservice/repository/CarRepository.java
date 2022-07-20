package org.aibles.carservice.repository;


import org.aibles.carservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query(value = "Select * from cars  c where c.name LIKE %?1% ", nativeQuery = true)
    Page<Car> findCarByName(@Param("name") String name,Pageable pageable);

    @Query(value = "Select * from cars  c where c.brand_car LIKE %?1% ", nativeQuery = true)
    Page<Car> findCarByBrand(String brandCar,Pageable pageable);

    @Query(value = "Select * from cars  c where c.color LIKE %?1% ", nativeQuery = true)
    Page<Car> findCarByColor(String color,Pageable pageable);

    @Query(value = "Select * from cars  c where c.price = :price ", nativeQuery = true)
    Page<Car> findCarByPrice(@Param("price") long price,Pageable pageable);

    @Query(value = "Select * from cars  c where c.engine_type LIKE %?1%", nativeQuery = true)
    Page<Car> findCarByEngineType(String engineType,Pageable pageable);

    Page<Car> findAll(Pageable pageable);

}
