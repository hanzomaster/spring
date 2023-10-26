package com.example.spring.dtos.user;

import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.spring.entities.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto implements Serializable {
    private String username;
    @Past
    private Date dob;
}
