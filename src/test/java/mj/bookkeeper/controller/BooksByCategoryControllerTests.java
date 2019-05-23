package mj.bookkeeper.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksByCategoryControllerTests {

	@Inject
	MockMvc mockMvc; 
	
	@Test
	public void testValidationSuccessfulScenario() {
		try {
			mockMvc.perform(get("/api/category/Computers/books")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationUnsuccessfulScenario() {
		try {
			mockMvc.perform(get("/api/category//books")).andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
