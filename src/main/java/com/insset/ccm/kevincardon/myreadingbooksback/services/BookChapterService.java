package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookChapterService {
    private BookChapterRepository bookChapterRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public BookChapter createBookChapter (BookChapter bookChapter){
        bookChapter.setId(sequenceGeneratorService.generateSequence(BookChapter.SEQUENCE_NAME));

        bookChapterRepository.save(bookChapter);
        return bookChapter;
    }

    @Autowired
    public void setBookChapterRepository(BookChapterRepository bookChapterRepository){
        this.bookChapterRepository = bookChapterRepository;
    }

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService){
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
}
