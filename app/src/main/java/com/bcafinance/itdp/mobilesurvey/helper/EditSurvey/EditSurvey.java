package com.bcafinance.itdp.mobilesurvey.helper.EditSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditSurvey {
    @SerializedName("CodeSurvey")
    @Expose
    private String codeSurvey;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("dataQuest")
    @Expose
    private List<DataQuest> dataQuest = null;
    @SerializedName("narasumber1")
    @Expose
    private List<Narasumber1> narasumber1 = null;
    @SerializedName("narasumber2")
    @Expose
    private List<Narasumber2> narasumber2 = null;

    public String getCodeSurvey() {
        return codeSurvey;
    }

    public void setCodeSurvey(String codeSurvey) {
        this.codeSurvey = codeSurvey;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public List<DataQuest> getDataQuest() {
        return dataQuest;
    }

    public void setDataQuest(List<DataQuest> dataQuest) {
        this.dataQuest = dataQuest;
    }

    public List<Narasumber1> getNarasumber1() {
        return narasumber1;
    }

    public void setNarasumber1(List<Narasumber1> narasumber1) {
        this.narasumber1 = narasumber1;
    }

    public List<Narasumber2> getNarasumber2() {
        return narasumber2;
    }

    public void setNarasumber2(List<Narasumber2> narasumber2) {
        this.narasumber2 = narasumber2;
    }
}
