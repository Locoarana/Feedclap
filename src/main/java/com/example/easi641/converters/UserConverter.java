package com.example.easi641.converters;

import com.example.easi641.dto.SignupRequestDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.User;

public class UserConverter extends  AbstractConverter<User, UserDto> {

    @Override
    public UserDto fromEntity(User entity) {
        if(entity == null) return null;
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                //.name(entity.getName())
                //.email(entity.getEmail())
                //.type(entity.getType())
                //.birthdate(entity.getBirthdate())
                .build();
    }

    @Override
    public User fromDTO(UserDto dto) {
        if(dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                //.name(dto.getName())
                //.email(dto.getEmail())
                //.type(dto.getType())
                //.birthdate(dto.getBirthdate())
                .build();
    }

    public User signup(SignupRequestDto dto){
        if(dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                //.name(dto.getName())
                //.email(dto.getEmail())
                //.type(dto.getType())
                //.birthdate(dto.getBirthdate())
                .password(dto.getPassword())
                .build();
    }
}