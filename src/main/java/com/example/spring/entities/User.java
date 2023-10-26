package com.example.spring.entities;

import com.example.spring.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    String username;
    @Email(message = "Email is not valid")
    @Column(unique = true)
    @NonNull
    String email;
    @Column
    @Length(min = 8)
    @NonNull
    String password;
    @Past Instant dob;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private Set<Post> posts = new LinkedHashSet<>();

}
