package com.example.spring.controllers;

import com.example.spring.dtos.mappers.TopicMapper;
import com.example.spring.dtos.topic.TopicCreateDto;
import com.example.spring.dtos.topic.TopicDto;
import com.example.spring.entities.Topic;
import com.example.spring.models.PaginateParam;
import com.example.spring.repositories.TopicRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/topics")
public class TopicController {
    TopicMapper topicMapper;
    TopicRepository topicRepository;

    @GetMapping
    public Iterable<TopicDto> getAllTopics(PaginateParam param) {
        return topicRepository.findAll(param.toPageRequest()).stream().map(topicMapper::toDto).toList();
    }

    @PostMapping
    public TopicDto createTopic(@RequestBody @Valid TopicCreateDto createDto) {
        return topicMapper.toDto(topicRepository.save(topicMapper.toEntity(createDto)));
    }

    @PatchMapping("/{id}")
    public TopicDto updateTopic(@PathVariable Long id, @RequestBody @Valid TopicCreateDto updateDto) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        return topicMapper.toDto(topicRepository.save(topicMapper.partialUpdate(updateDto, topic)));
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable Long id) {
        topicRepository.deleteById(id);
    }
}
