package com.j1.result;

import com.j1.type.MsgStatus;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class SoaApiBaseAction extends JsonpBaseAction {

    protected SoaApiBaseAction setResult(
            final ServiceMessage<?> serviceMessage,
            final String errorMsg) {
        if (serviceMessage != null && serviceMessage.getResult() != null) {
            Class<?> resultClass = serviceMessage.getResult().getClass();
            if (!resultClass.isPrimitive() && !resultClass.isAssignableFrom(String.class))
                this._result.setObjData(serviceMessage.getResult());
        }
        setResultInfo(serviceMessage, errorMsg);
        return this;
    }

    /**
     * 根据ServiceMessage设置返回信息,包含<code>serviceMessage.getResult()</code>设置到
     * <code>_result.setObjData()</code>
     *
     * @param serviceMessage
     * @param errorMsg       serviceMessage为空时的错误消息
     * @return
     */
   /* protected JsonResult setResult(
    		final ServiceMessage<?> serviceMessage,
            final String errorMsg,
            JsonResult result) {
    	if(result == null)
    		result = new JsonResult();
    	if (serviceMessage != null && serviceMessage.getResult() != null) {
    		Class<?> resultClass = serviceMessage.getResult().getClass();
    		if (!resultClass.isPrimitive() && !resultClass.isAssignableFrom(String.class))
    			result.setObjData(serviceMessage.getResult());
    	}
    	setResultInfo(serviceMessage, errorMsg, result);
    	return result;
    }*/
    /**
     * 根据ServiceMessage设置返回信息,只设置status和msg,不包含data
     *
     * @param serviceMessage
     * @param errorMsg       serviceMessage为空时的错误消息
     * @return
     */
   /* protected JsonResult setResultInfo(
    		ServiceMessage<?> serviceMessage,
            final String errorMsg,
            JsonResult result) {
    	if(result == null)
    		result = new JsonResult();
        if (serviceMessage == null) {
            result.setStatus(MsgStatus.EXCEPTION.getCode()).setMsg(
                    errorMsg);
        } else {
            result.setStatus(serviceMessage.getStatus().getCode())
                    .setMsg(serviceMessage.getMessage());
        }
        return result;
    }*/

    /**
     * 根据ServiceMessage设置返回信息,只设置status和msg,不包含data
     * 用此方法需要在action层加@Scope("request")
     *
     * @param serviceMessage
     * @param errorMsg       serviceMessage为空时的错误消息
     * @return
     */
    protected SoaApiBaseAction setResultInfo(
            ServiceMessage<?> serviceMessage,
            final String errorMsg) {
        if (serviceMessage == null) {
            this._result.setStatus(MsgStatus.EXCEPTION.getCode()).setMsg(
                    errorMsg);
        } else {
            this._result.setStatus(serviceMessage.getStatus().getCode())
                    .setMsg(serviceMessage.getMessage());
        }
        return this;
    }

    /**
     * 设置返回信息
     *
     * @param status 返回状态
     * @param msg    返回消息
     * @return
     */
    /*public JsonResult setResultInfo(String status, String msg, JsonResult result) {
    	if(result == null)
    		result = new JsonResult();
        result.setStatus(status).setMsg(msg);
        return result;
    }*/

    /**
     * 设置返回信息
     * 用此方法需要在action层加@Scope("request")
     *
     * @param status 返回状态
     * @param msg    返回消息
     * @return
     */
    public SoaApiBaseAction setResultInfo(String status, String msg) {
        this._result.setStatus(status).setMsg(msg);
        return this;
    }

}
