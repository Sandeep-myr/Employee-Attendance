package com.rt.pot.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rt.pot.model.Attandance;
import com.rt.pot.model.Employee;
import com.rt.pot.model.status.AttendanceStatus;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AttandanceRepo extends JpaRepository<Attandance, Integer>{
	
	public Attandance findByEmployeeAndDate(Employee employee,LocalDate date);
	
	@Modifying
	@Query(value = "UPDATE pot_employee_attendance SET check_out_time = ?3, attendance_status = ?4 WHERE employee_id = ?1 AND date = ?2", nativeQuery = true)
	public Integer updateCheckOutAndStatus(Integer employeeId, LocalDate attendanceDate, LocalTime checkOutTime, AttendanceStatus status);
}
