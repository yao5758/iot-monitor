package com.cy.monitor;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootTest
class MonitorApplicationTests {

    @Test
    void contextLoads() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StringBuffer params = new StringBuffer();
        try {
            params.append("rqbKey=" + "7c9d6f06c12d4dbaaa3967d3dd0cc04e");
            params.append("&");
            params.append("isValveClose=" + 1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        HttpPost httpPost = new HttpPost("http://218.22.6.60:8603/api/order/updateRqbState" + "?" + params);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
