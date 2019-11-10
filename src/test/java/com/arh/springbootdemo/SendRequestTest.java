package com.arh.springbootdemo;

import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

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
}
