package mj.bookkeeper.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mj.bookkeeper.BookkeeperApplication;
import mj.bookkeeper.application.BookLoaderImpl;
import mj.bookkeeper.domain.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookkeeperApplication.class)
public class BookByISBNServiceImplTests {

	@Autowired
	BookByISBNServiceImpl bookByISBNService;
	
	@Test
	public void testValidation() {
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
		assertEquals(responseEntity, bookByISBNService.getBookByISBN(" "));
	}
	
	@Test	
	public void testBookNotFoundScenario() {
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results founds");
		assertEquals(responseEntity, bookByISBNService.getBookByISBN("1111111111111"));
	}
	
	@Test
	public void testBookFoundScenario() {
		Book relevantBook = prepareExistingBook();
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(relevantBook.toString());
		assertEquals(responseEntity, bookByISBNService.getBookByISBN(relevantBook.getIsbn()));
		
	}
	
	private Book prepareExistingBook() {
		BookLoaderImpl bookLoader = new BookLoaderImpl();
		List<Book> books = bookLoader.loadAllBooks();
		return books.get(0);
	}

}
