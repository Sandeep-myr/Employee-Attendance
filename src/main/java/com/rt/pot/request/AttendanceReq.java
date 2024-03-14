package com.rt.pot.request;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AttendanceReq {

	@NotNull
	private Integer employeeCode;
	
	@NotNull(message="This Field are not null")
	private LocalDate date; 

	@ApiModelProperty(value = "Check out time", example = "HH:MM:SS", required = true, dataType = "java.lang.String")
	@NotNull(message = "Proper Check in Time Given ")
	private LocalTime checkInTime;
	@ApiModelProperty(value = "Check out time", example = "HH:MM:SS", required = true, dataType = "java.lang.String")
	@NotNull(message = "Proper Check out Time Given ")
	private LocalTime checkoutTime;
}
