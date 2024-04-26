package com.mongodb.project.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mongodb.project.dto.employeeDto;

@Service
public interface employeeService {

	public employeeDto addEmployee(employeeDto request);

	public List<employeeDto> getAllEmployee();

	public employeeDto getEmployee(String Id);

	public employeeDto updateEmployee(employeeDto employeeDto, String Id);

	public employeeDto removeEmployee(String Id);

}
