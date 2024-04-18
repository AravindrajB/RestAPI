package com.mongodb.project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RestAPI")
public class employeeEntity {

	@Id
	private int emp_id;
	private String emp_name;
	private String emp_role;
	private long emp_salary;
	private boolean status;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_role() {
		return emp_role;
	}

	public void setEmp_role(String emp_role) {
		this.emp_role = emp_role;
	}

	public long getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(long emp_salary) {
		this.emp_salary = emp_salary;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
