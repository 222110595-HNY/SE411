package psu.edu.ch06.crud04;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class Crud04ApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	void requestWithoutCredentialsIsRejected() throws Exception {
		mvc.perform(get("/api/v1/products")).andExpect(status().isUnauthorized());
	}

	@Test
	void wrongPasswordIsRejected() throws Exception {
		mvc.perform(get("/api/v1/products").with(httpBasic("user1", "wrong"))).andExpect(status().isUnauthorized());
	}

	@Test
	void usersFromDatabaseCanAccessApi() throws Exception {
		mvc.perform(get("/api/v1/products").with(httpBasic("user1", "pwd123"))).andExpect(status().isOk());
		mvc.perform(get("/api/v1/products/1").with(httpBasic("user2", "pwd456"))).andExpect(status().isOk());
	}

	@Test
	void postWorksWithoutCsrfToken() throws Exception {
		mvc.perform(post("/api/v1/products").with(httpBasic("user1", "pwd123"))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Monitor\",\"description\":\"A 27 inch monitor\",\"price\":799.99}"))
			.andExpect(status().isCreated());
	}

}
