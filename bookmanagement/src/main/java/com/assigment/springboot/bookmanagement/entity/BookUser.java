package com.assigment.springboot.bookmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "book_user")
public class BookUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message = "This field is required")
    @Column(name = "book_id")
    private int bookId;

    @NotNull(message = "This field is required")
    @Column(name = "user_id")
    private int userId;

    @NotNull(message = "This field is required")
    @Column(name = "quantity")
    private int quantity;



    public BookUser(int bookId, int userId, int quantity) {
        this.bookId = bookId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public BookUser() {
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }
    @Override
    public String toString() {
        return "BookUser{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                '}';
    }
}
