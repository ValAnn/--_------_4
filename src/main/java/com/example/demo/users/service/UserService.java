package com.example.demo.users.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.userfavoritebook.UserFavoriteBookEntity;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;

import com.example.demo.users.model.UserEntity;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    private final BookService bookService;

    public UserService(
            UserRepository repository,
            BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    private void checkLogin(String login) {
        if (repository.findByLoginIgnoreCase(login).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("User with login %s is already exists", login));
        }
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional(readOnly = true)
    public Page<UserEntity> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public UserEntity get(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        checkLogin(entity.getLogin());
        repository.save(entity);
        return repository.save(entity);
    }

    @Transactional
    public UserEntity update(long id, UserEntity entity) {
        final UserEntity existsEntity = get(id);
        checkLogin(entity.getLogin());
        existsEntity.setLogin(entity.getLogin());
        repository.save(existsEntity);
        return existsEntity;
    }

    @Transactional
    public UserEntity delete(long id) {
        final UserEntity existsEntity = get(id);
        repository.delete(existsEntity);
        return existsEntity;
    }

    @Transactional(readOnly = true)
    public List<BookEntity> getUserFavoriteBooks(long id) {
        return get(id).getFavoriteBooks().stream()

                .map(UserFavoriteBookEntity::getFavoriteBook)
                .toList();
    }

    // private List<BookEntity> changeUserSubscriptionsState(
    // UserEntity existsUser,
    // List<Long> subscriptionsIds,
    // boolean state) {
    // return existsUser.getFavoriteBooks().stream()
    // .filter(subscription -> Objects.nonNull(subscription.getSubscription()))
    // .filter(subscription ->
    // subscriptionsIds.contains(subscription.getSubscription().getId()))
    // .filter(subscription -> subscription.isActive() == !state)
    // .map(subscription -> {
    // subscription.setActive(state);
    // return subscription.getSubscription();
    // })
    // .toList();
    // }

    // @Transactional
    // public List<BookEntity> addFavoriteBooks(long id, List<Long> booksIds) {
    // final UserEntity existsUser = get(id);
    // List<BookEntity> favoriteBooks = booksIds.stream().map(book ->
    // bookService.get(book)).toList();

    // booksIds.forEach(book -> existsUser.addFavoriteBook(bookService.get(book)));
    // repository.save(existsUser);
    // return favoriteBooks;
    // }

    // @Transactional
    // public List<BookEntity> disableUserSubscriptions(long id, List<Long>
    // subscriptionsIds) {
    // final UserEntity existsUser = get(id);
    // final List<BookEntity> changedSubscriptions = changeUserSubscriptionsState(
    // existsUser, subscriptionsIds, false);
    // repository.save(existsUser);
    // return changedSubscriptions;
    // }

    // @Transactional
    // public List<BookEntity> deleteAllUserFavoriteBooks(long id) {
    // final UserEntity existsUser = get(id);
    // final List<BookEntity> subscriptions = existsUser.getFavoriteBooks().stream()

    // .toList();
    // repository.save(existsUser);
    // return subscriptions;
    // }
}
