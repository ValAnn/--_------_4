package com.example.demo.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.books.model.BookEntity;
import com.example.demo.books.model.BookGrouped;

public interface BookRepository
        extends CrudRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long> {
    // Optional<BookEntity> findOneByUserIdAndId(long userId, long id);

    List<BookEntity> findByAuthorId(long authorId);

    Page<BookEntity> findByAuthorId(long authorId, Pageable pageable);

    // select
    // tpe.name,
    // coalesce(sum(order.price), 0),
    // coalesce(sum(order.count), 0)
    // from types as tpe
    // left join orders as order on tpe.id = order.type_id and order.user_id = ?
    // group by tpe.name order by tpe.id
    // @Query("select "
    // + "a as author, "
    // + "from AuthorEntity a left join BookEntity o on o.author = a ")
    // // + "group by a book by a.id")
    // List<BookGrouped> getBooksTotalByAuthor(long authorId);
}
