package mj.bookkeeper.service;

import mj.bookkeeper.domain.Book;

/**
 * Interface for getting book by ISBN.
 * @author MJazy
 *
 */
public interface BookByISBNService {

	/**
	 * Method for getting book by ISBN.
	 * @param isbn
	 * @return book relevant for provided parameter.
	 */
	Book getBookByISBN(String isbn);
	
}
