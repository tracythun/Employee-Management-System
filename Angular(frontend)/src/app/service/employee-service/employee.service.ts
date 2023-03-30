import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  baseUrl : string;

  constructor(private http : HttpClient) {
    this.baseUrl = 'http://localhost:8081/users';
   }

   addEmployee(emp : Employee): Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl,emp);
  }

   getAllEmployee(): Observable<Employee[]>{
     return this.http.get<Employee[]>(this.baseUrl);
   }

   updateEmployee(emp: Employee, id: String) : Observable<Employee>{
     const url=`${this.baseUrl}/${id}`;
     return this.http.patch<Employee>(url,emp);
   }

   deleteEmployee(emp : Employee) : Observable<Employee> {
     return this.http.delete<Employee>(this.baseUrl+'/'+emp.id);
   }
  
   uploadFile(file: File) {
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post(this.baseUrl, formData);
  }
 }