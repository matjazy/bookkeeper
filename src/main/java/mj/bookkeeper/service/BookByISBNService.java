package mj.bookkeeper.service;

import org.springframework.http.ResponseEntity;

/**
 * Interface for getting book by ISBN.
 * @author MJazy
 *
 */
public interface BookByISBNService {

	/**
	 * Method for getting book by ISBN.
	 * @param isbn
	 * @return ResponseEntity with 200 status and relevant book in the body in case of success or ResponseEntity with 404 status and relevant information in body.
	 */
	ResponseEntity<String> getBookByISBN(String isbn);
	
}
