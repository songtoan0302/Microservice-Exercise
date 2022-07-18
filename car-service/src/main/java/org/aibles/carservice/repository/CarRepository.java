package org.aibles.carservice.repository;


import java.util.UUID;
import org.aibles.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

}
