package com.mongodb.project.service;

import java.util.List;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.mongodb.project.entity.employeeEntity;

@Service
public interface employeeService {

	public String addEmployee(@RequestBody employeeEntity entity);

	public List<employeeEntity> getAllEmployee();

	public Optional<employeeEntity> getEmployee(@PathVariable int emp_id);

	public ResponseEntity<String> updateEmployee(@PathVariable int emp_id, @RequestBody employeeEntity entity);

	public ResponseEntity<String> removeEmployee(@PathVariable int emp_id);
}
