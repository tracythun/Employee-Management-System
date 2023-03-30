package com.example.empmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.empmanagement.model.Employee;
import com.example.empmanagement.services.EmployeeService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping
public class EmployeeController{
	
	@Autowired
	private EmployeeService empService;
	
	// Add new employee
    @PostMapping("/users/{id}")
    public Employee addEmployee(@RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }
    
	// Get all employee
    @GetMapping("/users")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }
    
    //Get each employee by Id\
    @GetMapping("/users/{id}")
    public Employee getEmployeeById(@PathVariable String id){
    	return empService.getEmployeeById(id);
    }
   
    // Update employee
    @PatchMapping("/users/{id}")
    public Employee updateEMployee(@PathVariable String id, @RequestBody Employee employee) {
        return empService.updateEmployee(id, employee);
    }
    
    // Delete employee
    @DeleteMapping("/users/{id}")
    public boolean deleteEmployeeByID(@PathVariable String id) {
        return empService.deleteEmployeeByID(id);
    }
    
    // To Upload File
    @PostMapping("/users/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
    	  try {
    		  empService.processCsvFile(file.getInputStream());
              return ResponseEntity.ok().body("File uploaded successfully");
          } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
          }
    }


}