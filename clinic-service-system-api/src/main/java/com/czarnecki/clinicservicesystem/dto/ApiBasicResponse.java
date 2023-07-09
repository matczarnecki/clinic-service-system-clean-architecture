package com.czarnecki.clinicservicesystem.dto;

public class ApiBasicResponse {

    private Boolean success;
    private String message;

    public ApiBasicResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
