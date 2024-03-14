package com.rt.pot.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ContractorResponse {

	private String contractorName;

	private LocalDate birthDate;

	private ContactResponse contact;

	private AddressResponse addressResponse;

	private String password;

}
