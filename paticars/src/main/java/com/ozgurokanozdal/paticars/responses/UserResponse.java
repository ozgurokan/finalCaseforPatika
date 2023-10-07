package com.ozgurokanozdal.paticars.responses;


import com.ozgurokanozdal.paticars.entities.User;


public class UserResponse {

    Long id;
    String name;
    String surname;
    String username;
    String password;
    String email;


    public UserResponse(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.username = entity.getUsername();
    }

    public UserResponse(){};




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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
