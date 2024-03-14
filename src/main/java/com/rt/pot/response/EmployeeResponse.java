package com.rt.pot.response;

import lombok.Data;

@Data
public class EmployeeResponse {

	private Integer employeeCode;

	private String employeeName;

	private ContactResponse contact;

	private AddressResponse addressResponse;

}
