package com.inderjit.portal.signon.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inderjit.portal.exception.RecordNotFoundException;
import com.inderjit.portal.signon.service.SignonService;
import com.inderjit.portal.signon.vo.Signon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "PortalSignonController")
@RestController
public class PortalSignonController {

	
	@Autowired
	private SignonService signonService;

	@ApiOperation(value = "Get a List of Users on the Portal.", response = List.class, tags = "Get All Users")
	@GetMapping("getAll")
	public ResponseEntity<List<Signon>> getAllUsers(@RequestParam(defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(defaultValue = "1000", required = false) Integer size,
			@RequestParam(defaultValue = "email", required = false) String sortBy,
			@RequestParam(defaultValue = "ASC", required = false) String sortOrd) throws RecordNotFoundException {
		Pageable pageable = PageRequest.of(pageNo, size,
				new Sort((sortOrd.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sortBy));
		List<Signon> userList = signonService.getAllUsers(pageable);
		return new ResponseEntity<List<Signon>>(userList, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Register new User on Portal.", response = Signon.class, tags = "Register New")
	@PostMapping("register")
	public ResponseEntity<Signon> register(@Valid @RequestBody Signon user) throws RecordNotFoundException {
		Signon userSaved = signonService.createOrUpdateUser(user);
		return new ResponseEntity<Signon>(userSaved, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a registered User on Portal.", response = Signon.class, tags = "Get User By Id")
	@GetMapping("get/{id}")
	public ResponseEntity<Signon> getUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
		Signon user = signonService.getUserById(id);
		return new ResponseEntity<Signon>(user, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Sign in a active registered User on Portal.", response = Signon.class, tags = "Authenticate-SignOn")
	@GetMapping("authenticate")
	public ResponseEntity<Signon> authenticate(String signOn, String mobileNo) throws RecordNotFoundException {
		Signon user = signonService.getUserBySignon(signOn, mobileNo);
		return new ResponseEntity<Signon>(user, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a User on Portal.", response = HttpStatus.class, tags = "Delete User By Id")
	@DeleteMapping("delete")
	public HttpStatus deleteUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
		signonService.deleteUserById(id);
		return HttpStatus.FORBIDDEN;
	}

}
