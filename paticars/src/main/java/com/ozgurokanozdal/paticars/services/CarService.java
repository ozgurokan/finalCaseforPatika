package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.Car;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.CarRepository;
import com.ozgurokanozdal.paticars.requests.CarCreateRequest;
import com.ozgurokanozdal.paticars.requests.CarUpdateRequest;
import com.ozgurokanozdal.paticars.responses.CarResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public CarService(CarRepository carRepository,UserService userService,ModelMapper modelMapper){
        this.carRepository = carRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<CarResponse> getAllCars(Optional<Long> userId){

        if(userId.isPresent()){
            List<Car> cars  = carRepository.findAllByUserId(userId.get());
            List<CarResponse> carResponses = cars.stream().map(car -> new CarResponse(car)).collect(Collectors.toList());
            return carResponses;
        }else{
            List<Car> cars  = carRepository.findAll();
            List<CarResponse> carResponses = cars.stream().map(car -> new CarResponse(car)).collect(Collectors.toList());
            return carResponses;
        }


    }

    public CarResponse saveNewCar(CarCreateRequest newCarRequest){
        User user = userService.getUserById(newCarRequest.getUser_id());
        if(user == null){
            return null;
        }else{
            Car carToSave = modelMapper.map(newCarRequest, Car.class);
            carToSave.setUser(user);
            carRepository.save(carToSave);
            return modelMapper.map(carToSave,CarResponse.class);
        }
    }

    public CarResponse getCarById(Long carId) {
        Optional<Car> car = carRepository.findById(carId);

        if(car.isPresent()){
            return modelMapper.map(car, CarResponse.class);
        }
        return null;
    }

    public CarResponse updateCarById(Long carId, CarUpdateRequest carUpdate) {
       Optional<Car> car = carRepository.findById(carId);
       if(car.isPresent()){
           Car carToUpdate = car.get();
           carToUpdate.setModel(carUpdate.getModel());
           carToUpdate.setBrand(carUpdate.getBrand());
           carToUpdate.setCar_plate(carUpdate.getCar_plate());
           carToUpdate.setYear(carUpdate.getYear());
           carRepository.save(carToUpdate);
           return modelMapper.map(carToUpdate,CarResponse.class);
       }else{
           return null;
       }
    }

    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }
}
