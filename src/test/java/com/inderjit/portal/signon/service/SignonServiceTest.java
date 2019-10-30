package com.inderjit.portal.signon.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.inderjit.portal.signon.InderjitPortalSignon;
import com.inderjit.portal.signon.service.SignonService;
import com.inderjit.portal.signon.vo.Signon;

@SpringBootTest(classes = InderjitPortalSignon.class)
@RunWith(SpringRunner.class)
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class})
//@DatabaseSetup({"classpath:UserData.xml"})

public class SignonServiceTest {

	@Autowired
	private SignonService userService;

	@Test
	public void getAllUsersTest() {
		Pageable pageable = PageRequest.of(0, 100,new Sort(Direction.ASC,"email"));
		assertNotNull(userService.getAllUsers(pageable));
	}

	@Test
	public void getUserBySignon() {
		Signon user= null;
		try {
			user = userService.getUserBySignon("TEST","7738535998");			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		assertNotNull(user);
	}

	@Test
	public void getUserById() {
		Signon user= null;
		try {
			user = userService.getUserById(100l);			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		assertNotNull(user);
	}

//	@Test
	public void deleteUser() {
		boolean deleted=false;
		try {
			deleted = userService.deleteUserById(100l);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(deleted);
	}

	@Test
	public void registerNew() {
		java.sql.Time sqlDate = new java.sql.Time(new java.util.Date().getTime());
		System.out.println(sqlDate);
		Signon user= new Signon(100,"INDERJIT","SANHOTRA","email@one.com","7738535998","TEST","$2a$10$WLW6uPamKukW8CdKB38lJOVFFr4ZoporD2IW3xLYstxAM2oxi4WIS","ROLE_ADMIN,ROLE_USER",true,true,true,true,100,sqlDate,100, sqlDate);
		try {
			user = userService.createOrUpdateUser(user);
			System.out.println(user.toString());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		assertNotNull(user);
	}

}
