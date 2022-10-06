package com.RMP.resource_management;
import java.util.List;

 

import org.springframework.data.domain.Page;

 


public interface EmployeeService 
{
List<Employee> getAllEmployees();
void saveEmployee(Employee employee);
void deleteEmployeeById(long id);
Page<Employee> findPaginated(int pageNo, int pageSize);
List<Employee> getEmployeesByTrack(String firstName);
//public List<Employee> getByKeyword(String keyword);
}
