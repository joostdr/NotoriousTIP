package com.hr.securitylab.services;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpService {

    private static final HttpClient httpClient = HttpClientBuilder.create().build();
    private static final HttpClientContext context = HttpClientContext.create();

    static{
        prepare();
    }

    private static void prepare() {
        System.out.println("Preparing ESP............................................................");
        HttpHost targetHost = new HttpHost("192.168.111.1", 8080, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("datboi", "CC4d96"));
        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
        System.out.println("Context set");
    }

    public void httpOn() throws IOException {
        this.executeGet("http://requestb.in/13wbs111", "ON");
    }

    public void httpOff() throws IOException {
        this.executeGet("http://requestb.in/13wbs111", "OFF");
    }

    private void executeGet(String url, String action) {
        {
            System.out.println("Executing get on url: " + url +", action: "+ action);
            try {
                HttpResponse response = httpClient.execute(new HttpGet(url), context);
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response code: " + statusCode);
            } catch (IOException e) {
                System.out.println("Oops!");
            }
        }
    }
}
