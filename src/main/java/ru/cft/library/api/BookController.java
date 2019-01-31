package ru.cft.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.cft.library.entity.Book;
import ru.cft.library.exception.InvalidArgumentException;
import ru.cft.library.exception.ObjectNotFoundException;
import ru.cft.library.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = Paths.BOOKS,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Book add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "isbn") String isbn,
            @RequestParam(name = "authorId") Long authorId
    ) throws InvalidArgumentException {
        return bookService.add(name, description, isbn, authorId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = Paths.BOOKS + Paths.ID,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Book getById(@PathVariable(name = "id") Long id) throws ObjectNotFoundException {
        return bookService.getById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = Paths.BOOKS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Book> get(@RequestParam(name = "name", required = false) String name) {
        return bookService.get(name);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = Paths.BOOKS + Paths.ID,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Book update(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "isbn") String isbn,
            @RequestParam(name = "authorId") Long authorId
    ) throws ObjectNotFoundException, InvalidArgumentException {
        return bookService.update(id, name, description, isbn, authorId);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = Paths.BOOKS + Paths.ID
    )
    public void delete(@PathVariable(name = "id") Long id) throws ObjectNotFoundException {
        bookService.delete(id);
    }

}
