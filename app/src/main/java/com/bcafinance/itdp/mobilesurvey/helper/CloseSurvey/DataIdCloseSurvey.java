package com.bcafinance.itdp.mobilesurvey.helper.CloseSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataIdCloseSurvey {
    @SerializedName("DataID")
    @Expose
    private String dataID;

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }
}
