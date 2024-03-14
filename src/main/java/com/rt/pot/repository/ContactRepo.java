package com.rt.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.pot.model.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer>

{
   public Contact findByEmailIdIgnoreCase(String emailId);
	
	
}
