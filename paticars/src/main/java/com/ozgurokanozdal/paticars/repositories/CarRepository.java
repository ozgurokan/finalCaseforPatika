package com.ozgurokanozdal.paticars.repositories;

import com.ozgurokanozdal.paticars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByUserId(Long user_id);
}
