package com.rt.pot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rt.pot.error.PotException;
import com.rt.pot.request.ContactorReq;
import com.rt.pot.request.LoginReq;
import com.rt.pot.response.EmployeeResponse;
import com.rt.pot.service.IContractorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contractor")
public class ContractorController {

	@Autowired
	private IContractorService contractorService;

//=============================================================================================

	// This Method Handle The Admin Registration Request
	@PostMapping("/registration")
	public ResponseEntity<String> contractorRegistration(@RequestBody ContactorReq contractorReq) throws PotException {

		try {

			return new ResponseEntity<>(this.contractorService.contractorRegistration(contractorReq), HttpStatus.OK);

		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

	}

//=============================================================================================

	// This Method Handle the Admin Login Detils

	@PostMapping("/login")
	public ResponseEntity<?> loginContractor(@RequestBody LoginReq loginReq) throws PotException {

		try {
			return this.contractorService.loginVerification(loginReq);
		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

	}

//=============================================================================================

	// This method are handled the all admin details belong to perticular contcator

	@GetMapping("/AllEmployeeDetails")
	public ResponseEntity<?> getAllEmployee(@RequestBody LoginReq loginReq) throws PotException {
		try {

			return contractorService.getAllEmployeeDetails(loginReq);
		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}
	}

//=============================================================================================

// UPDATE THE EMPLOYEE DETAILS	

	@PutMapping("/updateEmployeeDetails")
	public ResponseEntity<?> updateEmployeeDetails(@RequestBody EmployeeResponse employeeResponse) throws PotException {
		try {

			return contractorService.updateEmployeeDetails(employeeResponse);
		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

	}

//=============================================================================================

	// Delete The Employee Record

	@DeleteMapping("/deleteEmployeeRecord")
	public ResponseEntity<String> deleteEmployeeRecord(@RequestBody EmployeeResponse employeeResponse)
			throws PotException {
		try {
			return new ResponseEntity<>(contractorService.deleteEmployeeRecord(employeeResponse), HttpStatus.OK);
		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}
	}
	
//=============================================================================================
	
	// Employee Has Check in The morning Attandance has been recorded
	
	
	
	
	

}
