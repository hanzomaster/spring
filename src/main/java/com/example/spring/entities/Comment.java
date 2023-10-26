package com.example.spring.entities;

import com.example.spring.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comment extends BaseEntity {
    String content;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "post_id", nullable = false, unique = true)
    Post post;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    Comment reply;

    @ToString.Exclude
    @OneToMany(mappedBy = "reply", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();
}
