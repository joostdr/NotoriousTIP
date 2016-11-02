package com.hr.securitylab.database.entities.rest;

/**
 * Created by joost on 2-11-2016.
 */
public class UpdateRest {

    private int versionNumber;
    private String fileName;
    private String data;
    private String hmac;
    private String error;

    public UpdateRest() {
    }

    public UpdateRest(int versionNumber, String fileName, String data, String hmac, String error) {
        this.versionNumber = versionNumber;
        this.fileName = fileName;
        this.data = data;
        this.hmac = hmac;
        this.error = error;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
