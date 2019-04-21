package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.models.Book;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private SequenceGeneratorService sequenceGeneratorService;

	private BookService bookService;
	private Book book;

	@Before
	public void setUp() {

		bookService = new BookService();
		bookService.setBookRepository(bookRepository);
		bookService.setSequenceGeneratorService(sequenceGeneratorService);

		book = new Book()
				.setId(1L)
				.setAuthor("author")
				.setAuthorMail("test@test.fr")
				.setCategorie("categ")
				.setCreationDate(OffsetDateTime.now().toString())
				.setPicture("pictureURL")
				.setTitle("titleBook");
	}

	@Test
	public void createBook() {
		when(sequenceGeneratorService.generateSequence(anyString())).thenReturn(1L);
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		Book res = bookService.createBook(book);

		assertThat(res).isEqualTo(book);
	}

	@Test
	public void getBook() {
		when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.of(book));

		Book res = bookService.getBook((int) book.getId());

		assertThat(res).isEqualTo(book);
	}

	@Test
	public void getAllBooks() {
		when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

		List<Book> res = bookService.getAllBooks();

		assertThat(res.get(0)).isEqualTo(book);
		assertThat(res.size()).isEqualTo(1);
	}

	@Test
	public void getAllBooksEmpty() {
		when(bookRepository.findAll()).thenReturn(Collections.emptyList());

		List<Book> res = bookService.getAllBooks();

		assertThat(res.size()).isEqualTo(0);
	}

	@Test
	public void getAllBookByAuthor() {
		when(bookRepository.findBookByAuthorMail(anyString())).thenReturn(Collections.singletonList(book));

		List<Book> res = bookService.getAllBookByAuthor(book.getAuthorMail());

		assertThat(res.get(0)).isEqualTo(book);
		assertThat(res.size()).isEqualTo(1);
	}

	@Test
	public void getAllBookByAuthorEmpty() {
		when(bookRepository.findBookByAuthorMail(anyString())).thenReturn(Collections.emptyList());

		List<Book> res = bookService.getAllBookByAuthor(book.getAuthorMail());

		assertThat(res.size()).isEqualTo(0);
	}


}
