package dev.patika.vetManagement.core.result;

import lombok.Getter;

@Getter
public class Result {

    private boolean status;
    private String msg;
    private String httpCode;

    public Result(boolean status, String msg, String httpCode) {
        this.status = status;
        this.msg = msg;
        this.httpCode = httpCode;
    }
}
