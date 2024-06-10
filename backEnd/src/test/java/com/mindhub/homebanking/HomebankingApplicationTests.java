package com.mindhub.homebanking;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomebankingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void userCanRegister() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
										.contentType(MediaType.APPLICATION_JSON)
										.content("{\"firstName\":\"Test\", \"lastName\":\"User\", \"email\":\"testuser@hotmail.com\", \"password\":\"testuser123\"}")
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isCreated());
	}

	@Test
	public void userCanLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
										.contentType(MediaType.APPLICATION_JSON)
										.content("{\"email\":\"melmorel@hotmail.com\", \"password\":\"melmorel123\"}")
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	public void loginAndSeeCurrentClientInfo() throws Exception {
		// Primero, iniciamos sesión y obtenemos el token de autenticación
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
										.contentType(MediaType.APPLICATION_JSON)
										.content("{\"email\":\"melmorel@hotmail.com\", \"password\":\"melmorel123\"}")
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn();

		String token = result.getResponse().getContentAsString();

		// Luego, usamos este token para hacer una solicitud GET a la ruta '/api/auth/current'
		mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/current")
										.header("Authorization", "Bearer " + token)
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.email").value("melmorel@hotmail.com"))
						.andExpect(jsonPath("$.accounts").isNotEmpty());
	}

	@Test
	@WithMockUser(username = "melmorel@hotmail.com", password = "melmorel123", roles = "CLIENT")
	public void melbaCanGetAccountInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/current")
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.email").value("melmorel@hotmail.com"))
						.andExpect(jsonPath("$.accounts").isNotEmpty());
	}

	@Test
	@WithMockUser(username = "melmorel@hotmail.com", password = "melmorel123", roles = "CLIENT")
	public void melbaCanMakeTransaction() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
										.contentType(MediaType.APPLICATION_JSON)
										.content("{\"sourceAccountNumber\": \"VIN001\", \"amount\": 100, \"description\": \"Test transaction\", \"destinationAccountNumber\": \"VIN002\"}")
										.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isCreated());

	}

}
