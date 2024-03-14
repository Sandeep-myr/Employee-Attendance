package com.rt.pot.service;

import com.rt.pot.request.AttendanceReq;
import com.rt.pot.request.EmployeeReq;

public interface IEmployeeService {

	public String employeeRegistration(EmployeeReq employeeReq);

	public String employeeCheckIn(AttendanceReq attendanceReq);

	public String employeeCheckOut(AttendanceReq attendanceReq);

}
