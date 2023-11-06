package com.example.spring.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.User}
 */
public record UserDto(UUID id, Timestamp createdAt, Timestamp updatedAt, String username,
                      @Email(message = "Email is not valid") String email, @Past Date dob, Point point) implements
        Serializable {
}
