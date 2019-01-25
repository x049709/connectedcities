package com.coolmix.userservice;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.coolmix.userservice.controllers.ConnectedCitiesController;

@RunWith(SpringRunner.class)
@WebMvcTest(ConnectedCitiesController.class)                  
public class ConnectedCitiesControllerTest {

	@Autowired
	private MockMvc mockMvc;                         

	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/connected?origin=Boston&destination=Newark"))                      
		.andExpect(status().isOk())                  
		.andExpect(content().string(                 
				containsString("yes")));
		mockMvc.perform(get("/connected?origin=Boston&destination=Nowhere"))                      
		.andExpect(status().isOk())                  
		.andExpect(content().string(                 
				containsString("no")));
	}

}