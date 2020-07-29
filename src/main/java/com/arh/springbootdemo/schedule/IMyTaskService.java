package com.arh.springbootdemo.schedule;

import com.arh.springbootdemo.entity.ScheduleCron;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/29
 **/
public interface IMyTaskService {

    void execute(ScheduleCron scheduleCron);
}
