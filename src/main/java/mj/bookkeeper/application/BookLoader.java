package mj.bookkeeper.application;

import java.util.ArrayList;

import mj.bookkeeper.domain.Book;

/**
 * Interface for loading books from source.
 * @author MJazy
 *
 */
public interface BookLoader {

	/**
	 * Method for getting all books from data source.
	 * @return list of Book objects.
	 */
	ArrayList<Book> loadAllBooks();
	
}
