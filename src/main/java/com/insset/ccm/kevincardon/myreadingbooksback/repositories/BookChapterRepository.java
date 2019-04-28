package com.insset.ccm.kevincardon.myreadingbooksback.repositories;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookChapterRepository extends MongoRepository<BookChapter, Integer> {
    public List<BookChapter> findAllByBookId(long bookId);

    public void deleteBookChaptersByBookId(int bookId);

}
