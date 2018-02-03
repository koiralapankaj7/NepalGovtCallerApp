package com.koiralapankaj007gmail.nepalgovtcallerapp;


public class CompleteContactInformationHolder {

    private int ID;
    private int CONTACT_IMAGE;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String COMPANY_NAME;
    private String PHONE_NUMBER_1;
    private String EMAIL_ID_1;
    private String URL_1;
    private String SOCIAL_PROFILE_1;
    private String INSTANT_MESSAGE_1;
    private String NOTES;

    public CompleteContactInformationHolder() {

    }

    public CompleteContactInformationHolder(int ID, int CONTACT_IMAGE, String FIRST_NAME, String LAST_NAME, String COMPANY_NAME, String PHONE_NUMBER_1, String EMAIL_ID_1, String URL_1, String SOCIAL_PROFILE_1, String INSTANT_MESSAGE_1, String NOTES) {
        this.ID = ID;
        this.CONTACT_IMAGE = CONTACT_IMAGE;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.COMPANY_NAME = COMPANY_NAME;
        this.PHONE_NUMBER_1 = PHONE_NUMBER_1;
        this.EMAIL_ID_1 = EMAIL_ID_1;
        this.URL_1 = URL_1;
        this.SOCIAL_PROFILE_1 = SOCIAL_PROFILE_1;
        this.INSTANT_MESSAGE_1 = INSTANT_MESSAGE_1;
        this.NOTES = NOTES;
    }

    public int getID() {
        return ID;
    }

    public int getCONTACT_IMAGE() {
        return CONTACT_IMAGE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public String getPHONE_NUMBER_1() {
        return PHONE_NUMBER_1;
    }

    public String getEMAIL_ID_1() {
        return EMAIL_ID_1;
    }

    public String getURL_1() {
        return URL_1;
    }

    public String getSOCIAL_PROFILE_1() {
        return SOCIAL_PROFILE_1;
    }

    public String getINSTANT_MESSAGE_1() {
        return INSTANT_MESSAGE_1;
    }

    public String getNOTES() {
        return NOTES;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCONTACT_IMAGE(int CONTACT_IMAGE) {
        this.CONTACT_IMAGE = CONTACT_IMAGE;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public void setPHONE_NUMBER_1(String PHONE_NUMBER_1) {
        this.PHONE_NUMBER_1 = PHONE_NUMBER_1;
    }

    public void setEMAIL_ID_1(String EMAIL_ID_1) {
        this.EMAIL_ID_1 = EMAIL_ID_1;
    }

    public void setURL_1(String URL_1) {
        this.URL_1 = URL_1;
    }

    public void setSOCIAL_PROFILE_1(String SOCIAL_PROFILE_1) {
        this.SOCIAL_PROFILE_1 = SOCIAL_PROFILE_1;
    }

    public void setINSTANT_MESSAGE_1(String INSTANT_MESSAGE_1) {
        this.INSTANT_MESSAGE_1 = INSTANT_MESSAGE_1;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }
}
