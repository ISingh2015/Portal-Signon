package com.inderjit.portal.signon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inderjit.portal.exception.RecordNotFoundException;
import com.inderjit.portal.signon.service.SignonService;
import com.inderjit.portal.signon.vo.Signon;

@RestController
@RequestMapping("/user")
public class PortalSignonController {

	@Autowired
	SignonService userService;


	@GetMapping("/getAll/page={pageNo}/{size}/{sortBy}")
	public ResponseEntity<List<Signon>> getAllUsers(@PathVariable("pageNo") Integer pageNo, @PathVariable("size") Integer size, @PathVariable("sortBy") String sortBy) throws RecordNotFoundException{
		Pageable pageable = PageRequest.of(pageNo, size, new Sort(Direction.ASC,sortBy));
		List<Signon> userList = userService.getAllUsers(pageable);
		return new ResponseEntity<List<Signon>> (userList, new HttpHeaders(), HttpStatus.OK); 
	}

	@PostMapping("/create")
	public ResponseEntity<Signon> createOrUpdateUser(Signon user) throws RecordNotFoundException{
		Signon userSaved = userService.createOrUpdateUser(user);
		return new ResponseEntity<Signon> (userSaved, new HttpHeaders(), HttpStatus.OK); 
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Signon> getUserById (@PathVariable("id") Long id) throws RecordNotFoundException{
		Signon user = userService.getUserById(id);
		return new ResponseEntity<Signon> (user, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/login/user={signOn}/mobile={mobileNo}")
	public ResponseEntity<Signon> getUserBySignon(@PathVariable("signOn") String signOn, @PathVariable("mobileNo") String mobileNo) throws RecordNotFoundException{
		Signon user = userService.getUserBySignon(signOn, mobileNo);
		return new ResponseEntity<Signon> (user, new HttpHeaders(), HttpStatus.OK); 
	}

	@DeleteMapping("delete/{id}")
	public HttpStatus deleteUserById (@PathVariable("id") Long id) throws RecordNotFoundException{
		userService.deleteUserById(id);
		return HttpStatus.FORBIDDEN; 
	}

}
