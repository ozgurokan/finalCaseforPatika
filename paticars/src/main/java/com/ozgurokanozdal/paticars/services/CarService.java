package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.Car;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.CarRepository;
import com.ozgurokanozdal.paticars.requests.CarCreateRequest;
import com.ozgurokanozdal.paticars.requests.CarUpdateRequest;
import com.ozgurokanozdal.paticars.responses.CarResponse;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

    public List<CarResponse> getAllCars(Optional<Long> userId){

        if(userId.isPresent()){
            List<Car> cars  = carRepository.findAllByUserId(userId.get());
            List<CarResponse> carResponses = new ArrayList<>();

            for (Car car: cars) {
                carResponses.add(new CarResponse(car));
            }


            return carResponses;
        }else{
            List<Car> cars  = carRepository.findAll();
            List<CarResponse> carResponses = new ArrayList<>();

            for (Car car: cars) {
                carResponses.add(new CarResponse(car));
            }
            return carResponses;
        }


    }

    public CarResponse saveNewCar(CarCreateRequest newCarRequest){
        User user = userService.getUserById(newCarRequest.getUser_id());
        if(user == null){
            return null;
        }else{
            Car carToSave = new Car();
            carToSave.setModel(newCarRequest.getModel());
            carToSave.setBrand(newCarRequest.getBrand());
            carToSave.setCar_plate(newCarRequest.getCar_plate());
            carToSave.setYear(newCarRequest.getYear());
            carToSave.setUser(user);
            carRepository.save(carToSave);
            return new CarResponse(carToSave);
        }

    }

    public CarResponse getCarById(Long carId) {
        Car car = carRepository.findById(carId).orElse(null);
        CarResponse carResponse = new CarResponse(car);
        return carResponse;
    }

    public CarResponse updateCarById(Long carId, CarUpdateRequest carUpdate) {
       Optional<Car> car = carRepository.findById(carId);
       if(car.isPresent()){
           Car CarToUpdate = car.get();
           CarToUpdate.setModel(carUpdate.getModel());
           CarToUpdate.setBrand(carUpdate.getBrand());
           // DB'de plaka benzersiz olduğu için zaten var olan başka bir plaka ile değiştiremez...error mesajını yaz
           CarToUpdate.setCar_plate(carUpdate.getCar_plate());
           CarToUpdate.setYear(carUpdate.getYear());
           carRepository.save(CarToUpdate);
           CarResponse carResponse = new CarResponse(CarToUpdate);
           return carResponse;
       }else{
           return null;
       }
    }

    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }
}
