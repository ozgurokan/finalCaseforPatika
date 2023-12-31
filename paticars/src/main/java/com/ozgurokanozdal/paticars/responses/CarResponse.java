package com.ozgurokanozdal.paticars.responses;


import com.ozgurokanozdal.paticars.entities.Car;

public class CarResponse {

//id kaldır plakaya göre işlem yap
    // id ön yüzde gereksiz şu anlık --> information leakage

    private Long id;
    private Long user_id;
    private String model;
    private String brand;
    private String car_plate;
    private String year;

    public CarResponse(Car entity){
        this.id = entity.getId();
        this.user_id = entity.getUser().getId();
        this.model = entity.getModel();
        this.brand = entity.getBrand();
        this.car_plate = entity.getCar_plate();
        this.year = entity.getYear();
    }

    public CarResponse(){};


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
