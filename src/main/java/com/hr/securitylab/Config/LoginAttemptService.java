package com.hr.securitylab.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Class which
 */

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPT = 5;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String s) throws Exception {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key){
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key){
        int attempts = 0;
        try{
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e){
            attempts = 0;
        }
        attempts ++;
        attemptsCache.put(key,attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
