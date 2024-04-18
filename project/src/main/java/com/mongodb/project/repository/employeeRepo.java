package com.mongodb.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.project.entity.employeeEntity;

@Repository
public interface employeeRepo extends MongoRepository<employeeEntity, Integer>{

}
