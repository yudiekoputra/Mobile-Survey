package com.bcafinance.itdp.mobilesurvey.helper.EditSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Narasumber2 {
    @SerializedName("Code_Pertanyaan")
    @Expose
    private String codePertanyaan;
    @SerializedName("JenisNarasumber")
    @Expose
    private String jenisNarasumber;
    @SerializedName("Answer")
    @Expose
    private String answer;
    @SerializedName("Desc")
    @Expose
    private String desc;

    public String getCodePertanyaan() {
        return codePertanyaan;
    }

    public void setCodePertanyaan(String codePertanyaan) {
        this.codePertanyaan = codePertanyaan;
    }

    public String getJenisNarasumber() {
        return jenisNarasumber;
    }

    public void setJenisNarasumber(String jenisNarasumber) {
        this.jenisNarasumber = jenisNarasumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
