package com.RMP.resource_management.Service;

import java.util.Collections;
import java.util.List;

import com.RMP.resource_management.Model.Employee;
import com.RMP.resource_management.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = (List<Employee>) employeeRepository.findAll();
        return list;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getEmployeesByTrack(String subSkill) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Employee> filterResults = new java.util.ArrayList<>(Collections.emptyList());
        if(subSkill.equalsIgnoreCase("All")){
            filterResults = employeeList;
        }
        else if (!employeeList.isEmpty()) {
            for (Employee emp : employeeList) {
                if (emp.getSub_Set().equalsIgnoreCase(subSkill)) {
                    filterResults.add(emp);
                }
            }
        }
        return filterResults;
    }


    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.employeeRepository.findAll(pageable);
    }
}
