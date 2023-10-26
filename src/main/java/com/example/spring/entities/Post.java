package com.example.spring.entities;


import com.example.spring.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post extends BaseEntity {
    @Column
    @NonNull
    String title;
    @Column(columnDefinition = "text")
    String content;

    boolean published;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @ToString.Exclude
    private Set<Comment> comments = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "Post_topics", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns =
    @JoinColumn(name = "topics_id"))
    private Set<Topic> topics = new LinkedHashSet<>();

}
