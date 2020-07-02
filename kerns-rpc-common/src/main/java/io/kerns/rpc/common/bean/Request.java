package io.kerns.rpc.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc 传入的信息
 * @author xiaohei
 * @create 2020-07-02 上午10:45
 **/
@Data
public class Request implements Serializable {
    /**
     * 代理的对象
     */
    private String target;
    /**
     * 代理的方法名字
     */
    private String method;
    /**
     * 传入的参数
     */
    private Object[] args;



}
