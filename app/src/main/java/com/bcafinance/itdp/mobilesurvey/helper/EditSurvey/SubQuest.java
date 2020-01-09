package com.bcafinance.itdp.mobilesurvey.helper.EditSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubQuest {
    @SerializedName("pertanyaan")
    @Expose
    private String pertanyaan;
    @SerializedName("pilihan")
    @Expose
    private String pilihan;
    @SerializedName("Keterangan")
    @Expose
    private String keterangan;

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
