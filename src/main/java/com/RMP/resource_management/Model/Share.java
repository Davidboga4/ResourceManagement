package com.RMP.resource_management.Model;

import javax.persistence.*;

@Entity
@Table(name = "share")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long employee_id;

    @Column
    private long manager_id;

    public Share() {}

    public Share(long employee_id, long manager_id) {
        this.employee_id = employee_id;
        this.manager_id = manager_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public long getManager_id() {
        return manager_id;
    }

    public void setManager_id(long manager_id) {
        this.manager_id = manager_id;
    }
}
