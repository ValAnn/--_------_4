package com.example.demo.userfavoritebook;

import java.util.Objects;

import com.example.demo.books.model.BookEntity;

import com.example.demo.users.model.UserEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_favoritebooks")
public class UserFavoriteBookEntity {
    @EmbeddedId
    private UserFavoriteBookId id = new UserFavoriteBookId();
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @MapsId("favoriteBookId")
    @JoinColumn(name = "favoritebook_id")
    private BookEntity favoriteBook;

    public UserFavoriteBookEntity() {
    }

    public UserFavoriteBookEntity(UserEntity user, BookEntity book) {
        this.user = user;
        this.favoriteBook = book;
    }

    public UserFavoriteBookId getId() {
        return id;
    }

    public void setId(UserFavoriteBookId id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        // if (!user.getUserSubscriptions().contains(this)) {
        // user.getUserSubscriptions().add(this);
        // }
    }

    public BookEntity getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(BookEntity book) {
        this.favoriteBook = book;
        if (!user.getFavoriteBooks().contains(this)) {
            // user.getFavoriteBooks().add(book);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), favoriteBook.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserFavoriteBookEntity other = (UserFavoriteBookEntity) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(user.getId(), other.user.getId())
                && Objects.equals(favoriteBook.getId(), other.favoriteBook.getId());
    }

}
