package com.example.demo.users.api;

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

import com.example.demo.books.api.BookDto;
import com.example.demo.books.model.BookEntity;
import com.example.demo.core.api.PageDto;
import com.example.demo.core.api.PageDtoMapper;
import com.example.demo.core.configuration.Constants;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    private UserDto toDto(UserEntity entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    public BookDto toBookDto(BookEntity entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    private UserEntity toEntity(UserDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    @GetMapping
    public PageDto<UserDto> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        return PageDtoMapper.toDto(userService.getAll(page, size), this::toDto);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable(name = "id") Long id) {
        return toDto(userService.get(id));
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto dto) {
        return toDto(userService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public UserDto update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid UserDto dto) {
        return toDto(userService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable(name = "id") Long id) {
        return toDto(userService.delete(id));
    }

    @GetMapping("/{id}/favoriteBooks")
    public List<BookDto> getUserFavoriteBooks(@PathVariable(name = "id") Long id) {
        return userService.getUserFavoriteBooks(id).stream()
                .map(favoriteBook -> toBookDto(favoriteBook))
                .toList();
    }

    // @PostMapping("/{id}/favoriteBooks")
    // public List<SubscriptionDto> deleteUserFavoriteBook(
    // @PathVariable(name = "id") Long id,
    // @RequestParam(name = "favoriteBooks", defaultValue = "") List<Long> booksIds)
    // {
    // return userService.enableUserSubscriptions(id, booksIds).stream()
    // .map(this::toBookDto)
    // .toList();
    // }

    // @DeleteMapping("/{id}/favoriteBook/all")
    // public List<BookDto> deleteAllUserSubscriptions(@PathVariable(name = "id")
    // Long id) {
    // return userService.deleteAllUserFavoriteBooks(id).stream()
    // .map(this::toBookDto)
    // .toList();
    // }
}
