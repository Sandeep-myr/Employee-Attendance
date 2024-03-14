package com.rt.pot.request;

import lombok.Data;

@Data
public class EmployeeReq {

	private String employeeName;
	
	private ContactReq contactReq;
	
	private AddressReq addressReq;
	
	private String password;
	
	private  LoginReq loginReq;
}
