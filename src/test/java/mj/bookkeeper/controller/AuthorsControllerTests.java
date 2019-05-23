package mj.bookkeeper.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import mj.bookkeeper.service.AuthorsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AuthorsServiceImpl authorsServiceImpl;
	
	@Test
	public void testResponse() {
		
		try {
			JSONObject relevantResponse = new JSONObject(authorsServiceImpl.getAllAuthors().getBody().toString());
			mockMvc.perform(get("/api/rating"))
			.andExpect(status().isOk())
			.andExpect(content().string(relevantResponse.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
