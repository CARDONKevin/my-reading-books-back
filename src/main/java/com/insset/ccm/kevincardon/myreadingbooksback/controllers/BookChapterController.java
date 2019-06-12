package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.exceptions.ForbiddenException;
import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtTokenProvider;
import com.insset.ccm.kevincardon.myreadingbooksback.services.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookChapterController {

    private BookChapterService bookChapterService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public BookChapterController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/chapter")
    public ResponseEntity<BookChapter> postBookChapter(@RequestHeader(value = "Authorization") String value, @Valid @RequestBody BookChapter bookChapter) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")) {
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookChapterService.createBookChapter(bookChapter), HttpStatus.CREATED);
    }

    @GetMapping(value = "/chapter/book/{bookId}")
    public ResponseEntity<List<BookChapter>> getAllChaptersOfBook(@RequestHeader(value = "Authorization") String value, @PathVariable int bookId) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")) {
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookChapterService.getChaptersOfBook(bookId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/chapter/delete/{id}")
    public ResponseEntity deleteChapter(@RequestHeader(value = "Authorization") String value, @PathVariable int id) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")) {
            throw new ForbiddenException();
        }

        bookChapterService.deleteChapter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/chapter/update/{id}")
    public ResponseEntity<BookChapter> updateChapter(@RequestHeader(value = "Authorization") String value, @PathVariable int id, @RequestBody BookChapter chapterUpdated) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")) {
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(bookChapterService.updateBookChapter(id, chapterUpdated), HttpStatus.OK);
    }

    @Autowired
    public void setBookChapterService(BookChapterService bookChapterService) {
        this.bookChapterService = bookChapterService;
    }
}
