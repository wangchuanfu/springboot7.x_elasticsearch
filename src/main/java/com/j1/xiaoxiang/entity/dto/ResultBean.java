package com.j1.xiaoxiang.entity.dto;

import lombok.Data;

@Data
public class ResultBean<T> {
    public static final String SUCCESS = "0000";
    public static final String UN_KNOWS = "5002";
    /**
     * 响应码
     */
    private String resultCode = SUCCESS;
    /**
     * 响应描述
     */
    private String resultMessage;
    /**
     * 接口调用成功标志
     */
    private boolean success;
    /**
     * 封装的业务数据
     */
    private T resultData;

    public boolean getSuccess() {
        return "0000".equals(resultCode);
    }

    public ResultBean() {
        super();
    }

    public ResultBean(T resultData) {
        super();
        this.resultData = resultData;
    }

    public ResultBean(Throwable e) {
        super();
        this.resultMessage = e.toString();
        this.resultCode = UN_KNOWS;
    }
}

