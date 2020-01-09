package com.bcafinance.itdp.mobilesurvey.helper.EditSurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataQuest {
    @SerializedName("Code_Quest")
    @Expose
    private String codeQuest;
    @SerializedName("Answer")
    @Expose
    private String answer;
    @SerializedName("Desc")
    @Expose
    private String desc;
    @SerializedName("SubQuest")
    @Expose
    private List<SubQuest> subQuest = null;

    public String getCodeQuest() {
        return codeQuest;
    }

    public void setCodeQuest(String codeQuest) {
        this.codeQuest = codeQuest;
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

    public List<SubQuest> getSubQuest() {
        return subQuest;
    }

    public void setSubQuest(List<SubQuest> subQuest) {
        this.subQuest = subQuest;
    }

}
