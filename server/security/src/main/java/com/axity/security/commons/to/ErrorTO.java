package com.axity.security.commons.to;

import java.io.Serializable;

public class ErrorTO implements Serializable {
    private long errorCode;
    private String errorMessage;

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}