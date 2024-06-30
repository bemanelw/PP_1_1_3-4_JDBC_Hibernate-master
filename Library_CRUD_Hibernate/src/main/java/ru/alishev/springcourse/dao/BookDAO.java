package ru.alishev.springcourse.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Component
@Transactional
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book", Book.class).list();
    }

    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setPerson(updatedBook.getPerson());
        session.update(book);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    public Person getBookOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book.getPerson();
    }

    public void release(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(null);
        session.update(book);
    }

    public void assign(int id, Person selectedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(selectedPerson);
        session.update(book);
    }
}
