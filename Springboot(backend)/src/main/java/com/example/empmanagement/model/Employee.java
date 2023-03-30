package com.example.empmanagement.model;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@NoArgsConstructor
@Table(name="employees")
public class Employee{
	 
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")	
    public String id;
	public String login;
	public String name;
	
	//to ensure that the salary property will have a default of two decimal places in API output.
	@DecimalMin(value = "0.00", inclusive = true)
	@DecimalMax(value = "99999999.99", inclusive = true)
	public double salary;

	public Employee(String id, String login, String name, double sal) {
		super();
		this.id=id;
		this.login = login;
		this.name = name;
		this.salary = sal;
	}

	public Employee() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}