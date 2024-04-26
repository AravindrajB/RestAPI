package com.mongodb.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.project.dto.employeeDto;
import com.mongodb.project.service.employeeService;
import com.mongodb.project.serviceImp.employeeServiceImp;


@RestController
@RequestMapping("/crud")
public class employeeController extends employeeServiceImp {

	@Autowired
	employeeService employeeservice;

	@PostMapping("/addEmployee")
	public employeeDto addEmployee(@RequestBody employeeDto request) {

		return employeeservice.addEmployee(request);
	}

	@GetMapping("/getAllEmployee")
	public List<employeeDto> getAllEmployee() {

		return employeeservice.getAllEmployee();

	}

	@GetMapping("/getEmployee/{Id}")
	public employeeDto getEmployee(@PathVariable("Id") String Id) {

		return employeeservice.getEmployee(Id);

	}

	@PutMapping("/updateEmployee/{Id}")
	public employeeDto updateEmployee(@RequestBody employeeDto employeeDto, @PathVariable String Id) {
		return employeeservice.updateEmployee(employeeDto, Id);
	}

	@DeleteMapping("/removeEmployee/{Id}")
	public employeeDto removeEmployee(@PathVariable String Id) {
		return employeeservice.removeEmployee(Id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlevalidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String errormsg = error.getDefaultMessage();
			errors.put(fieldname, errormsg);
		});

		return errors;
	}

}
