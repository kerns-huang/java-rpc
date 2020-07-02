package io.kerns.rpc.client.net;

import io.kerns.rpc.common.bean.ServerInfo;

/**
 * 网络客户端
 */
public interface NetClient {

    byte[] send(ServerInfo serverInfo,byte[] request);
}
