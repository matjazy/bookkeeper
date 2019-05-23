package mj.bookkeeper.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mj.bookkeeper.application.BookLoader;
import mj.bookkeeper.domain.Book;

/**
 * Implementation of BooksByCategoryService.
 * @author MJazy
 *
 */
@Service
public class BooksByCategoryServiceImpl implements BooksByCategoryService {

	private List<Book> books;
	
	/**
	 * Basic constructor for BooksByCategoryServiceImpl class.
	 */
	public BooksByCategoryServiceImpl(BookLoader bookLoader) {
		this.books = bookLoader.loadAllBooks();
	}
	
	/**
	 * Case sensitive method for getting books by category.
	 * @param category relevant for books to be returned.
	 * @return response entity with status 200 and list of books having supplied category.
	 */
	@Override
	public ResponseEntity<String> getBooksByCategory(String category) {
		return ResponseEntity.status(HttpStatus.OK).body(getBooksByCategoryList(category).toString());
	}

	private List<Book> getBooksByCategoryList(String category){
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
