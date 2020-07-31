package com.arh.springbootdemo.web.controller.test;

import com.arh.springbootdemo.configuration.WebSocketConfig;
import com.arh.springbootdemo.service.IUseAdviceService;
import com.arh.springbootdemo.util.ApplicationContextUtil;
import com.arh.springbootdemo.util.PropertyBeanWithAnnoConfigP;
import com.arh.springbootdemo.util.PropertyBeanWithAnnoValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chenli
 * @Date 2020/3/30
 **/
@RestController
public class TestGrammarController {

    private static final Logger logger = LoggerFactory.getLogger(TestGrammarController.class);

    @Autowired
    private PropertyBeanWithAnnoValue autoPropertyBeanWithAnnoValue;

    @Autowired
    private PropertyBeanWithAnnoConfigP autoPropertyBeanWithAnnoConfigP;

    @Autowired
    private IUseAdviceService useAdviceService;

    @Autowired
    private WebSocketConfig webSocketConfig;

    @RequestMapping("/testProperty")
    public void tp() {
        logger.debug("start test propertyBeanWithAnnoValue");
        PropertyBeanWithAnnoValue propertyBeanWithAnnoValue = new PropertyBeanWithAnnoValue();
        logger.debug(propertyBeanWithAnnoValue.getTestName());
        PropertyBeanWithAnnoValue propertyBeanWithAnnoValue2 = (PropertyBeanWithAnnoValue) ApplicationContextUtil.getAc().getBean("propertyBeanWithAnnoValue");
        logger.debug(propertyBeanWithAnnoValue2 != null ? propertyBeanWithAnnoValue2.getTestName() : "propertyBeanWithAnnoValue2为空");
        logger.debug(autoPropertyBeanWithAnnoValue.getTestName());

        logger.debug("start test propertyBeanWithAnnoConfigP");
        logger.debug(autoPropertyBeanWithAnnoConfigP.getTestName());
    }

    @RequestMapping("/testAdvice")
    public void testAdvice(){
        useAdviceService.sayHello();
    }

    @RequestMapping("/testImport")
    public void testImport(){
        //看@Configuration标注的类是否纳入spring上下文
        System.out.println(webSocketConfig);
    }

}
