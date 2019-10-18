package com.inderjit.portal.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCreator {
	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static void main (String ... args) {
		String passToEncode = "soccer2019";
		System.out.println(passwordEncoder.encode(passToEncode));
	}
}
