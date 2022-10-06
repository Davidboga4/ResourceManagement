package com.RMP.resource_management;

 

import java.util.Collections;


 

import java.util.List;

 

 

//import java.util.Optional;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

 


@Service
public class EmployeeServiceimp implements EmployeeService {

 

@Autowired
private EmployeeRepository employeeRepository;

 

@Override
public List<Employee> getAllEmployees() {
    List<Employee> list =  (List<Employee>)employeeRepository.findAll();
    return list;
//return employeeRepository.findAll();
}

 


/*@Override
public List<Employee> listAll(String SkillSet) {
    if (SkillSet != null) {
        return employeeRepository.search(SkillSet);
    }
    return employeeRepository.findAll();
}
@Override
public List<Employee> getByKeyword(String keyword)
{
      return employeeRepository.findBykeyword(keyword);
}*/

 

@Override
public List<Employee> getEmployeesByTrack(String firstName) {
    System.out.println(firstName);
    List<Employee> optional = employeeRepository.findAll();
    List<Employee> temp = new java.util.ArrayList<>(Collections.emptyList());
    if (!optional.isEmpty()) {
        for (Employee emp : optional) {
            if(emp.getSkillset().toLowerCase().equals(firstName.toLowerCase())){
                temp.add(emp);
//                System.out.println(emp.getFirstName());
            }
        }
    }
//    else {
//        throw new RuntimeException(" Employee not found for id :: ");
//    }
    return temp;
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




/*@Override
public void deleteEmployeeById(long id) {
	// TODO Auto-generated method stub
	
}

 

/*@Override
public List<Employee> findBySkillSet(String skillSet) {
    return null;
}*/
}
