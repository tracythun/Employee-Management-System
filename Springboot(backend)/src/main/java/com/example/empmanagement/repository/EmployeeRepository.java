package com.example.empmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empmanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	
	
}
