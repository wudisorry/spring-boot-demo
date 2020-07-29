package com.arh.springbootdemo.dao;

import com.arh.springbootdemo.entity.ScheduleCron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/29
 **/
public interface IScheduleCronRepository extends JpaRepository<ScheduleCron, Integer> {


    List<ScheduleCron> findByStatusOrStatusNull(Integer status);


}
