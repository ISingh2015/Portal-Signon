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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.inderjit.portal.exception.RecordNotFoundException;
import com.inderjit.portal.signon.repository.SignonRepository;
import com.inderjit.portal.signon.vo.Signon;

@Service
public class SignonService {
	static Logger logger = Logger.getLogger(SignonService.class);
	
	@Autowired
	private SignonRepository userRepository;

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Transactional(rollbackOn = RecordNotFoundException.class)
	public List<Signon> getAllUsers(Pageable pageable) {
		List<Signon> signonList = userRepository.getAllUsers(pageable);
		if (!signonList.isEmpty()) {
			return signonList;
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("Users Not Found : ");
			throw new RecordNotFoundException("Users Not Found : ");
		}
	}

	public Signon getUserBySignon(String signonName, String mobileNo) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.getUserBySignon(signonName, mobileNo);
		if (user.isPresent()) {
			return user.get();
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("User Not Found :"+signonName);
			throw new RecordNotFoundException("User Not Found : " + signonName);
		}
	}

	public Signon getUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.findById(id);
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
			Signon userToSave = userRepository.findById(user.getId()).get();
			userToSave.setFirstName(user.getFirstName());
			userToSave.setLastName(user.getLastName());
			userToSave.setEmail(user.getEmail());
			userToSave.setMobileNo(user.getMobileNo());
			userToSave.setSignOn(user.getSignOn());
			userToSave.setSignonPassword(passwordEncoder.encode(user.getSignonPassword()));
			userToSave.setSignonRole(user.getSignonRole());
			userToSave.setSignonActive(user.getSignonActive());
			userRepository.save(userToSave);
			return userToSave;
		} else {
			user.setSignonPassword(passwordEncoder.encode(user.getSignonPassword()));			
			userRepository.save(user);
			return user;
		}
	}

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public void deleteUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
		} else {
			if (logger.isEnabled(Level.ERROR))
				logger.info("User Not Found to Delete: " + id);
			throw new RecordNotFoundException("User Not Found to Delete : " + id);
		}
	}

}
