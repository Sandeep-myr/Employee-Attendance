package com.rt.pot.util;

import org.springframework.stereotype.Component;

import com.rt.pot.model.AddressRecord;
import com.rt.pot.model.Contact;
import com.rt.pot.model.Contractor;
import com.rt.pot.model.Employee;
import com.rt.pot.response.AddressResponse;
import com.rt.pot.response.ContactResponse;
import com.rt.pot.response.ContractorResponse;
import com.rt.pot.response.EmployeeResponse;

import lombok.Data;

@Data
@Component
public class EntityToResponse {

	public ContractorResponse getContractorResponse(Contractor contractor) {
		ContractorResponse contractorResponse = new ContractorResponse();
		contractorResponse.setContractorName(contractor.getContractorName());
		contractorResponse.setBirthDate(contractor.getBirthDate());
		contractorResponse.setPassword(contractor.getPassword());
		contractorResponse.setAddressResponse(this.getAddressResponse(contractor.getAddress()));
		contractorResponse.setContact(this.getContactResponse(contractor.getContact()));

		return contractorResponse;
	}

	private ContactResponse getContactResponse(Contact contact) {
		ContactResponse contactResponse = new ContactResponse();
		contactResponse.setEmailId(contact.getEmailId());
		contactResponse.setMobileNumber(contact.getMobileNumber());
		return contactResponse;
	}

	private AddressResponse getAddressResponse(AddressRecord address) {
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setCity(address.getCity());
		addressResponse.setState(address.getState());
		addressResponse.setStreet(address.getStreet());
		addressResponse.setZipcode(address.getZipcode());
		return addressResponse;
	}

	public EmployeeResponse getEmployeeResponse(Employee employee) {
      EmployeeResponse employeeResponse = new EmployeeResponse();
      employeeResponse.setEmployeeName(employee.getEmployeeName());
      employeeResponse.setEmployeeCode(employee.getEmployeeId());
      employeeResponse.setAddressResponse(this.getAddressResponse(employee.getAddressRecord()));
      employeeResponse.setContact(this.getContactResponse(employee.getContact()));
		return employeeResponse;
	}

}
