package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SamplingChecklistGetterSetter {
    @SerializedName("Sampling_Checklist")
    @Expose
    private List<SamplingChecklist> samplingChecklist = null;

    public List<SamplingChecklist> getSamplingChecklist() {
        return samplingChecklist;
    }

    public void setSamplingChecklist(List<SamplingChecklist> samplingChecklist) {
        this.samplingChecklist = samplingChecklist;
    }
}
