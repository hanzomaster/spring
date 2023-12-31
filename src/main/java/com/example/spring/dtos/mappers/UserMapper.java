package com.example.spring.dtos.mappers;

import com.example.spring.dtos.user.UserCreateDto;
import com.example.spring.dtos.user.UserDto;
import com.example.spring.dtos.user.UserUpdateDto;
import com.example.spring.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    User toEntity(UserCreateDto userCreateDto);

    UserCreateDto toCreateDto(User user);

    User toEntity(UserUpdateDto userUpdateDto);

    UserUpdateDto toUpdateDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
