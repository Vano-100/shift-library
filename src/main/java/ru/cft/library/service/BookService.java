package ru.cft.library.service;

import ru.cft.library.entity.Book;
import ru.cft.library.exception.InvalidArgumentException;
import ru.cft.library.exception.ObjectNotFoundException;

import java.util.List;

public interface BookService {

    Book add(String name, String description, String isbn, Long authorId) throws InvalidArgumentException;

    Book getById(Long id) throws ObjectNotFoundException;

    List<Book> get(String name);

    Book update(Long id, String name, String description, String isbn, Long authorId) throws ObjectNotFoundException, InvalidArgumentException;

    void delete(Long id) throws ObjectNotFoundException;

}
