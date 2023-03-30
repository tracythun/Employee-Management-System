package com.example.empmanagement.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.empmanagement.model.Employee;
import com.example.empmanagement.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	
	public List<Employee> getAllEmployees() {
	        return empRepository.findAll();
	}
	
	public Employee getEmployeeById(String id) {
		return empRepository.getById(id);
	}
	
	public Employee addEmployee(Employee employee) {
		return empRepository.save(employee);
	}
	 
	public Employee updateEmployee(String id, Employee employee) {
	    Employee existing_employee = empRepository.findById(id).orElse(null);
	        
	    if(existing_employee != null) {	   
	        	existing_employee.setId(id);
	        	existing_employee.setName(employee.getName());
	        	existing_employee.setLogin(employee.getLogin());
	        	existing_employee.setSalary(employee.getSalary());
	            return  empRepository.save(existing_employee);
	    	}else  {
	        	return null;
	      }
	    }

	public boolean deleteEmployeeByID(String id) {
		Employee existing_employee=empRepository.getById(id);
		if(existing_employee!=null) {
			empRepository.deleteById(id);
			return true;
		}
		return false;
	}

	

	public void processCsvFile(InputStream inputStream)throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                .withType(Employee.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

          List<Employee> employeeList = csvToBean.parse();
            
          empRepository.saveAll(employeeList);
        }
	}
}
