package io.kerns.rpc.server.register;

import io.kerns.rpc.common.bean.ServerInfo;

/**
 *  服务端注册器
 */
public interface Register {
    /**
     * 把自己注册到注册中心去,注册中心 选型 zookeeper,etcd,consol,naco，eruka 都OK
     */
   void register(ServerInfo serverInfo);

}
