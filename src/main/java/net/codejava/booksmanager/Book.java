package net.codejava.booksmanager;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Book {
    private Integer bookId ;
    private String title ;
    private String author ;
    private float price ;

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    public Integer getBookId() {
        return bookId;
    }
    @Column(name="auteur")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String effectiveJava) {
        this.title=title;
    }

    public void setPrice(float v) {
        this.price = v;
    }
}
