package psu.edu.ch06.crud04.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpsec) throws Exception {
		return httpsec
				// stateless sessions -> no session cookie to protect, so CSRF tokens are not needed
				.csrf(customizer -> customizer.disable())
				// every API endpoint requires authentication
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				// basic authentication (username/password in the Authorization header)
				.httpBasic(Customizer.withDefaults())
				// a new session for every request, nothing stored on the server
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

	// Used by Spring Security together with MyUserDetailsService to check the passwords stored (hashed) in the DB
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
