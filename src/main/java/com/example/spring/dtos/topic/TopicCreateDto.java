package com.example.spring.dtos.topic;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.spring.entities.Topic}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicCreateDto implements Serializable {
    @NotBlank
    private String name;
    private String description;
}
