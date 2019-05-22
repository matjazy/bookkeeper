package mj.bookkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mj.bookkeeper.application.BookLoader;
import mj.bookkeeper.domain.Book;

@Service
public class BookByISBNServiceImpl implements BookByISBNService {

	@Autowired
	private BookLoader bookLoader;
	
	@Override
	public ResponseEntity<String> getBookByISBN(String isbn) {
		if (!validateInput(isbn)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
		}
		Book book = prepareBookByISBN(isbn);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results founds");
		}
		return ResponseEntity.status(HttpStatus.OK).body(book.toString());
	}
	
	private boolean validateInput(String isbn) {
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
		for (Book book: bookLoader.loadAllBooks()) {
			if (book.getIsbn().equals(isbn)) {
				loadedBook = book;
			}
		}
		return loadedBook;
	}

}
