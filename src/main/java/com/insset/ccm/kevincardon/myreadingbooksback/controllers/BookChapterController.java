package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.services.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookChapterController {

    private BookChapterService bookChapterService;

    @PostMapping(value="/chapter")
    public ResponseEntity<BookChapter> postBook(@Valid @RequestBody BookChapter bookChapter) {
        return new ResponseEntity<>(bookChapterService.createBookChapter(bookChapter), HttpStatus.CREATED);
    }

    @Autowired
    public void setBookChapterService(BookChapterService bookChapterService){
        this.bookChapterService = bookChapterService;
    }
}
