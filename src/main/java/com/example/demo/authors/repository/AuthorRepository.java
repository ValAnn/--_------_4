package com.example.demo.authors.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.authors.model.AuthorEntity;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByNameIgnoreCase(String name);
}
