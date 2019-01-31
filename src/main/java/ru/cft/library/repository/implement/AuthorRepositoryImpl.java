package ru.cft.library.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.library.entity.Author;
import ru.cft.library.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author add(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author getById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> get(String name) {
        StringBuilder sb = new StringBuilder("select a from Author a where 1=1");
        if (name != null) {
            sb.append(" and lower(a.name) like '%'||lower(:name)||'%'");
        }

        TypedQuery<Author> query = entityManager.createQuery(sb.toString(), Author.class);
        if (name != null) {
            query.setParameter("name", name);
        }

        return query.getResultList();
    }

}
