package io.kerns.rpc.common.bean;

import io.kerns.rpc.common.enums.ProtocolType;

/**
 * 服务端信息,包含基本的调用信息
 *
 * @author xiaohei
 * @create 2020-07-02 上午11:17
 **/
public class ServerInfo {

    private String ip;

    private String port;

    private ProtocolType protocol;
}
