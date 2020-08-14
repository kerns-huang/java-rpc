package io.kerns.rpc.client.discover;


import io.kerns.rpc.common.bean.ServerInfo;
import io.kerns.rpc.common.enums.ProtocolType;

public class MockDiscover implements Discover {

    public ServerInfo discover(Object proxy) {
        ServerInfo serverInfo=new ServerInfo();
        serverInfo.setIp("127.0.0.1");
        serverInfo.setPort(8011);
        serverInfo.setProtocol(ProtocolType.JavaSerializable);
        return serverInfo;
    }
}