package com.SpringAuthentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.SpringAuthentication.service.impl.UserService;


/**
 * @author Ankit Khatri
 */

/* Class for implementing security and configuring Spring security */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/* Default or Open URLS for all users */
		.antMatchers("/css/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/forgot-password").permitAll()
		.antMatchers("/reset-password").permitAll()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/brokenLink").permitAll()
		.antMatchers(HttpMethod.GET, "/index").permitAll()
		.antMatchers(HttpMethod.GET, "/register").permitAll()
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/verify/user/**").permitAll()
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/welcome")
				.failureUrl("/login?error")
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
			.and()
				.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index")
				.permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/403");
	}

}
