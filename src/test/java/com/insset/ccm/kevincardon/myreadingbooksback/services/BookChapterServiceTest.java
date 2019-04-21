package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookChapterRepository;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookChapterServiceTest {

	@Mock
	private BookChapterRepository bookChapterRepository;

	@Mock
	private SequenceGeneratorService sequenceGeneratorService;

	private BookChapterService bookChapterService;
	private BookChapter bookChapter;

	@Before
	public void setUp() {

		bookChapterService = new BookChapterService();
		bookChapterService.setBookChapterRepository(bookChapterRepository);
		bookChapterService.setSequenceGeneratorService(sequenceGeneratorService);

		bookChapter = new BookChapter()
				.setId(5L)
				.setBookId(1)
				.setContent("My content")
				.setCreationDate(OffsetDateTime.now().toString())
				.setPicture("Picture url")
				.setPicture("Title of chapter");
	}

	@Test
	public void createBookChapter() {
		when(sequenceGeneratorService.generateSequence(anyString())).thenReturn(2L);
		when(bookChapterRepository.save(bookChapter)).thenReturn(bookChapter);

		BookChapter res = bookChapterService.createBookChapter(bookChapter);

		assertThat(res).isEqualTo(bookChapter);
	}

	@Test
	public void getChaptersOfBook() {
		when(bookChapterRepository.findAllByBookId(bookChapter.getBookId())).thenReturn(Collections.singletonList(bookChapter));

		List<BookChapter> res = bookChapterService.getChaptersOfBook(bookChapter.getBookId());

		assertThat(res.get(0)).isEqualTo(bookChapter);
		assertThat(res.size()).isEqualTo(1);
	}


}
