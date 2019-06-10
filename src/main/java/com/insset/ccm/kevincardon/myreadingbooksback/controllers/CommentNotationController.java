package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.exceptions.ForbiddenException;
import com.insset.ccm.kevincardon.myreadingbooksback.models.CommentNote;
import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtTokenProvider;
import com.insset.ccm.kevincardon.myreadingbooksback.services.CommentNotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentNotationController {

    private final JwtTokenProvider jwtTokenProvider;
    private CommentNotationService commentNotationService;

    @PostMapping(value="/comment")
    public ResponseEntity<CommentNote> postCommentNote(@RequestHeader(value="Authorization") String value, @Valid @RequestBody CommentNote comment) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(commentNotationService.createCommentNote(comment), HttpStatus.CREATED);
    }

    @GetMapping(value="/comment/{type}")
    public ResponseEntity<List<CommentNote>> getCommentNoteTyped(@RequestHeader(value="Authorization") String value, @PathVariable String type) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(commentNotationService.getAllByType(type), HttpStatus.OK);
    }

    @GetMapping(value="/comment/{type}/{id}")
    public ResponseEntity<List<CommentNote>> getCommentNoteTypedWithId(@RequestHeader(value="Authorization") String value, @PathVariable String type, @PathVariable String id) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(commentNotationService.getAllByTypeAndIdBook(type, id), HttpStatus.OK);
    }

    @GetMapping(value="/comment/{type}/{id}/{name}")
    public ResponseEntity<CommentNote> getMyCommentNoteTypedWithId(@RequestHeader(value="Authorization") String value, @PathVariable String type, @PathVariable String id, @PathVariable String name) {

        if (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(value))) {
            throw new ForbiddenException();
        }

        if (!jwtTokenProvider.getAuthorization(jwtTokenProvider.resolveToken(value)).equals("[{authority=ROLE_CLIENT}]")){
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(commentNotationService.getMyComment(type, id, name), HttpStatus.OK);
    }

    @Autowired
    public CommentNotationController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setCommentNotationService(CommentNotationService commentNotationService) {
        this.commentNotationService = commentNotationService;
    }
}
