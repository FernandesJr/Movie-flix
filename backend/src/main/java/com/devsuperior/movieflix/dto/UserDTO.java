package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private String name;

    public UserDTO(){}

    public UserDTO(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
