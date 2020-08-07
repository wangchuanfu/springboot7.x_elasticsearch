package com.j1.result;

import com.j1.type.MsgStatus;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class BaseServiceImpl {

    public BaseServiceImpl() {
    }

    public <T> ServiceMessage<T> returnResult(MsgStatus msgStatus, String message, T o) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(msgStatus, message, o);
        return serviceMessage;
    }

    public <T> ServiceMessage<T> returnResult(MsgStatus msgStatus, String message) {
        return this.returnResult(msgStatus, message, (T) null);
    }

    public <T> ServiceMessage<T> returnParamsError(String message) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.PARAMS_ERROR, message);
        return serviceMessage;
    }

    public <T> ServiceMessage<T> returnParamsError() {
        return this.returnParamsError("Params error!");
    }

    public <T> ServiceMessage<T> returnException(String message) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.EXCEPTION, message);
        if(message == null || "null".equalsIgnoreCase(message) || "".equals(message.trim()) || "Unexpected error".equals(message)) {
            this.logMethodCallStack();
        }

        return serviceMessage;
    }

    private void logMethodCallStack() {
        StringBuffer errorMessage = new StringBuffer();
        errorMessage.append("[BaseServiceImpl] print Method Call Stack:\n");
        StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
        StackTraceElement[] arr$ = stackTrace;
        int len$ = stackTrace.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StackTraceElement stackTraceElement = arr$[i$];
            errorMessage.append(stackTraceElement.toString() + "\n");
        }

    }

    public <T> ServiceMessage<T> returnException() {
        return this.returnException("Unexpected error");
    }

    public <T> ServiceMessage<T> returnException(Throwable e) {
        String message = e.getMessage();
        if(message == null || "null".equalsIgnoreCase(message) || "".equals(message.trim()) || e instanceof NullPointerException) {
            this.logMethodCallStack();
            message = "服务器开小差了";
        }

        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.EXCEPTION, message);
        return serviceMessage;
    }

    public <T> ServiceMessage<T> returnNoResult(String message) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.NO_RESULT, message);
        return serviceMessage;
    }

    public <T> ServiceMessage<T> returnNoResult() {
        return this.returnNoResult("Not find result");
    }

    public <E> ServiceMessage<E> returnCorrectResult(String message, E e) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.NORMAL, message, e);
        return serviceMessage;
    }

    public <E> ServiceMessage<E> returnCorrectResult(E e) {
        return this.returnCorrectResult(MsgStatus.NORMAL.name(), e);
    }

    public <E> ServiceMessage<E> returnCorrectResult(String message) {
        ServiceMessageImpl serviceMessage = new ServiceMessageImpl(MsgStatus.NORMAL, message);
        return serviceMessage;
    }
}
