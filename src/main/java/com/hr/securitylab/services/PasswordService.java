package com.hr.securitylab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {

    static String encryptPassword(String plainText){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plainText);
    }

}
