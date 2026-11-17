package psu.se411.lab16;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CrudApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void homeIsPublic() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void apiRedirectsAnonymousToLogin() throws Exception {
		mvc.perform(get("/api/v1/courses")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	@Test
	void loginWithInMemoryUsers() throws Exception {
		mvc.perform(formLogin().user("usera").password("pwd123")).andExpect(authenticated().withRoles("USER"));
		mvc.perform(formLogin().user("admin").password("admin123")).andExpect(authenticated().withRoles("ADMIN"));
		mvc.perform(formLogin().user("usera").password("wrong")).andExpect(unauthenticated());
	}

	@Test
	void userCanUseApiAndDashboardButNotAdminDashboard() throws Exception {
		mvc.perform(get("/api/v1/courses").with(user("usera").roles("USER"))).andExpect(status().isOk());
		mvc.perform(get("/dashboard").with(user("usera").roles("USER"))).andExpect(status().isOk());
		mvc.perform(get("/admindashboard").with(user("usera").roles("USER"))).andExpect(status().isForbidden());
	}

	@Test
	void adminCanOpenAdminDashboardOnly() throws Exception {
		mvc.perform(get("/admindashboard").with(user("admin").roles("ADMIN"))).andExpect(status().isOk());
		mvc.perform(get("/dashboard").with(user("admin").roles("ADMIN"))).andExpect(status().isForbidden());
	}

}
