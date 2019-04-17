package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public Book createBook(Book book) {
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));

        bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    public List<Book> getAllBookByAuthor(String email) {
        return bookRepository.findBookByAuthorMail(email);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
}
