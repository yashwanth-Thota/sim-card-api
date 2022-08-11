package com.simcardapi.enums;

public enum Status{
    ACTIVE("active"),
    INACTIVE("inactive");

    public final String value;

    private Status(String status){
        this.value=status;
    }

    public static Status getStatus(String value){
        for(Status st:Status.values()){
            if(st.value==value) return st;
        }
        return null;
    }
}
