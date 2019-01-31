package ru.cft.library.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.library.entity.Book;
import ru.cft.library.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book add(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> get(String name) {
        StringBuilder sb = new StringBuilder("select b from Book b where 1=1");
        if (name != null) {
            sb.append(" and lower(b.name) like '%'||lower(:name)||'%'");
        }

        TypedQuery<Book> query = entityManager.createQuery(sb.toString(), Book.class);
        if (name != null) {
            query.setParameter("name", name);
        }

        return query.getResultList();
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public void delete(Book book)  {
        entityManager.remove(book);
    }

}
