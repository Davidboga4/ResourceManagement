package com.RMP.resource_management.Repository;
import com.RMP.resource_management.Model.FormDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormDetails, Long>{

}
