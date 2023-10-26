package com.example.spring.entities;

import com.example.spring.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Topic extends BaseEntity {
    @NonNull
    String name;
    String description;

    @ManyToMany(mappedBy = "topics")
    @ToString.Exclude
    private Set<Post> posts = new LinkedHashSet<>();

}
