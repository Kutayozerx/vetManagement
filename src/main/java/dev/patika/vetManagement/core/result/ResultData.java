package dev.patika.vetManagement.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;
    public ResultData(boolean status, String msg, String httpCode, T data) {
        super(status, msg, httpCode);
        this.data = data;
    }
}
