package com.insset.ccm.kevincardon.myreadingbooksback.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "book_sequence";

    @Id
    private long id;

    private String author;
    private String authorMail;
    private String title;
    private String creationDate;
    private String picture;
    private String categorie;

    public Book() {

    }

    public Book(String author, String authorMail, String title, String creationDate, String picture, String categorie) {
        this.author = author;
        this.authorMail = authorMail;
        this.title = title;
        this.creationDate = creationDate;
        this.picture = picture;
        this.categorie = categorie;
    }

    public long getId() {
        return id;
    }

    public Book setId(long id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAuthorMail() {
        return authorMail;
    }

    public Book setAuthorMail(String authorMail) {
        this.authorMail = authorMail;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Book setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Book setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getCategorie() {
        return categorie;
    }

    public Book setCategorie(String categorie) {
        this.categorie = categorie;
        return this;
    }
}
