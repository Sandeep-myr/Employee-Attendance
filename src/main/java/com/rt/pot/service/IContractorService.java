package com.rt.pot.service;

import org.springframework.http.ResponseEntity;

import com.rt.pot.request.ContactorReq;
import com.rt.pot.request.LoginReq;
import com.rt.pot.response.EmployeeResponse;

public interface IContractorService {

	public String contractorRegistration(ContactorReq contractorReq);

	public ResponseEntity<?>  loginVerification(LoginReq loginReq);

	public ResponseEntity<?>   getAllEmployeeDetails(LoginReq loginReq);

	public ResponseEntity<?> updateEmployeeDetails(EmployeeResponse employeeResponse);

	public String deleteEmployeeRecord(EmployeeResponse employeeResponse);

}
