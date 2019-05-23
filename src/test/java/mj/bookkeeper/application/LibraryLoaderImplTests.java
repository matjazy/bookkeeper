package mj.bookkeeper.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mj.bookkeeper.domain.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryLoaderImplTests {

	@Inject
	LibraryLoaderImpl libraryLoaderImpl;
	
	@Test
	public void testSizeOfListByLoadAllBooks(){
		assertEquals(libraryLoaderImpl.loadAllBooks().size(), 40);
	}
	
	@Test
	public void testLoadAllBooksForNullPositions() {
		for (Book book: libraryLoaderImpl.loadAllBooks()) {
			assertNotEquals(null, book);
		}
	}
	
	@Test
	public void testIfAllBooksHaveISBN() {
		for (Book book: libraryLoaderImpl.loadAllBooks()) {
			assertNotEquals(null, book.getIsbn());
		}
	}
	
	@Test
	public void testIfISBN_13IsAssigned() {
		String relevantISBN13 = "9788120602229";
		boolean isRelevantISBN13Present = false;
		for (Book book: libraryLoaderImpl.loadAllBooks()) {
			if (book.getIsbn().equals(relevantISBN13)) {
				isRelevantISBN13Present = true;
			}
		}
		assertTrue(isRelevantISBN13Present);
	}
	
	@Test
	public void testIfBookCreatesCorrectly() {
	
		Book relevantBook = Book.builder()
				.isbn("9788120602229")
				.title("Monumental Java")
				.subtitle(null)
				.publisher("Asian Educational Services")
				.publishedDate(prepareLongDateFormat("1996-12-01"))
				.description("With Illustrations And Vignettes After Drawings Of Javanese Chandi Ornaments.")
				.pageCount(302)
				.thumbnailUrl("http://books.google.com/books/content?id=u67rbsVqB1oC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
				.language("en")
				.previewLink("http://books.google.pl/books?id=u67rbsVqB1oC&printsec=frontcover&dq=java&hl=&cd=9&source=gbs_api")
				.averageRating(0)
				.authors(new String[] {"J.F. Scheltema"})
				.categories(null)
				.build();

		Book loadedBook = new Book();		
		for (Book book: libraryLoaderImpl.loadAllBooks()) {
			if (book.getIsbn().equals("9788120602229")) {
				loadedBook = book;
			}
		}
		assertEquals(relevantBook, loadedBook);
	}
	
	@Test
	public void testIfPartialDateIsCalculatedCorrectly() {
		Book relevantBook = null;
		for (Book book: libraryLoaderImpl.loadAllBooks()) {
			if (book.getIsbn().equals("_-dCAAAAcAAJ")) {
				relevantBook = book;
			}
		}
		assertEquals(prepareLongDateFormat("1817"), relevantBook.getPublishedDate());
	}
	
	private long prepareLongDateFormat(String date) {
		if (date.length() == 4) {
			date = date + "-01-01";
		}
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return LocalDate.parse(date, dateFormatter)
				.atStartOfDay(ZoneOffset.UTC)
				.toInstant()
				.toEpochMilli();
	}
}
