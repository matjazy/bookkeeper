package mj.bookkeeper.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import mj.bookkeeper.domain.Book;

/**
 * Interface for getting books by category.
 * @author MJazy
 *
 */
public interface BooksByCategoryService {

	/**
	 * Method for getting books by category.
	 * @param category relevant for books to be returned.
	 * @return response entity with status 200 and list of books having supplied category.
	 */
	ResponseEntity<List<Book>> getBooksByCategory(String category);
	
}
