package com.ozgurokanozdal.paticars.controllers;

import com.ozgurokanozdal.paticars.entities.Car;
import com.ozgurokanozdal.paticars.requests.CarCreateRequest;
import com.ozgurokanozdal.paticars.requests.CarUpdateRequest;
import com.ozgurokanozdal.paticars.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars(@RequestParam Optional<Long> userId){
        return carService.getAllCars(userId);
    }

    @PostMapping
    public Car saveNewCar(@RequestBody CarCreateRequest newCarRequest){
        return carService.saveNewCar(newCarRequest);
    }

    @GetMapping("/{carId}")
    public Car getOneCar(@PathVariable Long carId){
        return carService.getCarById(carId);
    }

    @PutMapping("/{carId}")
    public Car updateOneCar(@PathVariable Long carId, @RequestBody CarUpdateRequest carUpdate){
        return carService.updateCarById(carId,carUpdate);
    }

    @DeleteMapping("/{carId}")
    public void deleteOneCar(@PathVariable Long carId){
        carService.deleteById(carId);
    }

}
