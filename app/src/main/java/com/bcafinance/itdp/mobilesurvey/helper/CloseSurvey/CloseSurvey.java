package com.bcafinance.itdp.mobilesurvey.helper.CloseSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloseSurvey {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataIdCloseSurvey data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataIdCloseSurvey getData() {
        return data;
    }

    public void setData(DataIdCloseSurvey data) {
        this.data = data;
    }
}
