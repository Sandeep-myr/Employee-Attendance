package com.rt.pot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pot_address_details")
public class AddressRecord{

	@Id
	@SequenceGenerator(name = "address_id", sequenceName = "address_id", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(generator = "address_id", strategy = GenerationType.SEQUENCE)
	private Integer addressId;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;

	@Column(name = "zipcode")
	private Integer zipcode;
	
	
	@OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
	private Contractor contractor;
}
