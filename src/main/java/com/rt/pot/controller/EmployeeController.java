package com.rt.pot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rt.pot.error.PotException;
import com.rt.pot.request.AttendanceReq;
import com.rt.pot.request.EmployeeReq;
import com.rt.pot.service.IEmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employee")
public class EmployeeController {
	
	
	@Autowired
	private IEmployeeService employeeService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerEmployee(@RequestBody EmployeeReq employeeReq) throws PotException{
		try {

			return new ResponseEntity<>(this.employeeService.employeeRegistration(employeeReq), HttpStatus.OK);

		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

		
	}
	
//============================================================================
	//This method are responsible to handle checkin date store
	
	@PostMapping("/checkIn")
	public ResponseEntity<String> checkInEmployee(@RequestBody AttendanceReq attendanceReq) throws PotException{
	
		try {

		
			
			return new ResponseEntity<>(this.employeeService.employeeCheckIn(attendanceReq), HttpStatus.OK);

		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

		
	}
	
//============================================================================
		//This method are responsible to handle checkout date store
	
	@PostMapping("/checkOut")
	public ResponseEntity<String> checkOutEmployee(@RequestBody AttendanceReq attendanceReq) throws PotException{
	
		try {

		
			
			return new ResponseEntity<>(this.employeeService.employeeCheckOut(attendanceReq), HttpStatus.OK);

		} catch (Exception e) {
			throw new PotException(e.getMessage());
		}

		
	}

	
}
