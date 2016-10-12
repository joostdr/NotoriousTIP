package com.hr.securitylab.Services;

import com.hr.securitylab.Models.Login;

/**
 * Created by joost on 12-10-2016.
 */
public class LoginService {

    public LoginService() {
    }

    public static String loginUser(Login loginModel){
        return checkUsernamePassword(loginModel.getUsername(), loginModel.getPassword()) ? "login" : "main";
    }

    private static boolean checkUsernamePassword(String username, String password){
        return username.equals("dat") && password.equals("boi");
    }
}
