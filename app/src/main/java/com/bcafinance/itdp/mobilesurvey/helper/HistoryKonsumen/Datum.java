package com.bcafinance.itdp.mobilesurvey.helper.HistoryKonsumen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("MobileID")
    @Expose
    private String mobileID;
    @SerializedName("NamaKonsumen")
    @Expose
    private String namaKonsumen;
    @SerializedName("TanggalInput")
    @Expose
    private String tanggalInput;

    public String getMobileID() {
        return mobileID;
    }

    public void setMobileID(String mobileID) {
        this.mobileID = mobileID;
    }

    public String getNamaKonsumen() {
        return namaKonsumen;
    }

    public void setNamaKonsumen(String namaKonsumen) {
        this.namaKonsumen = namaKonsumen;
    }

    public String getTanggalInput() {
        return tanggalInput;
    }

    public void setTanggalInput(String tanggalInput) {
        this.tanggalInput = tanggalInput;
    }
}
