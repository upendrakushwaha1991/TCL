package com.androidbuts.multispinnerfilter;

public class KeyPairBoolData {

    long id;
    String name;
    boolean isSelected;
    String merCd;
    Object object;
    String answerCd;
    String answer;


    public String getAnswerCd() {
        return answerCd;
    }

    public void setAnswerCd(String answerCd) {
        this.answerCd = answerCd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMerCd() {
        return merCd;
    }

    public void setMerCd(String merCd) {
        this.merCd = merCd;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isSelected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}