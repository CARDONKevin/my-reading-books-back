package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.exceptions.ForbiddenException;
import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtTokenProvider;
import com.insset.ccm.kevincardon.myreadingbooksback.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookController {

    private BookService bookService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public BookController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value="/book")
    public ResponseEntity<Book> postBook(@RequestHeader(value="Authorization") String value, @Valid @RequestBody Book book) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @GetMapping(value="/book")
    public ResponseEntity<List<Book>> getAllBooks(@RequestHeader(value="Authorization") String value) {
        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(value="/book/{bookId}")
    public ResponseEntity<Book> getOneBook(@RequestHeader(value="Authorization") String value, @PathVariable int bookId) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @GetMapping(value="/book/author/{email}")
    public ResponseEntity<List<Book>> getAllBookOfAAuthor(@RequestHeader(value="Authorization") String value, @PathVariable String email) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookService.getAllBookByAuthor(email), HttpStatus.OK);
    }

    @DeleteMapping(value="/book/delete/{id}")
    public ResponseEntity deleteBook(@RequestHeader(value="Authorization") String value, @PathVariable int id) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/book/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestHeader(value="Authorization") String value, @PathVariable int id, @RequestBody Book updatedBook) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.OK);
    }

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
}
