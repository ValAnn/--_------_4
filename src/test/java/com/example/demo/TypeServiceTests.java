package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.service.AuthorService;
import com.example.demo.core.error.NotFoundException;

@SpringBootTest
class TypeServiceTests {
    @Autowired
    private AuthorService typeService;

    private AuthorEntity type;

    @BeforeEach
    void createData() {
        removeData();

        type = typeService.create(new AuthorEntity("Ноутбук"));
        typeService.create(new AuthorEntity("Телефон"));
        typeService.create(new AuthorEntity("Игровая приставка"));
    }

    @AfterEach
    void removeData() {
        typeService.getAll().forEach(item -> typeService.delete(item.getId()));
    }

    @Test
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> typeService.get(0L));
    }

    @Test
    void createTest() {
        Assertions.assertEquals(3, typeService.getAll().size());
        Assertions.assertEquals(type, typeService.get(type.getId()));
    }

    @Test
    void createNotUniqueTest() {
        final AuthorEntity nonUniqueType = new AuthorEntity("Ноутбук");
        Assertions.assertThrows(IllegalArgumentException.class, () -> typeService.create(nonUniqueType));
    }

    @Test
    void createNullableTest() {
        final AuthorEntity nullableType = new AuthorEntity(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> typeService.create(nullableType));
    }

    @Test
    void updateTest() {
        final String test = "TEST";
        final String oldName = type.getName();
        final AuthorEntity newEntity = typeService.update(type.getId(), new AuthorEntity(test));
        Assertions.assertEquals(3, typeService.getAll().size());
        Assertions.assertEquals(newEntity, typeService.get(type.getId()));
        Assertions.assertEquals(test, newEntity.getName());
        Assertions.assertNotEquals(oldName, newEntity.getName());
    }

    @Test
    void deleteTest() {
        typeService.delete(type.getId());
        Assertions.assertEquals(2, typeService.getAll().size());

        final AuthorEntity newEntity = typeService.create(new AuthorEntity(type.getName()));
        Assertions.assertEquals(3, typeService.getAll().size());
        Assertions.assertNotEquals(type.getId(), newEntity.getId());
    }
}
