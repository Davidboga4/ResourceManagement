package com.RMP.resource_management;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "EmpID")
    private long EmpID;
    @Column(name = "Name")
    private String Name;
    @Column(name = "RM")
    private String RM;
    @Column(name = "BAND")
    private String BAND;
    @Column(name = "DOJ")
    private String DOJ;
    @Column(name = "Skill_Set")
    private String Skill_Set;
    @Column(name = "Sub_Set")
    private String Sub_Set;
    @Column(name = "RAS_Allocation")
    private String RAS_Allocation;
    @Column(name = "Remarks")
    private String Remarks;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long EmpID) {
        this.EmpID = EmpID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getRM() {
        return RM;
    }

    public void setRM(String rM) {
        RM = rM;
    }

    public String getBAND() {
        return BAND;
    }

    public void setBAND(String Band) {
        BAND = Band;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String dOJ) {
        DOJ = dOJ;
    }

    public String getSkillset() {
        return Skill_Set;
    }

    public void setSkillset(String skillSet) {
        Skill_Set = skillSet;
    }

    public String getRAS_Allocation() {
        return RAS_Allocation;
    }

    public void setRAS_Allocation(String rAS_Allocation) {
        RAS_Allocation = rAS_Allocation;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getSub_Set() {
        return Sub_Set;
    }

    public void setSub_Set(String sub_Set) {
        Sub_Set = sub_Set;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmpID(long empID) {
        EmpID = empID;
    }

    public String getSkill_Set() {
        return Skill_Set;
    }

    public void setSkill_Set(String skill_Set) {
        Skill_Set = skill_Set;
    }
}
