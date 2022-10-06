package com.RMP.resource_management.Service;

import com.RMP.resource_management.Model.FormDetails;
import com.RMP.resource_management.Repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Override
    public List<FormDetails> getAllFormDetails() {
        return formRepository.findAll();
    }

    @Override
    public void saveFormDetails(FormDetails formDetail) {
        this.formRepository.save(formDetail);
    }

    @Override
    public void deleteFormById(long id) {
        this.formRepository.deleteById(id);
    }

    @Override
    public FormDetails getFormDetailsById(Long id) {
        return formRepository.findById(id).orElse(null);
    }
}
