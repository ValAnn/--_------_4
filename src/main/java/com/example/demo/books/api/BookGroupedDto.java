package com.example.demo.books.api;

public class BookGroupedDto {
    private Long authorId;
    private Integer totalNumber;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long typeId) {
        this.authorId = typeId;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
