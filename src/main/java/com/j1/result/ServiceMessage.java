package com.j1.result;

import com.j1.type.MsgStatus;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public interface ServiceMessage<T> {
    MsgStatus getStatus();

    String getMessage();

    T getResult();
}
