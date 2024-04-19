package com.example.demo.books.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.core.model.BaseEntity;
import com.example.demo.userfavoritebook.UserFavoriteBookEntity;
import com.example.demo.authors.model.AuthorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private AuthorEntity author;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String annotation;
    @Column(nullable = false)
    private String info;
    @Column(nullable = false)
    private Integer number;
    // @OneToMany(mappedBy = "book")
    @OrderBy("id ASC")
    private Set<UserFavoriteBookEntity> userFavoriteBooks = new HashSet<>();

    public BookEntity() {
    }

    public BookEntity(AuthorEntity author, String name, String annotation, String info, Integer number) {
        this.author = author;
        this.name = name;
        this.annotation = annotation;
        this.info = info;
        this.number = number;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<UserFavoriteBookEntity> getUserFavoriteBooks() {
        return userFavoriteBooks;
    }

    public void addUser(UserFavoriteBookEntity userSubscription) {
        // if (userFavoriteBooks.getFavoriteBooks() != this) {
        // userFavoriteBooks.setSubscription(this);
        // }
        userFavoriteBooks.add(userSubscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name, annotation, info, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final BookEntity other = (BookEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getAuthor(), author)
                && Objects.equals(other.getName(), name)
                && Objects.equals(other.getAnnotation(), annotation)
                && Objects.equals(other.getInfo(), info)
                && Objects.equals(other.getNumber(), number);
    }
}
