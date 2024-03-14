package com.rt.pot.serviceimpl;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rt.pot.model.Attandance;
import com.rt.pot.model.Contact;
import com.rt.pot.model.Contractor;
import com.rt.pot.model.Employee;
import com.rt.pot.model.status.AttendanceStatus;
import com.rt.pot.repository.AttandanceRepo;
import com.rt.pot.repository.ContactRepo;
import com.rt.pot.repository.ContractorRepo;
import com.rt.pot.repository.EmployeeRepo;
import com.rt.pot.request.AttendanceReq;
import com.rt.pot.request.EmployeeReq;
import com.rt.pot.service.IEmployeeService;
import com.rt.pot.util.RequestToEntityMapping;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ContractorRepo contractorRepo;

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private RequestToEntityMapping entityMapping;

	@Autowired
	private AttandanceRepo attandanceRepo;

	@Override
	public String employeeRegistration(EmployeeReq employeeReq) {

		Employee employee = entityMapping.EmployeeReqToEmployee(employeeReq);
		Contact contact = contactRepo.findByEmailIdIgnoreCase(employeeReq.getLoginReq().getEmailId());
		Contractor contractor = contractorRepo.findByContactAndPassword(contact,
				employeeReq.getLoginReq().getPassword());
		if (contractor != null) {
			employee.setContractor(contractor);
			employeeRepo.save(employee);
			return "Employee Regitration Complete";
		} else {
			return "Something Invalid Credentials";
		}

	}

//=============================================================================

	// THIS METHOD ARE RESPONSIBLE TO HANDL THE STORING OF CHECKIN TIME
	@Override
	public String employeeCheckIn(@RequestBody AttendanceReq attendanceReq) {
		Employee employee = employeeRepo.findById(attendanceReq.getEmployeeCode()).get();
		if (employee != null) {
			Attandance attandance = entityMapping.attandenceReqToAttendance(attendanceReq);
			attandance.setEmployee(employee);
			attandanceRepo.save(attandance);

			return "Check In Successfully";
		} else {
			return "Check Out Successfully";
		}

	}

//=============================================================================

	// THIS METHOD ARE RESPONSIBLE TO HANDL THE STORING OF CHECKOUT TIME

	@Override
	public String employeeCheckOut(AttendanceReq attendanceReq) {
		Employee employee = employeeRepo.findById(attendanceReq.getEmployeeCode()).get();
		if (employee != null) {
			LocalTime checkoutTime = attendanceReq.getCheckoutTime();
			Attandance attandance = attandanceRepo.findByEmployeeAndDate(employee, attendanceReq.getDate());
			long duration = ChronoUnit.MINUTES.between(attandance.getCheckInTime(),checkoutTime);
			System.err.println(duration);
			if (duration >= 12 * 60) {
			
				attandanceRepo.updateCheckOutAndStatus(employee.getEmployeeId(), attendanceReq.getDate(), checkoutTime,
						AttendanceStatus.HyperActive);
			} else if (duration >= 10 * 60) {
				
				attandanceRepo.updateCheckOutAndStatus(employee.getEmployeeId(), attendanceReq.getDate(), checkoutTime,
						AttendanceStatus.Active);
			} else if (duration >= 8 * 60) {
			
				attandanceRepo.updateCheckOutAndStatus(employee.getEmployeeId(), attendanceReq.getDate(), checkoutTime,
						AttendanceStatus.Present);
			} else if (duration >= 4 * 60) {
				
				attandanceRepo.updateCheckOutAndStatus(employee.getEmployeeId(), attendanceReq.getDate(), checkoutTime,
						AttendanceStatus.Passive);
			}

			return "Check out Successfully";
		} else {
			return "Some thing are wrong";
		}
	}
}
