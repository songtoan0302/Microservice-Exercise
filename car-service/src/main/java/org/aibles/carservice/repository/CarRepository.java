package org.aibles.carservice.repository;

import org.aibles.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends BaseRepository<Car, String>, JpaSpecificationExecutor<Car> {}
