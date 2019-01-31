package ru.cft.library.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.library.entity.Author;
import ru.cft.library.entity.Book;
import ru.cft.library.exception.ObjectNotFoundException;
import ru.cft.library.repository.AuthorRepository;
import ru.cft.library.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author add(String name, String description) {
        return authorRepository.add(new Author(name, description));
    }

    @Override
    public Author getById(Long id) throws ObjectNotFoundException {
        Author author = authorRepository.getById(id);
        if (author == null) {
            throw new ObjectNotFoundException(String.format("Author with id %s not found", id));
        }
        return author;
    }

    @Override
    public List<Author> get(String name) {
        return authorRepository.get(name);
    }

    @Override
    public List<Book> getBooks(Long authorId) throws ObjectNotFoundException {
        Author author = authorRepository.getById(authorId);
        if (author == null) {
            throw new ObjectNotFoundException(String.format("Author with id %s not found", authorId));
        }

        return author.getBooks();
    }

}
