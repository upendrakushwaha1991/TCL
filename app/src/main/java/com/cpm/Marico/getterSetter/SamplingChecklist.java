package com.cpm.Marico.getterSetter;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SamplingChecklist {

    @SerializedName("Sampling_Checklist_Id")
    @Expose
    private Integer samplingChecklistId;
    @SerializedName("Sampling_Checklist")
    @Expose
    private String samplingChecklist;
    @SerializedName("Answer_Type")
    @Expose
    private String answerType;
    @SerializedName("Sampling_Answer_Id")
    @Expose
    private Integer samplingAnswerId;
    @SerializedName("Sampling_Answer")
    @Expose
    private String samplingAnswer;
    @SerializedName("Enable_Disable")
    @Expose
    private Integer enableDisable;

    private String Sampling_Correct_Answer = "";
    private int Sampling_Correct_Answer_Cd = 0;

    private int Checklist_cd;
    private String Answer;

    public int getChecklist_cd() {
        return Checklist_cd;
    }

    public void setChecklist_cd(int checklist_cd) {
        Checklist_cd = checklist_cd;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    private ArrayList<KeyPairBoolData> selectedSamplingData = new ArrayList<>();


    public ArrayList<KeyPairBoolData> getSelectedSamplingData() {
        return selectedSamplingData;
    }

    public void setSelectedSamplingData(ArrayList<KeyPairBoolData> selectedSamplingData) {
        this.selectedSamplingData = selectedSamplingData;
    }

    public String getSampling_Correct_Answer() {
        return Sampling_Correct_Answer;
    }

    public void setSampling_Correct_Answer(String sampling_Correct_Answer) {
        Sampling_Correct_Answer = sampling_Correct_Answer;
    }

    public int getSampling_Correct_Answer_Cd() {
        return Sampling_Correct_Answer_Cd;
    }

    public void setSampling_Correct_Answer_Cd(int sampling_Correct_Answer_Cd) {
        Sampling_Correct_Answer_Cd = sampling_Correct_Answer_Cd;
    }

    public Integer getSamplingChecklistId() {
        return samplingChecklistId;
    }

    public void setSamplingChecklistId(Integer samplingChecklistId) {
        this.samplingChecklistId = samplingChecklistId;
    }

    public String getSamplingChecklist() {
        return samplingChecklist;
    }

    public void setSamplingChecklist(String samplingChecklist) {
        this.samplingChecklist = samplingChecklist;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public Integer getSamplingAnswerId() {
        return samplingAnswerId;
    }

    public void setSamplingAnswerId(Integer samplingAnswerId) {
        this.samplingAnswerId = samplingAnswerId;
    }

    public String getSamplingAnswer() {
        return samplingAnswer;
    }

    public void setSamplingAnswer(String samplingAnswer) {
        this.samplingAnswer = samplingAnswer;
    }

    public Integer getEnableDisable() {
        return enableDisable;
    }

    public void setEnableDisable(Integer enableDisable) {
        this.enableDisable = enableDisable;
    }


}
