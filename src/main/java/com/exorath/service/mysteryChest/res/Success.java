package com.exorath.service.mysteryChest.res;

/**
 * Created by toonsev on 5/12/2017.
 */
public class Success {
    private boolean success;
    private Integer code;
    private String error;

    public Success(boolean success) {
        this.success = success;
    }

    public Success(boolean success, Integer code, String error) {
        this.success = success;
        this.code = code;
        this.error = error;
    }

    public Success(Integer code, String error) {
        this.code = code;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
