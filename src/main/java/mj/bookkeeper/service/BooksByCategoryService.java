package mj.bookkeeper.service;

import java.util.List;

import mj.bookkeeper.domain.Book;

/**
 * Interface for getting books by category.
 * @author MJazy
 *
 */
public interface BooksByCategoryService {

	List<Book> getBooksByCategory(String category);
	
}
