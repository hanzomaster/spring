package com.example.spring.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto implements Serializable {
    private String title;
    private String content;
    private Boolean published;
    private UUID userId;
    private Set<UUID> topicIds;
}
