package com.insset.ccm.kevincardon.myreadingbooksback.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_chapter")
public class BookChapter {

    @Transient
    public static final String SEQUENCE_NAME = "chapter_book_sequence";

    @Id
    private long id;

    private String title;
    private String creationDate;
    private String picture;
    private String content;
    private int bookId;

    public BookChapter(){

    }

    public BookChapter(String title, String creationDate, String picture, String content, int bookId) {
        this.title = title;
        this.creationDate = creationDate;
        this.picture = picture;
        this.content = content;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public BookChapter setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookChapter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public BookChapter setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public BookChapter setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BookChapter setContent(String content) {
        this.content = content;
        return this;
    }

    public int getBookId() {
        return bookId;
    }

    public BookChapter setBookId(int bookId) {
        this.bookId = bookId;
        return this;
    }
}
