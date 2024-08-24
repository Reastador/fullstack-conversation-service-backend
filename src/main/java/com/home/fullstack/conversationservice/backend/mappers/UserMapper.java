package com.home.fullstack.conversationservice.backend.mappers;

import com.home.fullstack.conversationservice.backend.dto.SignUpDto;
import com.home.fullstack.conversationservice.backend.dto.UserDto;
import com.home.fullstack.conversationservice.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore=true)
    User signUpToUser(SignUpDto userDto);
}
