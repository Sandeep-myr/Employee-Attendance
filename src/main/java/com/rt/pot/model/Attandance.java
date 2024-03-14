package com.rt.pot.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.rt.pot.model.status.AttendanceStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter	
@Setter
@Table(name="pot_employee_attendance")
public class Attandance {
	
	@Id
	@SequenceGenerator(name = "attandance_id",sequenceName = "attandance_id",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(generator = "attandance_id",strategy =  GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="date",nullable = false)
	private LocalDate date;
	
	@Column(name="check_In_Time",nullable = false)
	private LocalTime checkInTime;
	
	@Column(name="check_out_Time",nullable = false)
	private LocalTime checkOutTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "attendance_status")
	private AttendanceStatus attendanceStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id",referencedColumnName = "employeeId")
	private Employee  employee;
	
	
	
}
