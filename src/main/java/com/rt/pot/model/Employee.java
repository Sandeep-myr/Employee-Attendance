package com.rt.pot.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pot_employee_details")
public class Employee {

	@Id
	@SequenceGenerator(name = "employee_id", sequenceName = "employee_id", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(generator = "employee_id", strategy = GenerationType.SEQUENCE)
	private Integer employeeId;

	@Column(name = "employee_name", nullable = false)
	private String employeeName;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id", referencedColumnName = "contactId")
	private Contact contact;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private AddressRecord addressRecord;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractor_id", referencedColumnName = "contactorId")
	private Contractor contractor;
	
	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Attandance> attandance; 
}
