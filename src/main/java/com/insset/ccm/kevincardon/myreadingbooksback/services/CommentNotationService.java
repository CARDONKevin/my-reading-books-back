package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.CommentNote;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.CommentNotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentNotationService {
    private CommentNotationRepository commentNotationRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public CommentNote createCommentNote(CommentNote commentNote) {
        CommentNote entity = commentNotationRepository.findByAuthorMailAndIdBookAndTypeOfBook(commentNote.getAuthorMail(), commentNote.getIdBook(), commentNote.getTypeOfBook());
        if (entity != null){
            entity.setComment(commentNote.getComment());
            entity.setNotation(commentNote.getNotation());
            commentNotationRepository.save(entity);
            return entity;
        }

        commentNote.setId(sequenceGeneratorService.generateSequence(CommentNote.SEQUENCE_NAME));
        commentNotationRepository.save(commentNote);
        return commentNote;
    }

    public List<CommentNote> getAllByTypeAndIdBook(String type, String idBook){
        return commentNotationRepository.findAllByTypeOfBookAndIdBook(type, idBook);
    }

    public CommentNote getMyComment(String type, String idBook, String name){
        return commentNotationRepository.findByAuthorNameAndTypeOfBookAndIdBook(name, type, idBook);
    }

    public List<CommentNote> getAllByType(String type){
        return commentNotationRepository.findAllByTypeOfBook(type);
    }

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setCommentNotationRepository(CommentNotationRepository commentNotationRepository) {
        this.commentNotationRepository = commentNotationRepository;
    }






}
