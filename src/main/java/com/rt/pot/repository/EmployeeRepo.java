package com.rt.pot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.pot.model.Contact;
import com.rt.pot.model.Contractor;
import com.rt.pot.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	public List<Employee> findByContractor(Contractor contractor);
	
	public Employee findByEmployeeIdAndContact(Integer employeeId,Contact contact);
}
