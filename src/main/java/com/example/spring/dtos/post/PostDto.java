package com.example.spring.dtos.post;

import com.example.spring.dtos.topic.TopicDto;
import com.example.spring.dtos.user.UserDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.spring.entities.Post}
 */
public record PostDto(UUID id, Timestamp createdAt, Timestamp updatedAt, @NotNull String title, String content,
                      boolean published, UserDto user, Set<TopicDto> topics) implements Serializable {
}
