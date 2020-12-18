package com.j1.xiaoxiang.exception;

public enum  SGErrors {

    CHECK_PARAMS_ERRORS(1001, "参数校验错误"),
    ILLEGAL_PARAMS(1002, "非法入参!"),
    UNKNOW_ERROR(1999, "未知异常"),
    REMOTE_CALL(2001, "远程调用异常"),
    SHARE_ORDER_NOT_EXISTS(2101, "商品已下架"),
    SK_INVALID(2003, "无效的搜索词"),
    SK_INVALID_NO_RESULT(2008, "搜索词只包含表情符或非法字符，无结果"),
    SK_INVALID_NO_NEEDS(2004, "搜索词只包含表情符，无需搜索");


    private int code;
    private String desc;

    SGErrors(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
