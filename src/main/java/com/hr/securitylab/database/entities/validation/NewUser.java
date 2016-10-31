package com.hr.securitylab.database.entities.validation;

import com.hr.securitylab.validation.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Joost on 16-10-2016.
 */
@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class NewUser {

    @Size(min=8, max=32)
    @UsernameNotInUse
    @NotEmpty
    @NotNull
    private String username;

    @Size(min=8, max=32)
    @NotEmpty
    @NotNull
    @ValidPassword
    private String password;

    private String matchingPassword;

    @NotEmpty
    @ProductCodeExists
    @ProductCodeNotInUse
    @NotNull
    private String productCode;

    public NewUser() {
    }

    public NewUser(String username, String password, String matchingPassword, String productCode) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.productCode = productCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
