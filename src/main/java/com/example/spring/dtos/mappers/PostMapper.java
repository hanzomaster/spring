package com.example.spring.dtos.mappers;

import com.example.spring.dtos.post.PostCreateDto;
import com.example.spring.dtos.post.PostDto;
import com.example.spring.dtos.post.PostUpdateDto;
import com.example.spring.entities.Post;
import com.example.spring.entities.Topic;
import org.mapstruct.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses
        = TopicMapper.class)
public interface PostMapper {
    Post toEntity(PostDto postDto);

    PostDto toDto(Post post);

    @Mapping(source = "userId", target = "user.id")
    Post toEntity(PostCreateDto postCreateDto);

    @Mapping(source = "user.id", target = "userId")
    PostCreateDto toCreateDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userId", target = "user.id")
    Post partialUpdate(PostCreateDto postCreateDto, @MappingTarget Post post);

    @Mapping(source = "userId", target = "user.id")
    Post toEntity(PostUpdateDto postUpdateDto);

    @Mapping(target = "topicIds", expression = "java(topicsToTopicIds(post.getTopics()))")
    @Mapping(source = "user.id", target = "userId")
    PostUpdateDto toUpdateDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userId", target = "user.id")
    Post partialUpdate(PostUpdateDto postUpdateDto, @MappingTarget Post post);

    default Set<UUID> topicsToTopicIds(Set<? extends Topic> topics) {
        return topics.stream().map(Topic::getId).collect(Collectors.toSet());
    }
}
