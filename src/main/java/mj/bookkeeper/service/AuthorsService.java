package mj.bookkeeper.service;

import org.springframework.http.ResponseEntity;

/**
 * Interface for getting all authors.
 * @author MJazy
 *
 */
public interface AuthorsService {

	/**
	 * Method for getting all authors.
	 * @return response entity with status 200 and all authors.
	 */
	ResponseEntity<String> getAllAuthors();
	
}
