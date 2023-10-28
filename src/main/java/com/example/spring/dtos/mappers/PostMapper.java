package com.example.spring.dtos.mappers;

import com.example.spring.dtos.post.PostCreateDto;
import com.example.spring.dtos.post.PostDto;
import com.example.spring.dtos.post.PostUpdateDto;
import com.example.spring.entities.Post;
import com.example.spring.entities.Topic;
import com.example.spring.entities.User;
import com.example.spring.repositories.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TopicMapper.class, UserMapper.class})
public abstract class PostMapper {
    @Autowired
    private UserRepository userRepository;

    public abstract Post toEntity(PostDto postDto);

    public abstract PostDto toDto(Post post);

    @Mapping(source = "author", target = "user", qualifiedByName = "mapUserFromId")
    public abstract Post toEntity(PostCreateDto postCreateDto);

    @Mapping(source = "user.id", target = "author")
    public abstract PostCreateDto toCreateDto(Post post);

    public abstract Post toEntity(PostUpdateDto postUpdateDto);

    @Mapping(target = "topicIds", expression = "java(topicsToTopicIds(post.getTopics()))")
    public abstract PostUpdateDto toUpdateDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Post partialUpdate(PostUpdateDto postUpdateDto, @MappingTarget Post post);

    @Named("topicsToTopicIds")
    Set<UUID> topicsToTopicIds(Set<? extends Topic> topics) {
        return topics.stream().map(Topic::getId).collect(Collectors.toSet());
    }

    @Named("mapUserFromId")
    User mapUserFromId(UUID value) {
        return userRepository.findById(value).orElseThrow();
    }
}
