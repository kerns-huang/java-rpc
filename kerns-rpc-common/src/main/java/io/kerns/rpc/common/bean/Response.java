package io.kerns.rpc.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc返回的信息
 *
 * @author xiaohei
 * @create 2020-07-02 上午10:48
 **/
@Data
public class Response implements Serializable {

    private Exception exception;
    private Object result;

}
