package mj.bookkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mj.bookkeeper.domain.Book;
import mj.bookkeeper.service.BookByISBNService;

/**
 * Controller for handling requests related to accessing book by ISBN.
 * @author MJazy
 *
 */
@RestController
public class BookByISBNController {

	@Autowired
	BookByISBNService bookByISBNService;
	
	/**
	 * Method allowing getting book with ISBN.
	 * @param relevant ISBN number.
	 * @return response entity with status and body depending on request's success.
	 */
	@GetMapping("/api/book/{isbn}")
	ResponseEntity<Book> getBookByISBN(@PathVariable @NonNull String isbn){
		return bookByISBNService.getBookByISBN(isbn);
	}
}
