package com.j1.result;

import com.j1.type.MsgStatus;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class ServiceMessageImpl <T> implements ServiceMessage<T>{

    private MsgStatus status;
    private String message;
    private T result;

    public ServiceMessageImpl(T result) {
        this.status = MsgStatus.NORMAL;
        this.message = MsgStatus.NORMAL.name();
        this.result = result;
    }

    public ServiceMessageImpl(MsgStatus status, T result) {
        this.status = status;
        this.message = status.name();
        this.result = result;
    }

    public ServiceMessageImpl(MsgStatus status, String message) {
        this.status = status;
        this.message = message;
        this.result = null;
    }

    public ServiceMessageImpl(MsgStatus status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public void setStatus(MsgStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public MsgStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getResult() {
        return this.result;
    }
}
