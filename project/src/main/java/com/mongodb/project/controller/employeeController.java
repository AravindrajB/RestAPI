package com.mongodb.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.project.entity.employeeEntity;
import com.mongodb.project.service.employeeService;
import com.mongodb.project.serviceImp.employeeServiceImp;

@RestController
@RequestMapping("/crud")
public class employeeController extends employeeServiceImp {

	@Autowired
	employeeService employeeservice;

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody employeeEntity entity) {

		return employeeservice.addEmployee(entity);
	}

	@GetMapping("/getAllEmployee")
	public List<employeeEntity> getAllEmployee() {

		return employeeservice.getAllEmployee();

	}

	@GetMapping("/getEmployee/{emp_id}")
	public Optional<employeeEntity> getEmployee(int emp_id) {

		return employeeservice.getEmployee(emp_id);

	}

	@PutMapping("/updateEmployee/{emp_id}")
	public ResponseEntity<String> updateEmployee(int emp_id, employeeEntity entity) {
		return employeeservice.updateEmployee(emp_id, entity);
	}

	@DeleteMapping("/removeEmployee/{emp_id}")
	public ResponseEntity<String> removeEmployee(int emp_id) {
		return employeeservice.removeEmployee(emp_id);
	}

}
