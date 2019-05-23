package mj.bookkeeper.service;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mj.bookkeeper.application.LibraryLoader;
import mj.bookkeeper.domain.Book;

/**
 * Implementation of BookByISBNService interface.
 * @author MJazy
 *
 */
@Service
public class BookByISBNServiceImpl implements BookByISBNService {

	@Inject
	private LibraryLoader libraryLoader;
	
	@Override
	public ResponseEntity<Book> getBookByISBN(String isbn) {
		if (!validateInput(isbn)) {
			return ResponseEntity.notFound().build();
		}
		Book book = prepareBookByISBN(isbn);
		if (book == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	
	private boolean validateInput(String isbn) {
		if (isbn == null) {
			return false;
		}
		if (isbn.isBlank()) {
			return false;
		}
		if (isbn.contains(" ")) {
			return false;
		}
		if (isbn.length() < 10) {
			return false;
		}
		return true;
	}
	
	private Book prepareBookByISBN(String isbn) {
		Book loadedBook = null;
		for (Book book: libraryLoader.loadAllBooks()) {
			if (book.getIsbn().equals(isbn)) {
				loadedBook = book;
			}
		}
		return loadedBook;
	}

}
