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

    private HttpClient httpClient;

    public HttpService() {
            this.httpClient = HttpClientBuilder.create().build();
    }

    public void httpOn() throws IOException {
        System.out.println("Preparing ESP............................................................");
        HttpHost targetHost = new HttpHost("192.168.111.1", 8080, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("datboi", "CC4d96"));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        // Add AuthCache to the execution context
        final HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
        System.out.println("Context set");

        try {
            System.out.println("Executing get..........................................................");
            HttpResponse response = httpClient.execute(new HttpGet("http://192.168.111.1:8080/mode/2/o"), context);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            System.out.println("Executing get #2..........................................................");
            HttpResponse response2 = httpClient.execute(new HttpGet("http://192.168.111.1:8080/digital/2/1"), context);
            int statusCode2 = response2.getStatusLine().getStatusCode();
            System.out.println(statusCode2);
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }

    public void httpOff() throws IOException {
        System.out.println("Preparing ESP............................................................");
        HttpHost targetHost = new HttpHost("192.168.111.1", 8080, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("datboi", "CC4d96"));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        // Add AuthCache to the execution context
        final HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
        System.out.println("Context set");

        try {
            System.out.println("Executing get #2..........................................................");
            HttpResponse response2 = httpClient.execute(new HttpGet("http://192.168.111.1:8080/digital/2/0"), context);
            int statusCode2 = response2.getStatusLine().getStatusCode();
            System.out.println(statusCode2);
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }
}
