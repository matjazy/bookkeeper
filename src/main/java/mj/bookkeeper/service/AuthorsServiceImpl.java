package mj.bookkeeper.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mj.bookkeeper.application.LibraryLoader;
import mj.bookkeeper.domain.Author;
import mj.bookkeeper.domain.Book;

/**
 * Implementation of AuthorsService interface.
 * @author MJazy
 *
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

	private List<Book> books;
	
	/**
	 * Basic interface for AuthorsServiceImpl class.
	 */
	public AuthorsServiceImpl(LibraryLoader libraryLoader) {
		this.books = libraryLoader.loadAllBooks();
	}
	
	/**
	 * Method allowing to get all authors sorted by their average rating.
	 * In case there was no rating assigned it will show null for particular author.
	 * Nulls are treated as lowest possible rating.
	 * @return response entity with status 200 and list of all authors sorted by their average rating.
	 */
	@Override
	public ResponseEntity<List<Author>> getAllAuthors() {
		return ResponseEntity.status(HttpStatus.OK).body(prepareAllAuthorsList());
	}
	
	private List<Author> prepareAllAuthorsList() {
		Map<String, LinkedList<Double>> authorsMap = prepareAllAuthorsMap();
		List<Author> authorsList = new LinkedList<Author>();
		for (String authorName: authorsMap.keySet()) {
			Author author = new Author(authorName, calculateAverageRating(authorsMap.get(authorName)));
			authorsList.add(author);
		}
		Collections.sort(authorsList, Collections.reverseOrder());
		return authorsList;
	}
	
	private Double calculateAverageRating(LinkedList<Double> authorRatingList) {
		if (authorRatingList.size() < 1) {
			return null;
		}
		Double ratingSum = 0d;
		for (Double rating: authorRatingList) {
			ratingSum += rating;
		}
		if (ratingSum == 0d) {
			return null;
		}
		return ratingSum/authorRatingList.size();
	}
	
	private Map<String, LinkedList<Double>> prepareAllAuthorsMap() {
		Map<String, LinkedList<Double>> authorsMap = new HashMap<>();
		for (Book book: books) {
			iterateOverBookAuthors(authorsMap, book);
		}
		return authorsMap;
	}
	
	private void iterateOverBookAuthors(Map <String, LinkedList<Double>> authorsMap, Book book) {
		String[] authors = book.getAuthors();
		if (authors != null) {
			for (int index = 0; index < authors.length; index++) {
				LinkedList<Double> authorRatingList;
				if (!authorsMap.containsKey(authors[index])) {
					authorRatingList = new LinkedList<Double>();
					authorRatingList.add(book.getAverageRating());
					authorsMap.put(authors[index], authorRatingList);
					continue;
				}
				if(book.getAverageRating() != 0) {
					authorRatingList = authorsMap.get(authors[index]);
					authorRatingList.add(book.getAverageRating());
					authorsMap.put(authors[index], authorRatingList);						
				}
			}	
		}		
	}
}
