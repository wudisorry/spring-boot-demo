package com.arh.springbootdemo.exception;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/
public class MyServiceException extends RuntimeException {
    public MyServiceException() {
        super();
    }

    public MyServiceException(String message) {
        super(message);
    }

    public MyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyServiceException(Throwable cause) {
        super(cause);
    }

    protected MyServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
