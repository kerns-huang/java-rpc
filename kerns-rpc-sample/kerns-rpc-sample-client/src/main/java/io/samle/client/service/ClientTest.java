package io.samle.client.service;

import io.kerns.rpc.client.discover.Discover;
import io.kerns.rpc.client.discover.MockDiscover;
import io.kerns.rpc.client.factory.JdkProxyFactory;
import io.kerns.rpc.client.net.NetClient;
import io.kerns.rpc.client.net.SocketNetClient;
import io.samle.bean.User;

/**
 * @author xiaohei
 * @create 2020-07-05 下午5:38
 **/
public class ClientTest {

    public static void main(String[] args) {
        NetClient netClient = new SocketNetClient();
        Discover discover=new MockDiscover();
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        proxyFactory.setNetClient(netClient);
        UserService userService = proxyFactory.getProxy(UserService.class);
        User user = userService.getById(12);
        System.out.println(user);
    }
}
