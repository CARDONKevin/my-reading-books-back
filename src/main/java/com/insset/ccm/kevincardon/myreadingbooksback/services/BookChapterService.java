package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookChapterService {
    private BookChapterRepository bookChapterRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public BookChapter createBookChapter (BookChapter bookChapter){
        bookChapter.setId(sequenceGeneratorService.generateSequence(BookChapter.SEQUENCE_NAME));

        bookChapterRepository.save(bookChapter);
        return bookChapter;
    }

    public List<BookChapter> getChaptersOfBook(int bookId){
        return bookChapterRepository.findAllByBookId(bookId);
    }

    public void deleteChapter(int chapterId){
        bookChapterRepository.deleteById(chapterId);
    }

    public BookChapter updateBookChapter(int chapterId, BookChapter updatedBookChapter){
        Optional<BookChapter> bookChapter = bookChapterRepository.findById(chapterId);

        BookChapter bookChapterToUpdate = bookChapter.get();

        bookChapterToUpdate.setPicture(updatedBookChapter.getPicture());
        bookChapterToUpdate.setContent(updatedBookChapter.getContent());
        bookChapterToUpdate.setTitle(updatedBookChapter.getTitle());

        bookChapterRepository.save(bookChapterToUpdate);
        return bookChapterToUpdate;
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
