package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.Car;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.CarRepository;
import com.ozgurokanozdal.paticars.requests.CarCreateRequest;
import com.ozgurokanozdal.paticars.requests.CarUpdateRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;
    private UserService userService;

    public CarService(CarRepository carRepository,UserService userService){
        this.carRepository = carRepository;
        this.userService = userService;
    }

    public List<Car> getAllCars(Optional<Long> userId){
        if(userId.isPresent()){
            return carRepository.findByUserId(userId.get());
        }
        return carRepository.findAll();

    }

    public Car saveNewCar(CarCreateRequest newCarRequest){
        User user = userService.getUserById(newCarRequest.getUser_id());
        if(user == null){
            return null;
        }else{
            Car toSave = new Car();
            toSave.setModel(newCarRequest.getModel());
            toSave.setBrand(newCarRequest.getBrand());
            toSave.setCar_plate(newCarRequest.getCar_plate());
            toSave.setYear(newCarRequest.getYear());
            toSave.setUserId(newCarRequest.getUser_id());
            return carRepository.save(toSave);
        }

    }

    public Car getCarById(Long carId) {
        // add custom exception
        return carRepository.findById(carId).orElse(null);
    }

    public Car updateCarById(Long carId, CarUpdateRequest carUpdate) {
       Optional<Car> car = carRepository.findById(carId);
       if(car.isPresent()){
           Car toUpdate = car.get();
           toUpdate.setModel(carUpdate.getModel());
           toUpdate.setBrand(carUpdate.getBrand());
           // DB'de plaka benzersiz olduğu için zaten var olan başka bir plaka ile değiştiremez...error mesajını yaz
           toUpdate.setCar_plate(carUpdate.getCar_plate());
           toUpdate.setYear(carUpdate.getYear());
           carRepository.save(toUpdate);
           return toUpdate;
       }else{
           return null;
       }
    }

    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }
}
