package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.BookChapter;
import com.insset.ccm.kevincardon.myreadingbooksback.repositories.BookChapterRepository;
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
import static org.mockito.ArgumentMatchers.*;
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
	private BookChapter updatedBookChapter;

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
				.setTitle("Title of chapter");

		updatedBookChapter = new BookChapter()
				.setId(5L)
				.setBookId(1)
				.setContent("update")
				.setCreationDate(OffsetDateTime.now().toString())
				.setPicture("update")
				.setTitle("title updated");
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

	@Test
	public void updateChapter() {
		when(bookChapterRepository.findById(anyInt())).thenReturn(Optional.of(updatedBookChapter));
		when(bookChapterRepository.save(any(BookChapter.class))).thenReturn(bookChapter);
		BookChapter res = bookChapterService.updateBookChapter(1, updatedBookChapter);

		assertThat(res).isEqualTo(updatedBookChapter);
	}


}
