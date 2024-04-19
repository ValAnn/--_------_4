package com.example.demo.authors.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.repository.AuthorRepository;
import com.example.demo.core.error.NotFoundException;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    private void checkName(String name) {
        if (repository.findByNameIgnoreCase(name).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Type with name %s is already exists", name));
        }
    }

    @Transactional(readOnly = true)
    public List<AuthorEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional(readOnly = true)
    public AuthorEntity get(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(AuthorEntity.class, id));
    }

    @Transactional
    public AuthorEntity create(AuthorEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        checkName(entity.getName());
        return repository.save(entity);
    }

    @Transactional
    public AuthorEntity update(Long id, AuthorEntity entity) {
        final AuthorEntity existsEntity = get(id);
        checkName(entity.getName());
        existsEntity.setName(entity.getName());
        return repository.save(existsEntity);
    }

    @Transactional
    public AuthorEntity delete(Long id) {
        final AuthorEntity existsEntity = get(id);
        repository.delete(existsEntity);
        return existsEntity;
    }
}
