package com.RMP.resource_management;
import java.util.List;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

 

     /*@Query(value = "select * from employees  WHERE employees.SkillSet like %:keyword%", nativeQuery = true)
     List<Employee> findBykeyword(@Param("keyword") String keyword);*/
        //List<Employee> findBySkillSet(String firstName);

 

    
}