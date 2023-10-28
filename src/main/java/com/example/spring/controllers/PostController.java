package com.example.spring.controllers;

import com.example.spring.dtos.mappers.PostMapper;
import com.example.spring.dtos.post.PostCreateDto;
import com.example.spring.dtos.post.PostDto;
import com.example.spring.dtos.post.PostUpdateDto;
import com.example.spring.entities.Post;
import com.example.spring.models.PaginateParam;
import com.example.spring.repositories.PostRepository;
import com.example.spring.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<PostDto> getAllPosts(PaginateParam param) {
        return postRepository.findAll(param.toPageRequest()).stream().map(postMapper::toDto).toList();
    }

    @GetMapping("/byUser/{userId}")
    public Iterable<PostDto> getAllPostsByUserId(@PathVariable UUID userId, PaginateParam param) {
        return postRepository.findAllByUserId(userId, param.toPageRequest()).stream().map(postMapper::toDto).toList();
    }

    @PostMapping
    public PostDto createPost(@RequestBody @Valid PostCreateDto createDto) {
        return postMapper.toDto(postRepository.save(postMapper.toEntity(createDto)));
    }

    @PatchMapping("/{id}")
    public PostDto updatePost(@PathVariable UUID id, @RequestBody @Valid PostUpdateDto updateDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toDto(postRepository.save(postMapper.partialUpdate(updateDto, post)));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable UUID id) {
        postRepository.deleteById(id);
    }
}
