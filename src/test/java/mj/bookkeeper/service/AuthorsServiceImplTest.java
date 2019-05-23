package mj.bookkeeper.service;

import static org.junit.Assert.assertEquals;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorsServiceImplTest {

	@Autowired
	AuthorsServiceImpl authorsServiceImpl;
		
	@Test
	public void testResponseEntityStatus() {
		ResponseEntity<String> responseEntity = authorsServiceImpl.getAllAuthors();
		assertEquals(responseEntity.getStatusCodeValue(), 200);
	}

	@Test
	public void testNoRatingForAuthorScenario() {
		try {
			JSONArray responseBody = new JSONArray(authorsServiceImpl.getAllAuthors().getBody());
			JSONObject lastObject = responseBody.getJSONObject(responseBody.length()-1);
			assertEquals(lastObject.get("averageRating"), null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRatingCalculation() {
		try {
			JSONArray responseBody = new JSONArray(authorsServiceImpl.getAllAuthors().getBody());
			JSONObject firstObject = responseBody.getJSONObject(0);
			assertEquals(firstObject.get("averageRating"), 5.0d);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}	
}
