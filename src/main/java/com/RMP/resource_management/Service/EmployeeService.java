package com.RMP.resource_management.Service;

import java.util.List;


import com.RMP.resource_management.Model.Employee;
import org.springframework.data.domain.Page;


public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    void deleteEmployeeById(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize);

    Employee getEmployeeById(Long id);
    List<Employee> getEmployeesByTrack(String subSkill);
}
