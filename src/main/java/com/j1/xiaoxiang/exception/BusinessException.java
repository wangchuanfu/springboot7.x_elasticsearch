package com.j1.xiaoxiang.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private int code;
    private String desc;
    public BusinessException(int code, String desc) {
        super("BusinessException -> code : " + code + ", desc : " + desc);
        this.code = code;
        this.desc = desc;
    }

    public BusinessException(SGErrors errors) {
        super("BusinessException -> code : " + errors.getCode() + ", desc : " + errors.getDesc());
        this.code = errors.getCode();
        this.desc = errors.getDesc();
    }

    public BusinessException(SGErrors errors, String message) {
        super("BusinessException -> code : " + errors.getCode() + ", desc : " + errors.getDesc() + " ," + message);
        this.code = errors.getCode();
        this.desc = errors.getDesc() + " ," + message;
    }

    public BusinessException(Throwable throwable) {
        super("BusinessException -> code : " + SGErrors.UNKNOW_ERROR.getCode() + ", desc : " + throwable.getMessage());
        this.code = SGErrors.UNKNOW_ERROR.getCode();
        this.desc = throwable.getMessage();
    }

}
