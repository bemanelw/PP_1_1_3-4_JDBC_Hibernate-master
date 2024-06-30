package ru.alishev.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "title")
    @NotEmpty(message = "Название не должно быть пустым")
    private String title;
    @NotEmpty(message = "Автор не должен быть пустым")
    @Column(name = "author")
    private String author;
    @Column(name = "publicationYear")
    private int publicationYear;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Book() {
    }

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int id) {
        this.bookId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
