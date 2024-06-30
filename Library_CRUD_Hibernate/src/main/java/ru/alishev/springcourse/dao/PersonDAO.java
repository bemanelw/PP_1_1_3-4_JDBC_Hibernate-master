package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
@Transactional
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class).list();
    }

    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setFullName(updatedPerson.getFullName());
        person.setBirthYear(updatedPerson.getBirthYear());
        session.update(person);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }

    public Person getPersonByFullName(String fullName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person where fullName = :fullName", Person.class);
        query.setParameter("fullName", fullName);
        return query.uniqueResult();
    }

    public List<Book> getBooksByPersonId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person.getBooks();
    }
}
