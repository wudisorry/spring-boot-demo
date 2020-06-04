package com.arh.springbootdemo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AdviceTest {

    @Around("within(com.arh.springbootdemo.service.impl.UseAdviceServiceimpl)")
    public Object round(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        try {
            System.out.println("come in AdviceTest.round()");
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("系统异常");
        } finally {
        }
    }

}
