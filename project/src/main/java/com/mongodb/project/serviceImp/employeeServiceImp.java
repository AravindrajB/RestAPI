package com.mongodb.project.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.project.entity.employeeEntity;
import com.mongodb.project.repository.employeeRepo;
import com.mongodb.project.service.employeeService;

@Service
public class employeeServiceImp implements employeeService {

	@Autowired
	private employeeRepo employeerepo;

	@Override
	public String addEmployee(@RequestBody employeeEntity entity) {

		employeeEntity result = employeerepo.save(entity);

		if (result != null) {

			return "New Record Added";

		} else {

			return "Record not Added";
		}

	}

	@Override
	public List<employeeEntity> getAllEmployee() {

		List<employeeEntity> allEmployee = employeerepo.findAll();

		return allEmployee;

	}

	@Override
	public Optional<employeeEntity> getEmployee(int emp_id) {

		Optional<employeeEntity> employee = employeerepo.findById(emp_id);

		return employee;
	}

	@Override
	public ResponseEntity<String> updateEmployee(int emp_id, employeeEntity entity) {

		Optional<employeeEntity> result = employeerepo.findById(emp_id);

		if (result.isPresent()) {

			employeeEntity empEntity = result.get();
			empEntity.setEmp_name(entity.getEmp_name());
			empEntity.setEmp_role(entity.getEmp_role());
			empEntity.setEmp_salary(entity.getEmp_salary());
			empEntity.setStatus(entity.isStatus());
			employeerepo.save(empEntity);

			return ResponseEntity.status(HttpStatus.OK).body("Record Updated");

		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
		}

	}

	@Override
	public ResponseEntity<String> removeEmployee(int emp_id) {

		Optional<employeeEntity> employee = employeerepo.findById(emp_id);

		if (employee.isPresent()) {

			employeerepo.deleteById(emp_id);

			return ResponseEntity.status(HttpStatus.OK).body("Record Deleted");

		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
		}

	}

}
