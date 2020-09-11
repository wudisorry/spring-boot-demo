package com.arh.springbootdemo.service;

import com.arh.springbootdemo.entity.ScheduleCron;

import java.util.List;

public interface IScheduleCronService {

    List<ScheduleCron> queryActiveScheduleCron();

    void addDefaultScheduleCron();

    void saveOrUpdateScheduleCron(ScheduleCron scheduleCron);
}
