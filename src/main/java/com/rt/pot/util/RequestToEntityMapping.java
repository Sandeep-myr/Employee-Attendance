package com.rt.pot.util;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.rt.pot.model.AddressRecord;
import com.rt.pot.model.Attandance;
import com.rt.pot.model.Contact;
import com.rt.pot.model.Employee;
import com.rt.pot.model.status.AttendanceStatus;
import com.rt.pot.request.AddressReq;
import com.rt.pot.request.AttendanceReq;
import com.rt.pot.request.ContactReq;
import com.rt.pot.request.EmployeeReq;
import com.rt.pot.response.EmployeeResponse;

import lombok.Data;

@Data
@Component
public class RequestToEntityMapping {

	public AddressRecord addressReqToAddress(AddressReq addressReq) {
		AddressRecord addressRecord = new AddressRecord();
		addressRecord.setStreet(addressReq.getStreet());
		addressRecord.setCity(addressReq.getCity());
		addressRecord.setState(addressReq.getState());
		addressRecord.setZipcode(addressReq.getZipcode());
		return addressRecord;
	}

	public Contact contactReqToContactEntity(ContactReq contactReq) {
		Contact contact = new Contact();
		contact.setEmailId(contactReq.getEmailId());
		contact.setMobileNumber(contactReq.getMobileNumber());
		return contact;
	}

	public Employee EmployeeReqToEmployee(EmployeeReq employeeReq) {
		Employee employee = new Employee();
		employee.setEmployeeName(employeeReq.getEmployeeName());
		employee.setPassword(employeeReq.getPassword());
		Contact contact = this.contactReqToContactEntity(employeeReq.getContactReq());
		employee.setContact(contact);
		AddressRecord addressRecord = this.addressReqToAddress(employeeReq.getAddressReq());
		employee.setAddressRecord(addressRecord);
		
		return employee;
	}

	public Attandance attandenceReqToAttendance(AttendanceReq attendanceReq) {
		Attandance attandance = new Attandance();
		attandance.setCheckInTime(attendanceReq.getCheckInTime());
		attandance.setDate(attendanceReq.getDate());
		attandance.setCheckOutTime(LocalTime.of(14, 00));
        attandance.setAttendanceStatus(AttendanceStatus.Absent);
		return attandance;
	}

		

}
