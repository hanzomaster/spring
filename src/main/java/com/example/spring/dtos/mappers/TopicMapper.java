package com.example.spring.dtos.mappers;

import com.example.spring.dtos.topic.TopicCreateDto;
import com.example.spring.dtos.topic.TopicDto;
import com.example.spring.entities.Topic;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {
    Topic toEntity(TopicDto topicDto);

    TopicDto toDto(Topic topic);

    Topic toEntity(TopicCreateDto topicCreateDto);

    TopicCreateDto toCreateDto(Topic topic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Topic partialUpdate(TopicCreateDto topicCreateDto, @MappingTarget Topic topic);
}
