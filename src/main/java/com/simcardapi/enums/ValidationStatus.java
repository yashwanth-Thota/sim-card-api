package com.simcardapi.enums;

public enum ValidationStatus{
    VERIFIED("verified"),
    PENDING("inactive"),
    EXPIRED("expired");

    public final String value;

    private ValidationStatus(String status){
        this.value=status;
    }

    public static ValidationStatus getValidationStatus(String value){
        for(ValidationStatus st:ValidationStatus.values()){
            if(st.value==value) return st;
        }
        return null;
    }
}
