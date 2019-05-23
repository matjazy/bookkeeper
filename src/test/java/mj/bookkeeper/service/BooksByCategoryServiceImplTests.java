package mj.bookkeeper.service;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
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
public class BooksByCategoryServiceImplTests {

	@Autowired
	BooksByCategoryServiceImpl booksByCategoryServiceImpl;
	
	@Autowired
	BookLoaderImpl bookLoaderImpl;
	
	@Test
	public void testResponse() {
		List<Book> relevantBooksByCategory = prepareBooksByCategoryList("Computers");
		System.out.println(relevantBooksByCategory);
		ResponseEntity<String> relevantResponse = ResponseEntity.status(HttpStatus.OK).body(relevantBooksByCategory.toString());
		assertEquals(relevantResponse, booksByCategoryServiceImpl.getBooksByCategory("Computers"));
	}
	
	private List<Book>prepareBooksByCategoryList(String category) {
		List<Book> books = bookLoaderImpl.loadAllBooks();
		List<Book> booksByCategory = new LinkedList<Book>(); 
		for (Book book: books) {
			String[] categories = book.getCategories();
			if (categories != null) {
				for (int index = 0; index < categories.length; index++) {
					if (categories[index].equals(category)) {
						booksByCategory.add(book);
						}
					}
				}
			}
		return booksByCategory;
	}
	
}