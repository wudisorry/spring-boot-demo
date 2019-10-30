package com.arh.springbootdemo.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arh.springbootdemo.service.ISendRequestService;
import com.arh.springbootdemo.util.CollectionUtil;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/29
 * uri和url区别 https://www.jianshu.com/p/db65de31fe1b
 **/
@Service
public class SendRequestServiceImpl implements ISendRequestService {

    private static final Logger logger = LoggerFactory.getLogger(SendRequestServiceImpl.class);

    public void singleDoGet(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = null;
        httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (CollectionUtil.isNotEmpty(paramMap)) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("Authorization","");
//            RequestConfig requestConfig = RequestConfig.DEFAULT;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)//连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//请求超时时间
                    .setSocketTimeout(60000)//数据读取超时时间
                    .build();
            httpGet.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf-8");
            logger.info(content);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    @Override
    public void singleDoPost(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = null;
        httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            if (CollectionUtil.isNotEmpty(paramMap)) {
                List<NameValuePair> nameValuePairList = new ArrayList<>();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);
                httpPost.setEntity(urlEncodedFormEntity);
            }

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)//连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//请求超时时间
                    .setSocketTimeout(60000)//数据读取超时时间
                    .build();
            httpPost.setConfig(requestConfig);

            httpResponse = httpClient.execute(httpPost);
            logger.info(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf-8");
            logger.info(content);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public void testGet() throws Exception {
        HttpProcessor httpproc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36"))
                .add(new RequestExpectContinue(true)).build();

        HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

        HttpCoreContext coreContext = HttpCoreContext.create();
        //HttpHost host = new HttpHost("localhost", 8080);
        //HttpGet
        HttpHost host = HttpHost.create("https://www.baidu.com");
        coreContext.setTargetHost(host);

        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
        ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

        try {

            String[] targets = {
                    "/"};

            for (int i = 0; i < targets.length; i++) {
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket);
                }
                BasicHttpRequest request = new BasicHttpRequest("GET", targets[i]);
                System.out.println(">> Request URI: " + request.getRequestLine().getUri());

                httpexecutor.preProcess(request, httpproc, coreContext);
                HttpResponse response = httpexecutor.execute(request, conn, coreContext);
                httpexecutor.postProcess(response, httpproc, coreContext);

                System.out.println("<< Response: " + response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println("==============");
                if (!connStrategy.keepAlive(response, coreContext)) {
                    conn.close();
                } else {
                    System.out.println("Connection kept alive...");
                }
            }
        } finally {
            conn.close();
        }
    }
}
