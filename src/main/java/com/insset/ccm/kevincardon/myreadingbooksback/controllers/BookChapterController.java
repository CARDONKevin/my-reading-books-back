package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.services.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookChapterController {

    private BookChapterService bookChapterService;

    @PostMapping(value="/chapter")
    public ResponseEntity<BookChapter> postBook(@Valid @RequestBody BookChapter bookChapter) {
        return new ResponseEntity<>(bookChapterService.createBookChapter(bookChapter), HttpStatus.CREATED);
    }

    @GetMapping(value="/chapter/book/{bookId}")
    public ResponseEntity<List<BookChapter>> getAllChaptersOfBook(@PathVariable int bookId) {
        return new ResponseEntity<>(bookChapterService.getChaptersOfBook(bookId), HttpStatus.OK);
    }

    @Autowired
    public void setBookChapterService(BookChapterService bookChapterService){
        this.bookChapterService = bookChapterService;
    }
}
