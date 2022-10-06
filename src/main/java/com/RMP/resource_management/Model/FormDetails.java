package com.RMP.resource_management.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "formdetails")
public class FormDetails {
//    public FormDetails() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "EmpID")
    private long EmpID;

    @Column(name = "blockedBy")
    private String blockedBy;

    @Column(name = "project")
    private String project;

    @Column(name = "billStarted")
    private String billStarted;

    @Column(name = "billEnded")
    private String billEnded;

    @Column(name = "billRate")
    private String billRate;

    @Column(name = "jobDesc")
    private String jobDesc;

    @Column(name = "location")
    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmpID() {
        return EmpID;
    }

    public void setEmpID(long empID) {
        EmpID = empID;
    }

    public String getBillStarted() {
        return billStarted;
    }

    public void setBillStarted(String billStarted) {
        this.billStarted = billStarted;
    }

    public String getBillEnded() {
        return billEnded;
    }

    public void setBillEnded(String billEnded) {
        this.billEnded = billEnded;
    }

    public String getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(String blockedBy) {
        this.blockedBy = blockedBy;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBillRate() {
        return billRate;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
