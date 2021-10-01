package com.example.oauth2resourceserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {

    private String name;
    private String email;

    public UserProfile(String name){
        this.name = name;
    }

}
