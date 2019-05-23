package mj.bookkeeper.service;

import org.springframework.http.ResponseEntity;

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
	ResponseEntity<String> getBooksByCategory(String category);
	
}
