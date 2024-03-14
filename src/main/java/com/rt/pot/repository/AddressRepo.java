package com.rt.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.pot.model.AddressRecord;

@Repository
public interface AddressRepo extends JpaRepository<AddressRecord, Integer> {

}
