package com.liu.smsintercept;

/**
 * Created by liu on 2015/12/30 0030.
 */
public class Blacklist {
    private int id;
    private String address;
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
