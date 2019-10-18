package com.inderjit.portal.signon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.inderjit.portal.signon.service.SignonUserDetailService;

@Configuration
public class SignonSecurityConfig extends WebSecurityConfigurerAdapter {

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private SignonUserDetailService signonUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		http.headers().frameOptions().disable();
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

}
