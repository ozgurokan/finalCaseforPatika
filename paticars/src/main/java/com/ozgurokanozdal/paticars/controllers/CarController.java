package com.ozgurokanozdal.paticars.controllers;

import com.ozgurokanozdal.paticars.entities.Car;
import com.ozgurokanozdal.paticars.requests.CarCreateRequest;
import com.ozgurokanozdal.paticars.requests.CarUpdateRequest;
import com.ozgurokanozdal.paticars.responses.CarResponse;
import com.ozgurokanozdal.paticars.services.CarService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CarResponse>> getAllCars(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(carService.getAllCars(userId));
    }

    @PostMapping
    public ResponseEntity<CarResponse> saveNewCar(@RequestBody CarCreateRequest newCarRequest){

        return ResponseEntity.ok(carService.saveNewCar(newCarRequest));
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponse> getOneCar(@PathVariable Long carId){
        return ResponseEntity.ok(carService.getCarById(carId));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponse> updateOneCar(@PathVariable Long carId, @RequestBody CarUpdateRequest carUpdate){
        return ResponseEntity.ok(carService.updateCarById(carId,carUpdate));
    }

    @DeleteMapping("/{carId}")
    public void deleteOneCar(@PathVariable Long carId){
        carService.deleteById(carId);
    }

}
