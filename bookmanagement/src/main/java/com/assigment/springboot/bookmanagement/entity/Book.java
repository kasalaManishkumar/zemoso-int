package com.assigment.springboot.bookmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    @Column(name = "book_name")
    private String bookName;

    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    @Column(name = "genere")
    private String genere;

    @NotNull(message = "This field is required")
    @Column(name = "quantity")
    private int quantity;

    @NotNull(message = "This field is required")
    @Column(name = "price")
    private int price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "book_user", joinColumns =@JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> user;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "bookId")
    private List<Review> review;

    public  Book() {
    }

    public void adjustQuantity(BookUser bookUser){
        quantity=quantity-bookUser.getQuantity();
    }

    public Book(String bookName, String genere, int quantity, int price) {
        this.bookName = bookName;
        this.genere = genere;
        this.quantity = quantity;
        this.price = price;
    }


    public Book(int id, String bookName, String genere, int quantity, int price) {
        this.id = id;
        this.bookName = bookName;
        this.genere = genere;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", genere='" + genere + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
