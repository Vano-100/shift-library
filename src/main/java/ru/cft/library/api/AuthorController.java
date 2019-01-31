package ru.cft.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.cft.library.entity.Author;
import ru.cft.library.entity.Book;
import ru.cft.library.exception.ObjectNotFoundException;
import ru.cft.library.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = Paths.AUTHORS,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Author add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description
    ) {
        return authorService.add(name, description);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = Paths.AUTHORS + Paths.ID,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Author getById(@PathVariable(name = "id") Long id) throws ObjectNotFoundException {
        return authorService.getById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = Paths.AUTHORS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Author> get(@RequestParam(name = "name", required = false) String name) {
        return authorService.get(name);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = Paths.AUTHORS + Paths.ID + Paths.BOOKS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Book> getBooks(@PathVariable(name = "id") Long authorId) throws ObjectNotFoundException {
        return authorService.getBooks(authorId);
    }

}
