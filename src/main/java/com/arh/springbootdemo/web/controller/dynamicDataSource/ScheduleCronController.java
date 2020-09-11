package com.arh.springbootdemo.web.controller.dynamicDataSource;

import com.arh.springbootdemo.service.IScheduleCronService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@ConditionalOnProperty(name = "use.dynamicDataSource", havingValue = "true", matchIfMissing = false)
@RestController
@RequestMapping("/dynamicDataSource/sc")
public class ScheduleCronController {

    @Resource(name = "scheduleCronServiceDynamicSourceImpl")
    private IScheduleCronService scheduleCronService;


    @RequestMapping("/addDefault")
    public void addDefault(){
        scheduleCronService.addDefaultScheduleCron();
    }

}
