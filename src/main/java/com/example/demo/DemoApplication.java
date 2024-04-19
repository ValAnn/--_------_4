package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.service.AuthorService;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;

import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    private final AuthorService authorService;
    private final UserService userService;
    private final BookService bookService;

    public DemoApplication(
            AuthorService authorService,

            UserService userService,
            BookService bookService) {
        this.authorService = authorService;
        // this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && Arrays.asList(args).contains("--populate")) {
            log.info("Create default authors values");
            final var type1 = authorService.create(new AuthorEntity("Ноутбук"));
            final var type2 = authorService.create(new AuthorEntity("Телефон"));
            final var type3 = authorService.create(new AuthorEntity("Игровая приставка"));

            log.info("Create default user values");
            final var user1 = userService.create(new UserEntity("user1"));
            userService.create(new UserEntity("user2"));

            log.info("Create default order values");
            final var books = List.of(
                    new BookEntity(type1, "name1", "ann", "info", 1),
                    new BookEntity(type1, "name2", "ann", "info", 1),
                    new BookEntity(type1, "name3", "ann", "info", 1),
                    new BookEntity(type1, "name4", "ann", "info", 1));
            books.forEach(order -> bookService.create(order));
        }
    }
}
