package mj.bookkeeper.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import mj.bookkeeper.domain.Author;

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
	ResponseEntity<List<Author>> getAllAuthors();
	
}
