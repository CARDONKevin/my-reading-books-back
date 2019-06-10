package com.insset.ccm.kevincardon.myreadingbooksback.repositories;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer> {
    public List<Book> findBookByAuthorMail(String authorMail);

}
