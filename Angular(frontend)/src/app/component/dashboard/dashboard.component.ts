import { Component, OnInit} from '@angular/core';
import { FormGroup, FormBuilder} from '@angular/forms'
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/service/employee-service/employee.service';
import { HttpClient } from '@angular/common/http';
import { Sort} from '@angular/material/sort';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  empDetail !: FormGroup;
  empObj : Employee = new Employee();
  empList : Employee[] = [];
  sortedData : Employee[];
  
  constructor(private formBuilder : FormBuilder, private empService : EmployeeService, private http: HttpClient) {
    this.sortedData = this.empList.slice();
  }

  ngOnInit(): void {
  
    this.getAllEmployee();

    this.empDetail = this.formBuilder.group({
      id : [''],
      login : [''],
      name : [''],
      salary: [''],
    });    
  }

  addEmployee() {
    console.log(this.empDetail);
    this.empObj.id = this.empDetail.value.id;
    this.empObj.login = this.empDetail.value.login;
    this.empObj.name = this.empDetail.value.name;
    this.empObj.salary = this.empDetail.value.salary;

    this.empService.addEmployee(this.empObj).subscribe(res=>{
        console.log(res);
        this.getAllEmployee();
    },err=>{
        console.log(err);
    });
  }

  getAllEmployee() {
    this.empService.getAllEmployee().subscribe(res=>{
        this.sortedData = res;
    },err=>{
      console.log("error while fetching data.")
    });
  }

  editEmployee(emp : Employee) {
    this.empDetail.controls['id'].setValue(emp.id);
    this.empDetail.controls['login'].setValue(emp.login);
    this.empDetail.controls['name'].setValue(emp.name);
    this.empDetail.controls['salary'].setValue(emp.salary);
  }

  updateEmployee() {
   
    const id = this.empDetail.value.id;
    
    this.empObj.login = this.empDetail.value.login;
    this.empObj.name = this.empDetail.value.name;
    this.empObj.salary = this.empDetail.value.salary;

    this.empService.updateEmployee(this.empObj,id).subscribe(res=>{
      console.log(res);
      this.getAllEmployee();
    },err=>{
      console.log(err);
    })

  }

  confirmDelete(employee: any): void {
    if (window.confirm(`Are you sure you want to delete ${employee.name}?`)) {
        this.deleteEmployee(employee);
    }
  }

  deleteEmployee(emp : Employee) {

    this.empService.deleteEmployee(emp).subscribe(res=>{
      console.log(res);
      alert('Employee deleted successfully');
      this.getAllEmployee();
    },err => {
      console.log(err);
    });

  }

  sortData(sort: Sort) {
    const data = this.sortedData.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
  }

  this.sortedData = data.sort((a, b) => {
    const isAsc = sort.direction === 'asc';
    switch (sort.active) {
      case 'id':
        return compare(a.id, b.id, isAsc);
      case 'login':
        return compare(a.login, b.login, isAsc);
      case 'name':
        return compare(a.name, b.name, isAsc);
      case 'salary':
        return compare(a.salary, b.salary, isAsc);
      default:
        return 0;
    }
  });
}
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
