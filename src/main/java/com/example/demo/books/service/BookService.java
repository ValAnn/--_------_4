package com.example.demo.books.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.books.model.BookEntity;
import com.example.demo.books.model.BookGrouped;
import com.example.demo.books.repository.BookRepository;
import com.example.demo.core.error.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@Service
public class BookService {
    private final BookRepository repository;
    private final UserService userService;

    public BookService(BookRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<BookEntity> getAll(long authorId) {
        userService.get(authorId);
        if (authorId <= 0L) {
            return repository.findByAuthorId(authorId);
        } else {
            // return repository.findByUserIdAndTypeId(userId, typeId);
            return repository.findByAuthorId(authorId);
        }
    }

    @Transactional(readOnly = true)
    public Page<BookEntity> getAll(long authorId, int page, int size) {
        final Pageable pageRequest = PageRequest.of(page, size);
        // //userService.get(userId);
        // if (authorId <= 0L) {
        // return repository.findByAuthorId(pageRequest);
        // }
        return repository.findByAuthorId(authorId, pageRequest);
    }

    @Transactional(readOnly = true)
    public BookEntity get(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BookEntity.class, id));
    }

    @Transactional
    public BookEntity create(BookEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        return repository.save(entity);
    }

    @Transactional
    public BookEntity update(long id, BookEntity entity) {
        final BookEntity existsEntity = get(id);
        existsEntity.setAuthor(entity.getAuthor());
        existsEntity.setName(entity.getName());
        existsEntity.setAnnotation(entity.getAnnotation());
        existsEntity.setInfo(entity.getInfo());
        existsEntity.setNumber(entity.getNumber());
        return repository.save(existsEntity);
    }

    @Transactional
    public BookEntity delete(long id) {
        final BookEntity existsEntity = get(id);
        repository.delete(existsEntity);
        return existsEntity;
    }

    @Transactional(readOnly = true)
    public List<BookEntity> getTotal(long authorId) {

        // return repository.getBooksTotalByAuthor(authorId);
        return repository.findByAuthorId(authorId);
    }
}
