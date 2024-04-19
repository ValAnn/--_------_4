package com.example.demo.authors.model;

import java.util.Objects;

import com.example.demo.core.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    public AuthorEntity() {
    }

    public AuthorEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final AuthorEntity other = (AuthorEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getName(), name);
    }

}
