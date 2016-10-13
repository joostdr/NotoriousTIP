package com.hr.securitylab.services;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class HttpService {

    private HttpClient client;

    public HttpService() throws Exception {
        this.client = HttpClientBuilder.create().build();
        prepareESP();
    }

    public void turnOn() throws Exception{
        System.out.println("Turning ON...........................................");
        String url = "http://192.168.111.1/digital/2/1";
        executePost(url);
    }

    public void turnOff() throws Exception{
        System.out.println("Turning OFF...........................................");
        String url = "http://192.168.111.1/digital/2/0";
        executePost(url);
    }

    private void executePost(String url) throws Exception{
        HttpPost post = new HttpPost(url);
        getResponseCode(client.execute(post));
    }

    private void prepareESP() throws Exception{
        System.out.println("Preparing ESP...............................................");
        HttpPost post = new HttpPost("http://192.168.111.1/mode/2/o");
        getResponseCode(client.execute(post));
    }

    private void getResponseCode(HttpResponse response){
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(responseCode);
    }

}
