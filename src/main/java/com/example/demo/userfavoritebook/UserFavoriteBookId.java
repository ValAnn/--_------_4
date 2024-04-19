package com.example.demo.userfavoritebook;

import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserFavoriteBookId {
    private Long userId;
    private Long favoriteBookId;

    public UserFavoriteBookId() {
    }

    public UserFavoriteBookId(Long userId, Long favoriteBookId) {
        this.userId = userId;
        this.favoriteBookId = favoriteBookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFavoriteBookId() {
        return favoriteBookId;
    }

    public void setFavoriteBookId(Long favoriteBookId) {
        this.favoriteBookId = favoriteBookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Optional.ofNullable(userId).orElse(0L),
                Optional.ofNullable(favoriteBookId).orElse(0L));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserFavoriteBookId other = (UserFavoriteBookId) obj;
        return Objects.equals(userId, other.userId)
                && Objects.equals(favoriteBookId, other.favoriteBookId);
    }

}
