package com.example.demo.books.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authors.service.AuthorService;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.model.BookGrouped;
import com.example.demo.books.service.BookService;
import com.example.demo.core.api.PageDto;
import com.example.demo.core.api.PageDtoMapper;
import com.example.demo.core.configuration.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, AuthorService authorService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    private BookDto toDto(BookEntity entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    private BookEntity toEntity(BookDto dto) {
        final BookEntity entity = modelMapper.map(dto, BookEntity.class);
        entity.setAuthor(authorService.get(dto.getAuthorId()));
        return entity;
    }

    private BookGroupedDto toGroupedDto(BookGrouped entity) {
        return modelMapper.map(entity, BookGroupedDto.class);
    }

    @GetMapping
    public PageDto<BookDto> getAll(
            @RequestParam(name = "authorId", defaultValue = "0") Long authorId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        return PageDtoMapper.toDto(bookService.getAll(authorId, page, size), this::toDto);
    }

    @GetMapping("/{id}")
    public BookDto get(
            @PathVariable(name = "id") Long id) {
        return toDto(bookService.get(id));
    }

    @PostMapping
    public BookDto create(
            @RequestBody @Valid BookDto dto) {
        return toDto(bookService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public BookDto update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid BookDto dto) {
        return toDto(bookService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public BookDto delete(
            @PathVariable(name = "id") Long id) {
        return toDto(bookService.delete(id));
    }

    // @GetMapping("/total")
    // public List<BookGroupedDto> getMethodName(@PathVariable(name = "authorId")
    // Long authorId) {
    // return bookService.getTotal(authorId).stream()
    // .map(this::toGroupedDto)
    // .toList();
    // }

}
