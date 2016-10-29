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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    public String httpOn() throws IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
       // System.out.println("http://192.168.111.1:8080/" + EncryptionService.encrypt("digital") + "/" + EncryptionService.encrypt("2") + "/" + EncryptionService.encrypt("1"));
        //System.out.println("http://192.168.111.1:8080/" + EncryptionService.encrypt("digital") + "/" + EncryptionService.encrypt("2") + "/" + EncryptionService.encrypt("0"));
        //return this.executeGet("http://192.168.111.1:8080/" + EncryptionService.encrypt("digital") + "/" + EncryptionService.encrypt("2") + "/" + EncryptionService.encrypt("1"));
        return this.executeGet("http://192.168.111.1");
    }

    public String httpOff() throws IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        //return this.executeGet("http://192.168.111.1:8080/" + EncryptionService.encrypt("digital") + "/" + EncryptionService.encrypt("2") + "/" + EncryptionService.encrypt("0"));
        //return this.executeGet("http://192.168.111.1:8080/digital/2/0");
        return this.executeGet("http://google.com");
    }

    private String executeGet(String url) {
        {
            System.out.println("Executing get on url: " + url + "......................");
            try {
                HttpResponse response = httpClient.execute(new HttpGet(url), context);
                return Integer.toString(response.getStatusLine().getStatusCode());
            } catch (IOException e) {
                return "500";
            }
        }
    }
}
