package com.hr.securitylab.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

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
        System.out.println(getResponseCode(client.execute(post)));
    }

    private void prepareESP() throws IOException{
        System.out.println("Preparing ESP...............................................");
        HttpPost post = new HttpPost("http://192.168.111.1/mode/2/o");
        try{
            System.out.println(getResponseCode(client.execute(post)));
        }
        catch (HttpHostConnectException h){
            System.out.println("No connection could be established with the target");
        }
    }

    private int getResponseCode(HttpResponse response){
        return response.getStatusLine().getStatusCode();
    }

}
