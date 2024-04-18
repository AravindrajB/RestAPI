package com.mongodb.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.project.service.employeeService;
import com.mongodb.project.serviceImp.employeeServiceImp;

@RestController
@RequestMapping("/crud")
public class employeeController extends employeeServiceImp {

	@Autowired
	employeeService employeeservice;

}
