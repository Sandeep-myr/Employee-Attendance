package com.rt.pot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter	
@Setter
@Table(name="pot_contractor_details")
public class Contractor {
	
	@Id
	@SequenceGenerator(name = "contractor_id",sequenceName = "contractor_id",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(generator = "contractor_id",strategy =  GenerationType.SEQUENCE)
	private Integer contactorId;
	
	@NotNull
	@Column(name="employee_name")
	private String contractorName;

	@Column(name="birth_date",nullable = false)
	private LocalDate birthDate;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id",referencedColumnName = "contactId")
	private Contact contact;

	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName = "addressId")
	private AddressRecord address;
	
	
	@OneToMany(mappedBy = "contractor",cascade = CascadeType.ALL)
	private List<Employee> employee;
	
	
}
