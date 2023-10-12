package com.ozgurokanozdal.paticars.responses;


import com.ozgurokanozdal.paticars.entities.User;


public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String username;



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
