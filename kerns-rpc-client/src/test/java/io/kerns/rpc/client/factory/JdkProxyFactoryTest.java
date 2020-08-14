package io.kerns.rpc.client.factory;

import io.kerns.rpc.client.discover.MockDiscover;
import io.kerns.rpc.client.net.SocketNetClient;
import io.kerns.rpc.common.protocol.JavaSerializableMessageProtocol;

public class JdkProxyFactoryTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();
        jdkProxyFactory.setProtocol(new JavaSerializableMessageProtocol());
        jdkProxyFactory.setDiscover(new MockDiscover());
        jdkProxyFactory.setNetClient(new SocketNetClient());
        HelloInterface helloInterface = jdkProxyFactory.getProxy(HelloInterface.class);
        System.out.println(helloInterface.hello());
    }

}