package mj.bookkeeper.application;

import java.util.List;

import mj.bookkeeper.domain.Book;

/**
 * Interface for loading books from source.
 * @author MJazy
 *
 */
public interface LibraryLoader {

	/**
	 * Method for getting all books from data source.
	 * @return list of Book objects.
	 */
	List<Book> loadAllBooks();
	
}
