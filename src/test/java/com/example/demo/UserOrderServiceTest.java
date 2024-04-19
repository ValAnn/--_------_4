package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.service.AuthorService;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;

import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

import jakarta.persistence.EntityManager;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserOrderServiceTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AuthorService typeService;

    @Autowired
    private AuthorService orderService;
    @Autowired
    private UserService userService;

    private AuthorEntity type1;
    private AuthorEntity type2;
    private AuthorEntity type3;

    private BookEntity subscription;

    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    void createData() {
        removeData();

        type1 = typeService.create(new AuthorEntity("Ноутбук"));
        type2 = typeService.create(new AuthorEntity("Телефон"));
        type3 = typeService.create(new AuthorEntity("Игровая приставка"));

        // subscription = subscriptionService.create(new BookEntity("Подписка 1"));
        // subscriptionService.create(new BookEntity("Подписка 2"));
        // subscriptionService.create(new BookEntity("Подписка 3"));

        user1 = userService.create(new UserEntity("user1"));
        user2 = userService.create(new UserEntity("user2"));

        // final var orders = List.of(
        // new BookEntity(type1, 49999.00, 20),
        // new BookEntity(type1, 129999.00, 3),
        // new BookEntity(type2, 15450.50, 30),
        // new BookEntity(type2, 69900.50, 10),
        // new BookEntity(type2, 150000.00, 6),
        // new BookEntity(type3, 75000.00, 6),
        // new BookEntity(type3, 67800.00, 3));
        // orders.forEach(order -> orderService.create(user1.getId(), order));
    }

    @AfterEach
    void removeData() {
        userService.getAll().forEach(item -> userService.delete(item.getId()));
        typeService.getAll().forEach(item -> typeService.delete(item.getId()));
        // subscriptionService.getAll().forEach(item ->
        // subscriptionService.delete(item.getId()));
    }

    // @Test
    // @Order(1)
    // void createTest() {
    // Assertions.assertEquals(7, orderService.getAll(user1.getId(), 0).size());
    // Assertions.assertEquals(0, orderService.getAll(user2.getId(), 0).size());
    // }

    // @Test
    // @Order(2)
    // void orderFilterTest() {
    // Assertions.assertEquals(2, orderService.getAll(user1.getId(),
    // type1.getId()).size());
    // Assertions.assertEquals(3, orderService.getAll(user1.getId(),
    // type2.getId()).size());
    // Assertions.assertEquals(2, orderService.getAll(user1.getId(),
    // type3.getId()).size());
    // }

    // @Test
    // @Order(3)
    // void subscriptionsTest() {
    // Assertions.assertEquals(3,
    // userService.getUserSubscriptions(user1.getId()).size());
    // }

    // @Test
    // @Order(4)
    // void subscriptionDisableTest() {
    // userService.disableUserSubscriptions(user1.getId(),
    // List.of(subscription.getId()));
    // Assertions.assertEquals(subscriptionService.getAll().size() - 1,
    // userService.getUserSubscriptions(user1.getId()).size());
    // }

    // @Test
    // @Order(5)
    // void subscriptionEnableTest() {
    // userService.enableUserSubscriptions(user1.getId(),
    // List.of(subscription.getId()));
    // Assertions.assertEquals(subscriptionService.getAll().size(),
    // userService.getUserSubscriptions(user1.getId()).size());
    // }

    // @Test
    // @Order(6)
    // void subscriptionsDeleteTest() {
    // userService.deleteAllUserSubscriptions(user1.getId());
    // Assertions.assertTrue(userService.getUserSubscriptions(user1.getId()).isEmpty());
    // }

    // @Test
    // @Order(6)
    // void userCascadeDeleteTest() {
    // userService.delete(user1.getId());
    // final var orders = entityManager.createQuery(
    // "select count(o) from OrderEntity o where o.user.id = :userId");
    // orders.setParameter("userId", user1.getId());
    // Assertions.assertEquals(0, orders.getFirstResult());
    // final var subscriptions = entityManager.createQuery(
    // "select count(us) from UserSubscriptionEntity us where us.user.id =
    // :userId");
    // subscriptions.setParameter("userId", user1.getId());
    // Assertions.assertEquals(0, subscriptions.getFirstResult());

    // }
}
