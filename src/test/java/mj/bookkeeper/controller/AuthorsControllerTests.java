package mj.bookkeeper.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
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
		ResponseEntity<String> relevantResponse = authorsServiceImpl.getAllAuthors();
		try {
			mockMvc.perform(get("/api/rating"))
			.andExpect(status().isOk())
			.andExpect(content().string(relevantResponse.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
