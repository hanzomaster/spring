package com.example.spring.dtos.post;

import com.example.spring.dtos.topic.TopicCreateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto implements Serializable {
    @NotBlank
    private String title;
    private String content;
    private Boolean published;
    private UUID userId;
    private Set<TopicCreateDto> topics = new LinkedHashSet<>();
}
