package com.j1.type;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public enum MsgStatus {

    EXCEPTION("1"), NORMAL("0"), NO_RESULT("0"), PARAMS_ERROR("1");

    private String i;

    private MsgStatus(String n) {
        this.i = n;
    }

    public String getCode() {
        return this.i;
    }

    public static MsgStatus resolve(String i) {
        try {
            return MsgStatus.valueOf(i);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTypeName(String type) {
        try {
            return MsgStatus.resolve(type).getCode();
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(MsgStatus.EXCEPTION);
    }

}
