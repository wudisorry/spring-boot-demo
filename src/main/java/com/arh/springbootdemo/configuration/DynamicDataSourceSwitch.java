package com.arh.springbootdemo.configuration;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSourceSwitch {
    String dataSourceKey() default DynamicDataSourceContext.BUSINESS_KEY;
}
