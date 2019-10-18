package com.inderjit.portal.signon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inderjit.portal.exception.RecordNotFoundException;
import com.inderjit.portal.signon.repository.SignonRepository;
import com.inderjit.portal.signon.vo.Signon;

@Service
public class SignonService implements UserDetailsService {

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
			throw new RecordNotFoundException("User Not Found : " + signonName);
		}
	}

	public Signon getUserById(Long id) throws RecordNotFoundException {
		Optional<Signon> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
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
			throw new RecordNotFoundException("User Not Found with ID : " + id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String signOnName) throws UsernameNotFoundException {
		System.out.println("Load User: " + signOnName);
		User user=null;
		try {
			Signon signon = userRepository.getUserBySignon(signOnName, "");
			if (signon.equals(null)) {
				throw new UsernameNotFoundException("User Not Found : " + signOnName);
			}
			List<SimpleGrantedAuthority> authList = getAuthorities(signon.getSignonRole());
	        user = new User(signon.getSignOn(), signon.getSignonPassword(), authList);			
		} catch (Exception ex) {

		}
		return user;
	}

	private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
 
        if (!role.isEmpty() && role.trim().length() > 0) {
            if (role.equalsIgnoreCase("admin")) {
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
 
        return authList;
    }
}
