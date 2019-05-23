package mj.bookkeeper.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookByISBNControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testValidationUnsuccessfulScenario() {
		try {
			mockMvc.perform(get("/api/book/")).andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationSuccessfulScenario() {
		try {
			mockMvc.perform(get("/api/book/9780201725933")).andExpect(status().isOk()).andExpect(content().string(containsString("On to Java 2")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
