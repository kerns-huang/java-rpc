package io.kerns.rpc.client.factory;

import io.kerns.rpc.client.discover.MockDiscover;
import io.kerns.rpc.client.net.SocketNetClient;
import io.kerns.rpc.common.protocol.JavaSerializableMessageProtocol;

public class JdkProxyFactoryTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();
        //设置序列化反序列化协议。
        jdkProxyFactory.setProtocol(new JavaSerializableMessageProtocol());
        //设置服务发现
        jdkProxyFactory.setDiscover(new MockDiscover());
        //设置网络交互客户端。
        jdkProxyFactory.setNetClient(new SocketNetClient());
        //获取代理实现
        HelloInterface helloInterface = jdkProxyFactory.getProxy(HelloInterface.class);
        System.out.println(helloInterface.hello());
    }

}