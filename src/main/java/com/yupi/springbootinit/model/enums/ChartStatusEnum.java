package com.yupi.springbootinit.model.enums;

public enum ChartStatusEnum {

    WAIT(0,"wait"),
    RUNNING(1,"running"),
    SUCCEED(2,"succeed"),
    FAILED(3,"failed");

    private final Integer statusCode;

    private final String value;

    ChartStatusEnum(Integer statusCode, String value) {
        this.statusCode = statusCode;
        this.value = value;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getValue() {
        return value;
    }
}
