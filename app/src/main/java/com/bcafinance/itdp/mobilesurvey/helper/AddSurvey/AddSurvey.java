package com.bcafinance.itdp.mobilesurvey.helper.AddSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSurvey {

    @SerializedName("Kode_Konsumen")
    @Expose
    private String kodeKonsumen;
    @SerializedName("Jenis_survey")
    @Expose
    private String jenisSurvey;
    @SerializedName("data")
    @Expose
    private DataIdAddSurvey data;


    public String getKodeKonsumen() {
        return kodeKonsumen;
    }

    public void setKodeKonsumen(String kodeKonsumen) {
        this.kodeKonsumen = kodeKonsumen;
    }

    public String getJenisSurvey() {
        return jenisSurvey;
    }

    public void setJenisSurvey(String jenisSurvey) {
        this.jenisSurvey = jenisSurvey;
    }


    public DataIdAddSurvey getData() {
        return data;
    }

    public void setData(DataIdAddSurvey data) {
        this.data = data;
    }

}
