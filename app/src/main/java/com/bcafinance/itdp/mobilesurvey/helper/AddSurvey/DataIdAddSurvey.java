package com.bcafinance.itdp.mobilesurvey.helper.AddSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataIdAddSurvey {

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
