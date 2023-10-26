package com.example.spring.dtos.user;

import com.example.spring.dtos.post.PostDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.User}
 */
public record UserDto(UUID id, Timestamp createdAt, Timestamp updatedAt, String username,
                      @Email(message = "Email is not valid") String email, @Past Date dob,
                      Set<PostDto> posts) implements Serializable {
}
