package com.rt.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.pot.model.Contact;
import com.rt.pot.model.Contractor;

@Repository
public interface ContractorRepo extends JpaRepository<Contractor, Integer> {

	
	public Contractor findByContactAndPassword(Contact Contact,String password);
}
