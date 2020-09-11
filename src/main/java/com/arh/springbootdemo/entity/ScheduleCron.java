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
@Table(name = "T_SCHEDULE_CRON")
public class ScheduleCron extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Integer id;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_CLASS_NAME")
    private String className;

    @Column(name = "F_CRON_EXPRESSION")
    private String cronExpression;

    @Column(name = "F_TASK_EXPLAIN")
    private String taskExplain;

    @Column(name = "F_STATUS")
    private Integer status;

    @Column(name = "F_EXCEPTION_MSG")
    private String exceptionMsg;

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

}
