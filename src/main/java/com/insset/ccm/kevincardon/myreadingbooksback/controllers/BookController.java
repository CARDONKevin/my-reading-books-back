package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import com.insset.ccm.kevincardon.myreadingbooksback.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @PostMapping(value="/book")
    public ResponseEntity<Book> postBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @GetMapping(value="/book")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(value="/book/{bookId}")
    public ResponseEntity<Book> getOneBook(@PathVariable int bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
}
