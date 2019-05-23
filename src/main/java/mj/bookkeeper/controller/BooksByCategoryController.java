package mj.bookkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mj.bookkeeper.service.BooksByCategoryService;

/**
 * Controller for handling requests related to getting books by category.
 * @author MJazy
 *
 */
@RestController
public class BooksByCategoryController {

	@Autowired
	BooksByCategoryService booksByCategoryService;
	
	/**
	 * Case sensitive method for getting books by category.
	 * @param category relevant for books to be returned.
	 * @return response entity with status and body depending on validation of request.
	 */
	@GetMapping("/api/category/{category}/books")
	public ResponseEntity<String> getBooksByCategory(@PathVariable @NonNull String category) {
		return booksByCategoryService.getBooksByCategory(category);
	}
}
