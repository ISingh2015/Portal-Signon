package com.inderjit.portal.signon.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inderjit.portal.signon.vo.Signon;

@Repository
public interface SignonRepository extends JpaRepository<Signon, Long> {
	
	@Query ("FROM Signon") 
	List<Signon> getAllUsers (Pageable pageable);
	
	@Query ("select s from Signon s where s.signOn = ?1 or s.mobileNo = ?2") 
	Signon getUserBySignon (String userName, String mobileNo);
	
}
