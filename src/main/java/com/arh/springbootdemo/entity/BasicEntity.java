package com.arh.springbootdemo.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class BasicEntity implements Serializable {

    @Column(name = "F_ADD_USER")
    private Integer addUser;

    @Column(name = "F_ADD_DATE")
    private Date addDate;

    @Column(name = "F_UPDATE_USER")
    private Integer updateUser;

    @Column(name = "F_UPDATE_DATE")
    private Date updateDate;

    public Integer getAddUser() {
        return addUser;
    }

    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
