package com.RMP.resource_management.Service;

import com.RMP.resource_management.Model.Share;
import com.RMP.resource_management.Repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareRepository pr;

    @Override
    public List<Share> getAllShareDetails() {
        return pr.findAll();
    }

    @Override
    public Share getShareDetails(Long id) {
        return pr.findById(id).orElse(null);
    }

    @Override
    public void saveSharedDetails(Share share) {
        this.pr.save(share);

    }

    @Override
    public void deleteShareById(long id) {
        this.pr.deleteById(id);
    }

}
