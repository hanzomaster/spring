package com.example.spring.dtos.topic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.Topic}
 */
public record TopicDto(UUID id, Timestamp createdAt, Timestamp updatedAt, String name, String description) implements
        Serializable {
}
