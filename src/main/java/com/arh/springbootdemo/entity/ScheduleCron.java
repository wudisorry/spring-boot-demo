package com.arh.springbootdemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * 如果Column注解定义的字段名和属性名一样,会被忽略。
 * @Author chenli
 * @Date 2020/7/29
 **/
@Entity
@Table(name = "t_schedule_cron")
public class ScheduleCron implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column(name = "classname")
    private String className;

    @Column(name = "cronexpression")
    private String cronExpression;

    @Column(name = "taskexplain")
    private String taskExplain;

    @Column
    private Integer status;

    @Column(name = "exceptionmsg")
    private String exceptionMsg;

    @Column(name = "adduser")
    private Integer addUser;

    @Column(name = "adddate")
    private Date addDate;

    @Column(name = "updateuser")
    private Integer updateUser;

    @Column(name = "updatedate")
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

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
