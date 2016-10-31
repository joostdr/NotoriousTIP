package com.hr.securitylab.database.entities.validation;

import com.hr.securitylab.validation.annotations.ProductCodeExists;
import com.hr.securitylab.validation.annotations.ValidPassword;
import com.hr.securitylab.validation.annotations.ValidPin;

import javax.validation.constraints.Size;

@ValidPin
public class ResetPassword {

    @ProductCodeExists
    private String productCode;

    private String pin;

    @Size(min = 8, max = 32)
    @ValidPassword
    private String newPassword;

    public ResetPassword() {
    }

    public ResetPassword(String productCode, String pin, String newPassword) {
        this.productCode = productCode;
        this.pin = pin;
        this.newPassword = newPassword;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
