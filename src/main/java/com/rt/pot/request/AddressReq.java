package com.rt.pot.request;

import lombok.Data;

@Data
public class AddressReq {
	
	private String street;

	private String city;

	private String state;

	private Integer zipcode;

}
