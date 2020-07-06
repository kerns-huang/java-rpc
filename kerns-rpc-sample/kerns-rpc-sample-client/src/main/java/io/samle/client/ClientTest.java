package io.samle.client;

import io.kerns.rpc.client.discover.Discover;
import io.kerns.rpc.client.discover.MockDiscover;
import io.kerns.rpc.client.factory.JdkProxyFactory;
import io.kerns.rpc.client.net.NetClient;
import io.kerns.rpc.client.net.SocketNetClient;
import io.kerns.rpc.common.protocol.JavaSerializableMessageProtocol;
import io.kerns.rpc.common.protocol.MessageProtocol;
import io.samle.bean.User;
import io.samle.client.service.UserService;

/**
 * @author xiaohei
 * @create 2020-07-05 下午5:38
 **/
public class ClientTest {

    public static void main(String[] args) {
        NetClient netClient = new SocketNetClient();
        Discover discover = new MockDiscover();
        MessageProtocol protocol = new JavaSerializableMessageProtocol();
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        proxyFactory.setNetClient(netClient);
        proxyFactory.setDiscover(discover);
        proxyFactory.setProtocol(protocol);
        UserService userService = proxyFactory.getProxy(UserService.class);
        User user = userService.getById(12);
        System.out.println(user);
    }
}
