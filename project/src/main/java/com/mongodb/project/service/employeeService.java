package com.mongodb.project.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mongodb.project.dto.employeeDto;


@Service
public interface employeeService {

	public employeeDto addEmployee(employeeDto request) throws Exception;

	public List<employeeDto> getAllEmployee()throws Exception;

	public employeeDto getEmployee(String Id) throws Exception;

	public employeeDto updateEmployee(employeeDto employeeDto, String Id) throws Exception;

	public employeeDto removeEmployee(String Id) throws Exception;

	public List<employeeDto> searchUser(String keyword) throws Exception;

	public List<employeeDto> searchUserByDynamicField(String field, String key) throws Exception;
}
