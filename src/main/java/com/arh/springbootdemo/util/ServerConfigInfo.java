package com.arh.springbootdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/23
 **/
@Component
public class ServerConfigInfo {

    public static final Logger logger = LoggerFactory.getLogger(ServerConfigInfo.class);

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private String port;

    public String getPort() {
        return port;
    }

    public String getPortFromEnv() {
        return env.getProperty("local.server.port");
    }

    public String getIp() {
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        String ip = localHost.getHostAddress();  // 返回格式为：xxx.xxx.xxx
        // localHost.getHostName() 一般是返回电脑用户名
        return ip;
    }
}
