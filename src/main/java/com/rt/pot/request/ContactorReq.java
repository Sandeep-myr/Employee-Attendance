package com.rt.pot.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ContactorReq {

	private String contractorName;

	private LocalDate birthDate;
	
	private  ContactReq contactReq;
	
	private AddressReq addressReq;
	
	private String password;
	
	
}
