package com.cy.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cy.monitor.openapi.HttpRequest;
import com.cy.monitor.openapi.Response;
import com.cy.monitor.openapi.SimpleHttpRequest;
import com.cy.monitor.util.HeaderUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author chenyao
 * @version 1.0
 * @Description //采集平台指令下发接口可用性监控
 * @date 2022/3/10 17:36
 */

@RestController
@RequestMapping("/iot/monitoring")
public class PlatformMonitoring {
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    @RequestMapping("/monitoring")
    public Integer sendCommandMonitoring() {
        System.out.println("测试api/v1/device/send/command接口可用性");
        String url = "http://127.0.0.1:8844/api/v1/device/send/command";
        HttpRequest request = new SimpleHttpRequest(url, httpClient);
        String body = "{\n" +
                "\t\"deviceId\": \"12345678\",\n" +
                "\t\"sendType\": \"2\",\n" +
                "\t\"commandType\": \"kaihu\",\n" +
                "\t\"commandContent\": \"\"\n" +
                "}";
        request.headers(HeaderUtils.createHeadersOfJsonString(body));
        request.requestBody(body);
        try {
            Response response = request.post();
            System.out.println("成功");
            System.out.println(JSON.parse(response.asBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错误");
            return 1;
        }
        return 0;
    }

    @RequestMapping("/getToken")
    public String getToken() {
        String url = "http://127.0.0.1:8844/api/v1/token";
        HttpRequest request = new SimpleHttpRequest(url, httpClient);
        String body = "{\"expires\": 7200}";
        request.headers(HeaderUtils.createHeadersOfJsonString(body));
        request.requestBody(body);
        try {
            Response response = request.post();
            Object object = JSON.parse(response.asBytes());
            System.out.println(object);
            if(object!=null){
                Map<String,String> map = (Map<String, String>) object;
                return map.get("result");
            }else{
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "获取token失败";
        }
    }
}
