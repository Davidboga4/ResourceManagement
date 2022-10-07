package com.RMP.resource_management.Service;

import com.RMP.resource_management.Model.Manager;
import com.RMP.resource_management.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository pr;

    @Override
    public List<Manager> getAllManagerDetails() {
        return pr.findAll();
    }

    @Override
    public Manager getManagerDetails(Long id) {
        return pr.findById(id).orElse(null);
    }

    @Override
    public void saveManager(Manager manager) {
        this.pr.save(manager);

    }

    @Override
    public void deleteManagerById(long id) {
        this.pr.deleteById(id);
    }

}
