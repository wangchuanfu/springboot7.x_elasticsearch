package com.j1.result;


import com.j1.type.MsgStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class JsonResult {
    /**
     * 返回状态码
     */
    private String status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回结果对象
     */
    private JSONObject data;

    public JsonResult() {
        this.setSuccess();
        this.setData(new JSONObject());
    }

    public String getStatus() {
        return status;
    }

    public JsonResult setStatus(String status) {
        this.status = status;
        return this;
    }

    public JsonResult setSuccess() {
        this.status = MsgStatus.NORMAL.getCode();
        return this;
    }

    public JsonResult setFailure() {
        this.status = MsgStatus.EXCEPTION.getCode();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public JSONObject getData() {
        synchronized (data) {
            return data;
        }
    }

    public boolean success() {
        return this.status.equals(MsgStatus.NORMAL.getCode());
    }

    public JsonResult setData(JSONObject data) {
        synchronized (data) {
            this.data = data;
            return this;
        }
    }

    public JsonResult put(Object key, Object val) {
        this.getData().put(key, val == null ? JSONNull.getInstance() : val);
        return this;
    }

    public JsonResult setObjData(Object o) {
        if (o == null)
            return this;
        try {
            if (Map.class.isAssignableFrom(o.getClass())) {
                this.setData((JSONObject) o);
            } else if (Collection.class.isAssignableFrom(o.getClass())) {
                this.put("data", JSONArray.fromObject(o));
            } else {
                this.setData(JSONObject.fromObject(o));
            }
        } catch (Exception e) {

        }
        return this;
    }

    public JsonResult putObjData(Object o) {
        if (o == null)
            return this;
        JSONObject jo = null;
        try {
            if (o.getClass().equals(JSONObject.class)) {
                jo = (JSONObject) o;
            } else {
                jo = JSONObject.fromObject(o);
            }
            if (jo != null)
                this.getData().putAll(jo);
        } catch (Exception e) {

        }
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
