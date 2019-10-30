package com.inderjit.portal.signon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inderjit.portal.jwt.SignonAuthenticationEntryPoint;
import com.inderjit.portal.jwt.SignonJWTRequestFilter;
import com.inderjit.portal.signon.service.SignonUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SignonSecurityConfig extends WebSecurityConfigurerAdapter {

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private SignonAuthenticationEntryPoint signonAuthenticationEntryPoint;

	@Autowired
	private SignonUserDetailService signonUserDetailService;
	
	@Autowired
	private SignonJWTRequestFilter signonJWTRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		http.headers().frameOptions().disable();
//		http.csrf().disable().authorizeRequests().antMatchers("/authenticate","/swagger-ui.html").permitAll().
//				anyRequest().authenticated().and().
//				exceptionHandling().authenticationEntryPoint(signonAuthenticationEntryPoint).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//		http.addFilterBefore(signonJWTRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

//  In-Memory authentication for example User admin and password
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(signonUserDetailService).passwordEncoder(passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
