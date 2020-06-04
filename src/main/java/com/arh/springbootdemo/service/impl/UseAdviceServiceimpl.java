package com.arh.springbootdemo.service.impl;

import com.arh.springbootdemo.service.IUseAdviceService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/4
 **/
@Service
public class UseAdviceServiceimpl implements IUseAdviceService {

    public void sayHello() {
        System.out.println("hello world");
    }
}
