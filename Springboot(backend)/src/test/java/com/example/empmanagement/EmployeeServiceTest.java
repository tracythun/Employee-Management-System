package com.example.empmanagement;

import static org.junit.Assert.assertEquals
;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.empmanagement.model.Employee;
import com.example.empmanagement.repository.EmployeeRepository;
import com.example.empmanagement.services.EmployeeService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


@SpringBootTest
class EmployeeServiceTest {

	 @Mock
     private EmployeeRepository empRepository;
	
	 @Autowired
	 private EmployeeService empService;
	 
	 @Before
	 public void setUp() {
		 
	    MockitoAnnotations.initMocks(this);
	 }
	 
	//Test method to get all employees
	 @Test
	 public void testGetAllEmployees() {
		 
	    //create mock data to return data
		List<Employee> mockEmployees = Arrays.asList(
		     new Employee("e0001","hpotter","Harry Potter",1234.00),
		     new Employee("e0002","rwesley","Ron Weasley",19234.50),
		     new Employee("e0003","ssnape","Severus Snape",4000.00),
		     new Employee("e0004","rhagrid","Rubeus Hagrid",3999.99),
		     new Employee("e0005","voldemort","Lord Voldemort",523.40),
		     new Employee("e0006","gwesley","Ginny Weasley",4000.00),
		     new Employee("e0007","hgranger","Hermione Granger",0.00),
		     new Employee("e0008","adumbledore","Albus Dumbledore",34.23),
		     new Employee("e0009","dmalfoy","Draco Malfoy",34234.50),
		     new Employee("e0010","basilisk","Basilisk",-23.43)
		);
	        
	    when(empRepository.findAll()).thenReturn(mockEmployees);
		            
	    List<Employee> actualEmployees = empService.getAllEmployees();	    
	 }
	 
	   //Test method for getEmployeeById
		@Test
		public void testGetEmployeeById() {
		Employee mockEmployee = new Employee("e0001","hpotter","Harry Potter",1234.00);
		when(empRepository.getById("e0001")).thenReturn(mockEmployee);
			
		     Employee actualEmployee = empService.getEmployeeById("e0001");
		     
		     assertTrue(actualEmployee != null);
		     assertTrue(actualEmployee.getId().equals(mockEmployee.getId()));
		     assertTrue(actualEmployee.getName().equals(mockEmployee.getName()));
		     assertTrue(actualEmployee.getLogin().equals(mockEmployee.getLogin()));
		     assertTrue(actualEmployee.getSalary() == mockEmployee.getSalary());
		}
		
	 //test method for addEmployee
	 @Test
	 public void testAddEmployee() {
		  Employee mockEmployee = new Employee("e0011","test","Testing",400.00);
		  when(empRepository.save(mockEmployee)).thenReturn(mockEmployee);
		   Employee actualEmployee = empService.addEmployee(mockEmployee);
	 }
	 
	 
	//Test method for updateEmployee
	@Test
	public void testUpdateEmployee() {
		Employee existingEmployee = new Employee("e0001", "hpotter","Harry Potter",1234.00);
	    Employee updatedEmployee = new Employee("e0001", "jsmith","Joh Smith", 6000.00);
	    
	    when(empRepository.findById("e0001")).thenReturn(java.util.Optional.of(existingEmployee));
        when(empRepository.save(existingEmployee)).thenReturn(updatedEmployee);

        Employee actualUpdatedEmployee = empService.updateEmployee("e0001", updatedEmployee);
        assertTrue(actualUpdatedEmployee != null);
        assertTrue(actualUpdatedEmployee.getName().equals(updatedEmployee.getName()));
        assertTrue(actualUpdatedEmployee.getLogin().equals(updatedEmployee.getLogin()));
        assertTrue(actualUpdatedEmployee.getSalary() == updatedEmployee.getSalary());
	}

	 //test method for delete employee by id 
		@Test
		public void testDeleteEmployeeByID() {
			Employee mockEmployee = new Employee();
			when(empRepository.getById("e0007")).thenReturn(mockEmployee);
			doNothing().when(empRepository).deleteById("e0007");
			boolean deleted = empService.deleteEmployeeByID("e0007");
			assertTrue(deleted);
	 } 
	 
		//test method for upload csv
		@Test
	    public void testProcessCsvFile() throws IOException {
	        String csvData = "id,login, name, salary\ne0012,john,John,400.00\ne0013,test,Test,10.00\n";
	        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
	
	        List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
	     
	        CsvToBean<Employee> csvToBeanMock = mock(CsvToBean.class);
	        when(csvToBeanMock.parse()).thenReturn(Arrays.asList(new Employee(), new Employee()));
	        
	        CsvToBeanBuilder<Employee> csvToBeanBuilderMock = mock(CsvToBeanBuilder.class);
	        when(csvToBeanBuilderMock.withType(Employee.class)).thenReturn(csvToBeanBuilderMock);
	        when(csvToBeanBuilderMock.withIgnoreLeadingWhiteSpace(true)).thenReturn(csvToBeanBuilderMock);
	        when(csvToBeanBuilderMock.build()).thenReturn(csvToBeanMock);
	
	        empService.processCsvFile(inputStream);

	    }	
   
}
