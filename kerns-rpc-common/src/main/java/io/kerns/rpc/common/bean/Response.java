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
    //返回的消息码。
    private String code;
    //返回消息
    private String msg;
    /**
     * 返回的结果对象
     */
    private Object result;

}
