package com.example.spring.dtos.post;

import com.example.spring.dtos.topic.TopicDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.Post}
 */
public record PostDto(UUID id, Timestamp createdAt, Timestamp updatedAt, String title, String content,
                      boolean published, Set<TopicDto> topics) implements Serializable {
}
