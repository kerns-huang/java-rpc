package io.kerns.rpc.client.discover;

import io.kerns.rpc.common.bean.ServerInfo;

/**
 * 服务发现，通过负载均衡或者随机算法，返回一个服务的信息,可以通过etcd，zookeeper来做服务发现
 */
public interface Discover {
    /**
     * 通过代理对象查找对应的服务信息，可以通过名字找，也可以通过 注解找，基本就是一个约定的过程
     * @param proxy
     * @return
     */
    ServerInfo discover(Object proxy);
}
