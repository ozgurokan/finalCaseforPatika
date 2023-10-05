package com.ozgurokanozdal.paticars.responses;


import com.ozgurokanozdal.paticars.entities.User;

public class UserResponse {

    Long id;
    String name;
    String surname;

    public UserResponse(User entitiy){
        this.id = entitiy.getId();
        this.name = entitiy.getName();
        this.surname = entitiy.getSurname();
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
