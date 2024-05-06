package com.mongodb.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.mongodb.project.entity.employeeEntity;

@Repository
public interface employeeRepo extends MongoRepository<employeeEntity, String> {

	public employeeEntity findByEmpId(Object string);

//	public List<employeeEntity> findByKeyword(String keyword);

	@Query("{$text: {$search: ?0}}")
	public List<employeeEntity> findAllByTextIndex(String keyword);

}
