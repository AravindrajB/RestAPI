package com.mongodb.project.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	@Autowired
	private MongoTemplate mongoTemp;

	// POST - ADD EMPLOYEE
	@Override
	public employeeDto addEmployee(final employeeDto employeeDto) throws Exception {

		employeeDto resultDto = new employeeDto();

		try {
			employeeEntity getEmpByEmpId = employeerepo.findByEmpId(employeeDto.getEmpId());

			employeeEntity result = new employeeEntity();

			// Validation

			if (getEmpByEmpId == null) {

				if (!employeeDto.getEmpId().isEmpty() && employeeDto.getEmpId() != null
						&& !employeeDto.getEmpName().isEmpty() && employeeDto.getEmpName() != null) {

					result.setEmpId(employeeDto.getEmpId());
					result.setEmpName(employeeDto.getEmpName());
					result.setEmpRole(employeeDto.getEmpRole());
					result.setEmpSalary(employeeDto.getEmpSalary());
					employeerepo.save(result);
					resultDto.setStatus(0); // No error
					resultDto.setMsg("Employee ID: " + employeeDto.getEmpId() + " Saved Suceessfully");

				} else {

					resultDto.setStatus(1); // has error
					resultDto.setMsg("EmpId & EmpName is mandatory!");
				}

			} else {

				resultDto.setStatus(1); // has error
				String currentId = employeeDto.getEmpId();
				resultDto.setMsg("Employee ID:" + currentId + " is Already Exists");

			}

		}

		catch (NullPointerException e) {
			resultDto.setStatus(1);
			resultDto.setMsg("EmpId & EmpName is should be not null");

		}

		catch (Exception e) {

			resultDto.setStatus(1); // has error
			resultDto.setMsg(e.getMessage());
		}

		return resultDto;
	}

	// GET - ALL EMPLOYEE
	@Override
	public List<employeeDto> getAllEmployee() throws Exception {

		List<employeeEntity> employeeEntity = employeerepo.findAll();
		List<employeeDto> employeeDto = new ArrayList<employeeDto>();
		for (employeeEntity employee : employeeEntity) {
			employeeDto.add(this.getemployeeDto(employee));
		}

		return employeeDto;

	}

	private employeeDto getemployeeDto(employeeEntity employee) throws Exception {

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
	public employeeDto getEmployee(final String Id) throws Exception {

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
	public employeeDto updateEmployee(final employeeDto employeeDto, final String Id) throws Exception {

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
	public employeeDto removeEmployee(final String Id) throws Exception {

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

	@Override
	public List<employeeDto> searchUser(final String keyword) throws Exception {

		Criteria criteria = new Criteria();
//		    criteria.orOperator(
//		        Criteria.where("empRole").regex("(?i).*" + keyword + ".*"),
//		        Criteria.where("empName").regex("(?i).*" + keyword + ".*"),
//		        Criteria.where("empSalary").regex("(?i).*" + keyword + ".*")
//		        // Add more criteria for other fields as needed
//		    );

//		    AggregationOperation match = Aggregation.match(Criteria.where("empRole").regex("(?i).*" + keyword + ".*"));
//
//  		AggregationOperation match = Aggregation.match(criteria);
//		    Aggregation aggregation = Aggregation.newAggregation(match);

		Query query = new Query();
		query.addCriteria(criteria.orOperator(Criteria.where("empRole").regex("(?i).*" + keyword + ".*"),
				Criteria.where("empId").regex("(?i).*" + keyword + ".*")));

		List<employeeEntity> employeeList = mongoTemp.find(query, employeeEntity.class);

//		    		mongoTemp.aggregate(aggregation, 
//		    		"RestAPI", employeeEntity.class).getMappedResults();

		List<employeeDto> resultDto = new ArrayList<>();
		for (employeeEntity employee : employeeList) {
			resultDto.add(this.getemployeeDto(employee));
		}

		return resultDto;

	}

	@Override
	public List<employeeDto> searchUserByDynamicField(String field, String key) throws Exception {
		
		Criteria criteria = new Criteria();
		Query query = new Query();
		query.addCriteria(criteria.orOperator(Criteria.where(field).regex("(?i).*" + key + ".*")));
		List<employeeEntity> employeeList = mongoTemp.find(query, employeeEntity.class);
		List<employeeDto> resultDto = new ArrayList<>();
		for (employeeEntity employee : employeeList) {
			resultDto.add(this.getemployeeDto(employee));
		}

		return resultDto;
	}

}
