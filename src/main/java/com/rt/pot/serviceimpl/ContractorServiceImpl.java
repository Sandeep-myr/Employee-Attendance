package com.rt.pot.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rt.pot.model.AddressRecord;
import com.rt.pot.model.Contact;
import com.rt.pot.model.Contractor;
import com.rt.pot.model.Employee;
import com.rt.pot.repository.ContactRepo;
import com.rt.pot.repository.ContractorRepo;
import com.rt.pot.repository.EmployeeRepo;
import com.rt.pot.request.ContactorReq;
import com.rt.pot.request.LoginReq;
import com.rt.pot.response.ContractorResponse;
import com.rt.pot.response.EmployeeResponse;
import com.rt.pot.service.IContractorService;
import com.rt.pot.util.EntityToResponse;
import com.rt.pot.util.RequestToEntityMapping;

@Service
public class ContractorServiceImpl implements IContractorService {

	@Autowired
	private ContractorRepo contractorRepo;

	@Autowired
	private RequestToEntityMapping entityMapping;

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private EntityToResponse entityToResponse;

	@Autowired
	private EmployeeRepo employeeRepo;

	// This Method Are Able To Handle Contractor Registration Request

	@Override
	public String contractorRegistration(ContactorReq contractorReq) {

		Contractor contractor = new Contractor();
		contractor.setContractorName(contractorReq.getContractorName());
		contractor.setBirthDate(contractorReq.getBirthDate());

		// SETTING THE ADDRESS RECORD INTO THE CONTERACTOR
		AddressRecord addressRecord = entityMapping.addressReqToAddress(contractorReq.getAddressReq());
		contractor.setAddress(addressRecord);

		// SETTING THE CONTCATOR CONTACT DETAILS
		Contact contact = entityMapping.contactReqToContactEntity(contractorReq.getContactReq());
		contractor.setContact(contact);

		// password setting

		contractor.setPassword(contractorReq.getPassword());

		List<Contractor> contractors = contractorRepo.findAll();
		boolean exist = false;
		for (Contractor contractorexist : contractors) {
			if (contractorexist.getContact().getEmailId().equalsIgnoreCase(contractor.getContact().getEmailId())) {
				exist = true;
			}

		}
		if (exist) {
			return "This EmailId Are already Exist";
		} else {
			contractorRepo.save(contractor);

			return "COntractor Registration Successfull";
		}

	}

//=====================================================================================

	// THIS METHOD ARE HANDLED TO LOGIN REQUEST

	@Override
	public ResponseEntity<?> loginVerification(LoginReq loginReq) {
		Contact contact = contactRepo.findByEmailIdIgnoreCase(loginReq.getEmailId());
		Contractor contractor = contractorRepo.findByContactAndPassword(contact, loginReq.getPassword());

		if (contractor != null) {
			ContractorResponse contractorResponse = entityToResponse.getContractorResponse(contractor);
			return new ResponseEntity<ContractorResponse>(contractorResponse, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
	}

	// =====================================================================================

	// THIS METHOD ARE FTECH ALL EMPLOYEE DETAILS

	@Override
	public ResponseEntity<?> getAllEmployeeDetails(LoginReq loginReq) {
		Contact contact = contactRepo.findByEmailIdIgnoreCase(loginReq.getEmailId());
		Contractor contractor = contractorRepo.findByContactAndPassword(contact, loginReq.getPassword());
		if (contractor != null) {
			List<Employee> employeeList = employeeRepo.findByContractor(contractor);
			List<EmployeeResponse> employeeResponses = new ArrayList<>();
			employeeList.stream().forEach(employee -> {
				EmployeeResponse employeeResponse = entityToResponse.getEmployeeResponse(employee);
				employeeResponses.add(employeeResponse);
			});

			return new ResponseEntity<List<EmployeeResponse>>(employeeResponses, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Some Thing wrong", HttpStatus.UNAUTHORIZED);
		}

	}

// =====================================================================================

	// This Method Are Handle The Update The Employee Details

	@Override
	public ResponseEntity<?> updateEmployeeDetails(EmployeeResponse employeeResponse) {
		Employee employee = employeeRepo.findById(employeeResponse.getEmployeeCode()).get();
		if (employeeResponse.getEmployeeName() != null) {
			employee.setEmployeeName(employeeResponse.getEmployeeName());

		}
		if (employeeResponse.getContact().getMobileNumber() != null) {

			employee.getContact().setMobileNumber(employeeResponse.getContact().getMobileNumber());
			employee.setContact(employee.getContact());
		}
		if (employeeResponse.getAddressResponse().getCity() != null) {

			employee.getAddressRecord().setCity(employeeResponse.getAddressResponse().getCity());
		}
		if (employeeResponse.getAddressResponse().getStreet() != null) {

			employee.getAddressRecord().setStreet(employeeResponse.getAddressResponse().getStreet());
		}
		if (employeeResponse.getAddressResponse().getState() != null) {

			employee.getAddressRecord().setState(employeeResponse.getAddressResponse().getState());
		}
		if (employeeResponse.getAddressResponse().getZipcode() != null) {

			employee.getAddressRecord().setZipcode(employeeResponse.getAddressResponse().getZipcode());
		}
		employeeRepo.save(employee);
		return new ResponseEntity<>("Employee Record Updated Successfully", HttpStatus.OK);
	}

	
// =====================================================================================
	
	// THIS METHOD ARE RESPONSIBLE TO HANDLE THE EMPLOYEE RECORD DELETE OPERATION
	
	@Override
	public String deleteEmployeeRecord(EmployeeResponse employeeResponse) {
		Contact contact = contactRepo.findByEmailIdIgnoreCase(employeeResponse.getContact().getEmailId());
		Employee employee = employeeRepo.findByEmployeeIdAndContact(employeeResponse.getEmployeeCode(),contact);
		if(employee!=null) {
			employeeRepo.delete(employee);
			return "Employee Has Been Deleted Succesfully";
		}
		else {
			return "Something is wrong";
		}
		

	}

}
