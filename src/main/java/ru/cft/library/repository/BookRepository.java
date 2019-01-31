package ru.cft.library.repository;

import ru.cft.library.entity.Book;

import java.util.List;

public interface BookRepository {

    Book add(Book book);

    Book getById(Long id);

    List<Book> get(String name);

    Book update(Book book);

    void delete(Book book);

}
