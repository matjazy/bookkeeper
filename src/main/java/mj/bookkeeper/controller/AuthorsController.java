package mj.bookkeeper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mj.bookkeeper.domain.Author;
import mj.bookkeeper.service.AuthorsServiceImpl;

@RestController
/**
 * Controller for getting all authors.
 * @author MJazy
 *
 */
public class AuthorsController {

	@Autowired
	private AuthorsServiceImpl authorsServiceImpl;
	
	/**
	 * Method for accessing endpoint with all authors.
	 * @return response entity with status 200 and list of authors sorted by averageRating in descending order.
	 * Authors with no averageRating are treated as if they have lowest averageRating.
	 */
	@GetMapping("/api/rating")
	public ResponseEntity<List<Author>> getAllAuthors(){
		return authorsServiceImpl.getAllAuthors();
	}
	
}
