package ru.cft.library.service;

import ru.cft.library.entity.Author;
import ru.cft.library.entity.Book;
import ru.cft.library.exception.ObjectNotFoundException;

import java.util.List;

public interface AuthorService {

    Author add(String name, String description);

    Author getById(Long id) throws ObjectNotFoundException;

    List<Author> get(String name);

    List<Book> getBooks(Long authorId) throws ObjectNotFoundException;

}
