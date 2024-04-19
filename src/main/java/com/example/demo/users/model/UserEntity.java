package com.example.demo.users.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.books.model.BookEntity;
import com.example.demo.core.model.BaseEntity;
import com.example.demo.userfavoritebook.UserFavoriteBookEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String login;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    private Set<UserFavoriteBookEntity> favoriteBooks = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<UserFavoriteBookEntity> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void addFavoriteBook(UserFavoriteBookEntity book) {

        favoriteBooks.add(book);
    }

    public void deleteFavoriteBook(UserFavoriteBookEntity book) {
        favoriteBooks.remove(book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, favoriteBooks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getLogin(), login)
                // && Objects.equals(other.getOrders(), orders)
                && Objects.equals(other.getFavoriteBooks(), favoriteBooks);
    }
}
