package com.example.spring.entities.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer()
                .getPersistentClass() : getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
