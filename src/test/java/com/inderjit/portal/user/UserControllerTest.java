package com.inderjit.portal.user;

import static org.junit.Assert.assertNotNull;

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
public class UserControllerTest {

	@Autowired
	private SignonService userService;

	@Test
	public void getAllUsersTest() {
		Pageable pageable = PageRequest.of(10, 100,new Sort(Direction.ASC,"email"));
		assertNotNull(userService.getAllUsers(pageable));
	}

	@Test
	public void getUserBySignon() {
		Signon user= null;
		try {
			user = userService.getUserBySignon("TEST","7738535998");			
		} catch (Exception e) {
			
		}
		assertNotNull(user);
	}

	@Test
	public void getUserById() {
		Signon user= null;
		try {
			user = userService.getUserById(100l);			
		} catch (Exception e) {
			
		}
		assertNotNull(user);
	}

//	@Test
	public void registerNew() {
		Signon user= new Signon(0,"first","last","email@one.com","990","TEST","$2a$10$oQv9u4pkaWnEewfAEtl/TOpz1y816YVgcUDkwm/tEXh0N8Dj47Q1C","Admin","1");
		try {
			user = userService.createOrUpdateUser(user);			
		} catch (Exception e) {
			
		}
		assertNotNull(user);
	}

}
