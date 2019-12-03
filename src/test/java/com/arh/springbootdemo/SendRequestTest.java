package com.arh.springbootdemo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.junit.jupiter.api.Test;

import com.arh.springbootdemo.domain.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @Description
 * @Author chenli
 * @Date 2019/11/8
 **/
public class SendRequestTest {

    //set-cookie https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Set-Cookie
    @Test
    public void testHeader1() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie",
                "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                "c2=b; path=\"/\", c3=c; domain=\"localhost\"");

        HeaderIterator iterator = response.headerIterator("Set-Cookie");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("-----------------------");

        HeaderElementIterator it = new BasicHeaderElementIterator(
                response.headerIterator("Set-Cookie"));

        while (it.hasNext()) {
            HeaderElement elem = it.nextElement();
            System.out.println(elem.getName() + " = " + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.println(" " + params[i]);
            }
        }
    }

    @Test
    public void testEntity1() {
        StringEntity entity = new StringEntity("haha", ContentType.create("text/plain", "utf-8"));
        System.out.println(entity.getContentType());
        System.out.println(entity.getContentLength());

    }

    @Test
    public void testEntity2(){
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("q", "httpclient")
                    .setParameter("btnG", "Google Search")
                    .setParameter("aq", "f")
                    .setParameter("oq", "")
                    .build();
            HttpGet httpget = new HttpGet(uri);
            System.out.println(httpget.getURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("param1", "value1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost("http://localhost/handler.do");
        httppost.setEntity(entity);
        System.out.println(httppost.getURI());

    }

    @Test
    public void testResponseHandler(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/json");

        ResponseHandler<Student> rh = new ResponseHandler<Student>() {

            @Override
            public Student handleResponse(
                    final HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);
                return gson.fromJson(reader, Student.class);
            }
        };
        CloseableHttpClient httpClient = null;
        try {
            Student s = httpClient.execute(httpget, rh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception{
        CloseableHttpClient httpclient = HttpClients.custom()
                .addInterceptorLast(new HttpRequestInterceptor() {

                    public void process(
                            final HttpRequest request,
                            final HttpContext context) throws HttpException, IOException {
                        AtomicInteger count = (AtomicInteger) context.getAttribute("count");
                        request.addHeader("Count", Integer.toString(count.getAndIncrement()));
                    }

                })
                .build();

        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("count", count);

        HttpGet httpget = new HttpGet("http://localhost/");
        for (int i = 0; i < 10; i++) {
            CloseableHttpResponse response = httpclient.execute(httpget, localContext);
            try {
                HttpEntity entity = response.getEntity();
            } finally {
                response.close();
            }
        }
    }
}
