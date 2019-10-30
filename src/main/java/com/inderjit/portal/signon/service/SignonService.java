package com.inderjit.portal.signon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inderjit.portal.exception.RecordNotFoundException;
import com.inderjit.portal.signon.repository.SignonRepository;
import com.inderjit.portal.signon.vo.Signon;

@Service
public class SignonService {
	static Logger logger = Logger.getLogger(SignonService.class);

	@Autowired
	private SignonRepository signonRepository;

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public List<Signon> getAllUsers(Pageable pageable) {
		List<Signon> signonList = signonRepository.getAllUsers(pageable);
		if (!signonList.isEmpty()) {
			return signonList;
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("Users Not Found : ");
			throw new RecordNotFoundException("Users Not Found : ");
		}
	}

	public Signon getUserBySignon(String signonName, String mobileNo) throws RecordNotFoundException {
		Optional<Signon> user = signonRepository.getUserBySignonAndMobile(signonName, mobileNo);
		if (user.isPresent()) {
			return user.get();
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("User Not Found :" + signonName);
			throw new RecordNotFoundException("User Not Found : " + signonName);
		}
	}

	public Signon getUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = signonRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.error("User Not Found with ID : " + id);
			throw new RecordNotFoundException("User Not Found with ID : " + id);
		}
	}

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public Signon createOrUpdateUser(Signon user) throws RecordNotFoundException {
		if (user.getId() != 0) {
			Signon userToSave = signonRepository.findById(user.getId()).get();
			userToSave.setFirstName(user.getFirstName());
			userToSave.setLastName(user.getLastName());
			userToSave.setEmail(user.getEmail());
			userToSave.setMobileNo(user.getMobileNo());
			userToSave.setSignOn(user.getSignOn());
			userToSave.setSignonPassword(passwordEncoder.encode(user.getSignonPassword()));
			userToSave.setSignonRole(user.getSignonRole());
			userToSave.setSignonEnabled(user.isSignonEnabled());
			userToSave.setUpdatedBy(user.getUpdatedBy());
			userToSave.setUpdateDate(user.getUpdateDate());
			signonRepository.save(userToSave);
			return userToSave;
		} else {
			if (validateSignOn(user)) {
				user.setCreatedBy(user.getCreatedBy());
				user.setCreateDate(user.getCreateDate());
				user.setSignonPassword(passwordEncoder.encode(user.getSignonPassword()));
				signonRepository.save(user);
			}
			return user;
		}
	}

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public boolean deleteUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = signonRepository.findById(id);
		if (user.isPresent()) {
			signonRepository.deleteById(id);
			return true;
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("User Not Found to Delete: " + id);
			throw new RecordNotFoundException("User Not Found to Delete : " + id);
		}
	}

	private boolean validateSignOn(Signon user) {
		if (signonRepository.checkIfDuplicateEmail(user.getEmail()).isPresent()) {
			if (logger.isEnabled(Level.ERROR))
				logger.info("Email already registered: " + user.getSignOn());
			throw new RecordNotFoundException("Email already registered: " + user.getSignOn());

		} else if (signonRepository.checkIfDuplicateSignon(user.getSignOn()).isPresent()) {
			if (logger.isEnabled(Level.ERROR))
				logger.info("Signon already registered: " + user.getSignOn());
			throw new RecordNotFoundException("Signon already registered: " + user.getSignOn());

		} else if (signonRepository.checkIfDuplicateMobileNo(user.getMobileNo()).isPresent()) {
			if (logger.isEnabled(Level.ERROR))
				logger.info("MobileNo already registered: " + user.getSignOn());
			throw new RecordNotFoundException("MobileNo already registered: " + user.getSignOn());

		}
		return true;
	}

//	private void createAuthentication(String username, String password) throws RecordNotFoundException{
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new RecordNotFoundException("User Disabled : " + username);
//		} catch (BadCredentialsException e) {
//			throw new RecordNotFoundException("Invalid Credentails: " + username);			
//		}
//	}	

}
