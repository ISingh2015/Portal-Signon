package com.inderjit.portal.signon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private SignonRepository userRepository;

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public List<Signon> getAllUsers(Pageable pageable) {
		List<Signon> signonList = userRepository.getAllUsers(pageable);
		if (!signonList.isEmpty()) {
			return signonList;
		} else {
			return new ArrayList<Signon>();
		}
	}

	public Signon getUserBySignon(String signonName, String mobileNo) throws RecordNotFoundException {
		Signon user = userRepository.getUserBySignon(signonName, mobileNo);
		if (!user.equals(null)) {
			return user;
		} else {
			if (logger.isEnabled(Level.INFO)) logger.info("User Not Found : " + signonName);			
			throw new RecordNotFoundException("User Not Found : " + signonName);
		}
	}

	public Signon getUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			if (logger.isEnabled(Level.ERROR)) logger.error("User Not Found with ID : " + id);			
			throw new RecordNotFoundException("User Not Found with ID : " + id);
		}
	}

	public Signon createOrUpdateUser(Signon user) throws RecordNotFoundException {
		Optional<Signon> userNew = userRepository.findById(user.getId());
		if (!userNew.isPresent()) {
			Signon userToSave = new Signon();
			userToSave.setId(user.getId());
			userToSave.setFirstName(user.getFirstName());
			userToSave.setFirstName(user.getLastName());
			userToSave.setEmail(user.getEmail());
			userRepository.save(userToSave);
			return userToSave;
		} else {
			userRepository.save(user);
			return user;
		}
	}

	public void deleteUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
		} else {
			if (logger.isEnabled(Level.INFO)) logger.info("User Not to Delete: " + id);
			throw new RecordNotFoundException("User Not Found to Delete : " + id);
		}
	}

}
