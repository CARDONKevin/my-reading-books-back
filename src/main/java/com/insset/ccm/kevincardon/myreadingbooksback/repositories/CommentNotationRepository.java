package com.insset.ccm.kevincardon.myreadingbooksback.repositories;

import com.insset.ccm.kevincardon.myreadingbooksback.models.CommentNote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentNotationRepository extends MongoRepository<CommentNote, Integer> {

    public CommentNote findByAuthorMailAndIdBookAndTypeOfBook(String authorMail, String idBook, String typeOfBook);

    public List<CommentNote> findAllByTypeOfBookAndIdBook(String typeOfBook, String idBook);

    public List<CommentNote> findAllByTypeOfBook(String typOfBook);

    public CommentNote findByAuthorNameAndTypeOfBookAndIdBook(String name, String type, String id);

    void deleteByIdBook(String id);

}
