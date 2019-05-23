package mj.bookkeeper.service;

import java.util.List;

import mj.bookkeeper.domain.Author;

/**
 * Interface for getting all authors.
 * @author MJazy
 *
 */
public interface AuthorsService {

	/**
	 * Method for getting all authors.
	 * @return
	 */
	List<Author> getAllAuthors();
	
}
