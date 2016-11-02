package com.hr.securitylab.database.entities.rest;

/**
 * Created by joost on 31-10-2016.
 */
public class PollingRest {

    private String command;
    private boolean vibrate;
    private String error;

    public PollingRest() {
    }

    public PollingRest(String command, boolean vibrate, String error) {
        this.command = command;
        this.vibrate = vibrate;
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
