/**
 * 
 */
package com.inderjit.portal.signon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public  class InderjitPortalSignon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(InderjitPortalSignon.class, args);
	}
	
}
