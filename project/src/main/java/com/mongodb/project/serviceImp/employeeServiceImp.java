package com.mongodb.project.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongodb.project.dto.employeeDto;
import com.mongodb.project.entity.employeeEntity;
import com.mongodb.project.repository.employeeRepo;
import com.mongodb.project.service.employeeService;

@Service
public class employeeServiceImp implements employeeService {

//	private static final Logger logger = LoggerFactory.getLogger(employeeServiceImp.class);

	@Autowired
	private employeeRepo employeerepo;

	// POST - ADD EMPLOYEE
	@Override
	public employeeDto addEmployee(final employeeDto employeeDto) {

		employeeDto resultDto = new employeeDto();

		employeeEntity getEmpByEmpId = employeerepo.findByEmpId(employeeDto.getEmpId());

		employeeEntity result = new employeeEntity();

		// Validation

		if (getEmpByEmpId == null) {

			result.setEmpId(employeeDto.getEmpId());
			result.setEmpName(employeeDto.getEmpName());
			result.setEmpRole(employeeDto.getEmpRole());
			result.setEmpSalary(employeeDto.getEmpSalary());
			employeerepo.save(result);
			resultDto.setStatus(0); // No error
			resultDto.setMsg("Employee Saved Suceessfully");

		} else {

			resultDto.setStatus(1); // has error
			String currentId = employeeDto.getEmpId();
			resultDto.setMsg("Employee ID:" + currentId + " is Already Exists");
		}

		return resultDto;
	}

	// GET - ALL EMPLOYEE
	@Override
	public List<employeeDto> getAllEmployee() {

		List<employeeEntity> employeeEntity = employeerepo.findAll();
		List<employeeDto> employeeDto = new ArrayList<employeeDto>();
		for (employeeEntity employee : employeeEntity) {
			employeeDto.add(this.getemployeeDto(employee));
		}

		return employeeDto;

	}

	private employeeDto getemployeeDto(employeeEntity employee) {

		employeeDto employeeDto = new employeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setEmpName(employee.getEmpName());
		employeeDto.setEmpRole(employee.getEmpRole());
		employeeDto.setEmpSalary(employee.getEmpSalary());

		return employeeDto;
	}

	// GET - SPECIFIC EMPLOYEE
	@Override
	public employeeDto getEmployee(final String Id) {

		Optional<employeeEntity> byId = employeerepo.findById(Id);
		employeeDto resultDto = new employeeDto();

		if (byId.isPresent() && !byId.isEmpty() && byId != null) {

			resultDto.setId(Id);
			resultDto.setEmpId(byId.get().getEmpId());
			resultDto.setEmpName(byId.get().getEmpName());
			resultDto.setEmpRole(byId.get().getEmpRole());
			resultDto.setEmpSalary(byId.get().getEmpSalary());
			resultDto.setStatus(0); // Found

		} else {

			resultDto.setStatus(1); // Not Found
			resultDto.setMsg("This EmpId is " + Id + " not found");

		}

		return resultDto;

	}

	// PUT - UPDATE EMPLOYEE
	@Override
	public employeeDto updateEmployee(final employeeDto employeeDto, final String Id) {

		Optional<employeeEntity> result = employeerepo.findById(Id);

		employeeDto resultDto = new employeeDto();

		if (result.isPresent() && !result.isEmpty() && result != null) {

			employeeEntity employeeEntity = result.get();
			employeeEntity.setEmpId(employeeDto.getEmpId());
			employeeEntity.setEmpName(employeeDto.getEmpName());
			employeeEntity.setEmpRole(employeeDto.getEmpRole());
			employeeEntity.setEmpSalary(employeeDto.getEmpSalary());
			employeerepo.save(employeeEntity);
			resultDto.setStatus(0); // Record Updated
			resultDto.setMsg("Record Updated: " + Id);

		} else {

			resultDto.setStatus(1); // Record Not Found
			resultDto.setMsg("Record Not In DB: " + Id);

		}

		return resultDto;

	}

	// DEL - REMOVE EMPLOYEE
	@Override
	public employeeDto removeEmployee(final String Id) {

		Optional<employeeEntity> employee = employeerepo.findById(Id);

		employeeDto resultDto = new employeeDto();

		if (employee.isPresent() && !employee.isEmpty() && employee != null) {

			employeerepo.deleteById(Id);

			resultDto.setStatus(0); // Deleted
			resultDto.setMsg("This " + Id + " is Deleted.");

		} else {

			resultDto.setStatus(1); // Record Not Found
			resultDto.setMsg("This " + Id + " is Not Found.");
		}

		return resultDto;

	}

}
