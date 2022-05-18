package com.assigment.springboot.bookmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @NotNull(message = "This field is required")
    @Column(name = "comment")
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book bookId;

    private String username;

    public Review(String comment) {
        this.comment = comment;
    }

    public Review(String comment, Book bookId) {
        this.comment = comment;
        this.bookId = bookId;
    }

    public Review(String comment, Book bookId, String username) {
        this.comment = comment;
        this.bookId = bookId;
        this.username = username;
    }

    public Review(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
