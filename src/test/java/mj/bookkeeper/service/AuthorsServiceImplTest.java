package mj.bookkeeper.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mj.bookkeeper.domain.Author;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorsServiceImplTest {

	@Autowired
	AuthorsServiceImpl authorsServiceImpl;
		
	@Test
	public void testResponseEntityStatus() {
		ResponseEntity<List<Author>> responseEntity = authorsServiceImpl.getAllAuthors();
		assertEquals(responseEntity.getStatusCodeValue(), 200);
	}

	@Test
	public void testNoRatingForAuthorScenario() {
			List<Author> responseBody = authorsServiceImpl.getAllAuthors().getBody();
			Author lastAuthor = responseBody.get(responseBody.size()-1);
			assertEquals(lastAuthor.getAverageRating(), null);
	}
	
	@Test
	public void testRatingCalculation() {
		List<Author> responseBody = authorsServiceImpl.getAllAuthors().getBody();
		Author firstAuthor = responseBody.get(0);
		assertEquals(firstAuthor.getAverageRating(), Double.valueOf(5.0));
	}	
}
