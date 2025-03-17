package fi.haagahelia.bookstore;

//import static method antMatcher
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	// @Autowired
	private UserDetailsService userDetailsService; // type of attribute -> interface

	// Constructor injection
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
			new AntPathRequestMatcher("/api/students**"),
			new AntPathRequestMatcher("/h2-console/**") };

	// with lambda
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/css/**")).permitAll() // Enable css when logged out
				.requestMatchers(WHITE_LIST_URLS).permitAll()
				.requestMatchers(antMatcher("/signup")).permitAll()
				.requestMatchers(antMatcher("/saveuser")).permitAll()
				.anyRequest().authenticated())
				.formLogin(formlogin -> formlogin
						.loginPage("/login")
						.defaultSuccessUrl("/booklist", true).permitAll())
				.logout(logout -> logout
						.permitAll());
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
