package com.arh.springbootdemo.service;

import java.util.Map;

public interface ISendRequestService {
    void singleDoGet(String url, Map<String, String> paramMap);

    void singleDoPost(String url, Map<String, String> paramMap);

    void testGet() throws Exception;
}
