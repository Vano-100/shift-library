package ru.cft.library.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.library.entity.Author;
import ru.cft.library.entity.Book;
import ru.cft.library.exception.InvalidArgumentException;
import ru.cft.library.exception.ObjectNotFoundException;
import ru.cft.library.repository.AuthorRepository;
import ru.cft.library.repository.BookRepository;
import ru.cft.library.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book add(String name, String description, String isbn, Long authorId) throws InvalidArgumentException {
        Author author = authorRepository.getById(authorId);
        if (author == null) {
            throw new InvalidArgumentException(
                    String.format("Invalid argument authorId - author with id %s not found", authorId)
            );
        }

        return bookRepository.add(new Book(name, description, isbn, author));
    }

    @Override
    public Book getById(Long id) throws ObjectNotFoundException {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new ObjectNotFoundException(String.format("Book with id %s not found", id));
        }

        return book;
    }

    @Override
    public List<Book> get(String name) {
        return bookRepository.get(name);
    }

    @Override
    @Transactional
    public Book update(Long id, String name, String description, String isbn, Long authorId) throws ObjectNotFoundException, InvalidArgumentException {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new ObjectNotFoundException(String.format("Book with id %s not found", id));
        }

        if (!authorId.equals(book.getAuthor().getId())) {
            Author author = authorRepository.getById(authorId);
            if (author == null) {
                throw new InvalidArgumentException(
                        String.format("Invalid argument authorId - author with id %s not found", authorId)
                );
            }

            book.setAuthor(author);
        }

        book.setName(name);
        book.setDescription(description);
        book.setIsbn(isbn);

        return bookRepository.update(book);
    }

    @Override
    @Transactional
    public void delete(Long id) throws ObjectNotFoundException {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new ObjectNotFoundException(String.format("Book with id %s not found", id));
        }

        bookRepository.delete(book);
    }

}
