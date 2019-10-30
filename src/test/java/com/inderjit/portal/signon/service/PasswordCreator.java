package com.inderjit.portal.signon.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCreator {
	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static void main (String ... args) {
		String passToEncode = "soccer";
		System.out.println(passwordEncoder.encode(passToEncode));
	}
}
