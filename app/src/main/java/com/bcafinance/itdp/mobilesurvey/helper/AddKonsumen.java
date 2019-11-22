package com.bcafinance.itdp.mobilesurvey.helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddKonsumen {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("MobileID")
    @Expose
    private String mobileID;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobileID() {
        return mobileID;
    }

    public void setMobileID(String mobileID) {
        this.mobileID = mobileID;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
