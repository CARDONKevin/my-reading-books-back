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

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
