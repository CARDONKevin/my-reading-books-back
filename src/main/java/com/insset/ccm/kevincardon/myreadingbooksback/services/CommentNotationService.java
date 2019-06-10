package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookChapterRepository;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookChapterRepository bookChapterRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public Book createBook(Book book) {
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));

        bookRepository.save(book);
        return book;
    }

    public Book updateBook(int id, Book bookUpdated) {

        Optional<Book> bookRetrieve = bookRepository.findById(id);

        Book bookToUpdate = bookRetrieve.get();

        bookToUpdate.setTitle(bookUpdated.getTitle());
        bookToUpdate.setPicture(bookUpdated.getPicture());
        bookToUpdate.setCategorie(bookUpdated.getCategorie());

        bookRepository.save(bookToUpdate);

        return bookToUpdate;
    }

    public void deleteBook(int id) {
        bookChapterRepository.deleteBookChaptersByBookId(id);
        bookRepository.deleteById(id);
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

    @Autowired
    public void setBookChapterRepository(BookChapterRepository bookChapterRepository) {
        this.bookChapterRepository = bookChapterRepository;
    }

}
