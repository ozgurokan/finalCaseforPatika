package com.ozgurokanozdal.paticars.repositories;

import com.ozgurokanozdal.paticars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByUserId(Long user_id);


    List<Car> findAllByUserIdAndBrand(Long userId, String brand);

    List<Car> findAllByUserIdAndModel(Long aLong, String s);


    List<Car> findAllByUserIdAndBrandAndModel(Long aLong, String s, String s1);
}
