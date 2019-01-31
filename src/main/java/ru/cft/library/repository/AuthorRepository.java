package ru.cft.library.repository;

import ru.cft.library.entity.Author;

import java.util.List;

public interface AuthorRepository {

    Author add(Author author);

    Author getById(Long id);

    List<Author> get(String name);

}
