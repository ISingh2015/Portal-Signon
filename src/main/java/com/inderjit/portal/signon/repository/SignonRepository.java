package com.inderjit.portal.signon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inderjit.portal.signon.vo.Signon;

@Repository
public interface SignonRepository extends JpaRepository<Signon, Long> {
	
	@Query ("FROM Signon s where s.signonEnabled is not null") 
	List<Signon> getAllUsers (Pageable pageable);
	
	@Query ("select s from Signon s where (s.signOn = ?1 or s.mobileNo = ?2)") 
	Optional<Signon> getUserBySignonAndMobile (String userName, String mobileNo);
	
	@Query ("select s from Signon s where (s.email = ?1)") 
	Optional<Signon> checkIfDuplicateEmail(String email);

	@Query ("select s from Signon s where (s.signOn = ?1)") 
	Optional<Signon> checkIfDuplicateSignon (String userName);

	@Query ("select s from Signon s where (s.mobileNo = ?1)") 
	Optional<Signon> checkIfDuplicateMobileNo (String mobileNo);

}
