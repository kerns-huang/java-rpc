package io.kerns.rpc.client.factory;

import io.kerns.rpc.client.discover.Discover;
import io.kerns.rpc.client.net.NetClient;
import io.kerns.rpc.common.protocol.MessageProtocol;
import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.bean.Response;
import io.kerns.rpc.common.bean.ServerInfo;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaohei
 * @create 2020-07-02 上午10:36
 **/
@Setter
public class JdkProxyFactory implements ProxyFactory {


    private NetClient netClient;

    private MessageProtocol protocol;

    /**
     * 服务发现
     */
    private Discover discover;

    /**
     * 返回jkd代理类
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> inter) {
        //如果传入的本身就是接口，就把接口直接当传入传入proxy，如果传入的是类，获取类的接口数据
        Class<?>[] clz = new Class[]{inter};
        return (T) Proxy.newProxyInstance(inter.getClassLoader(), clz, new JdkInvocationHandler(netClient, protocol, discover));
    }

    static class JdkInvocationHandler implements InvocationHandler {
        /**
         * 网络传输对象
         */
        private NetClient netClient;
        /**
         * 传输协议
         */
        private MessageProtocol protocol;
        /**
         * 服务发现
         */
        private Discover discover;


        public JdkInvocationHandler(NetClient netClient, MessageProtocol protocol, Discover discover) {
            this.netClient = netClient;
            this.protocol = protocol;
            this.discover = discover;
        }

        /**
         * 执行代理方法
         *
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Request request = new Request();
            request.setTarget(proxy.getClass().getName());
            request.setMethod(method.getName());
            request.setArgs(args);
            //协议加密
            byte[] requestByte = protocol.encodeRequest(request);
            //发现服务信息，从多个服务按照负载规则获取一个服务信息
            ServerInfo serverInfo = discover.discover(proxy);
            //通过网络包发送数据
            byte[] responseByte = netClient.send(serverInfo, requestByte);
            if(requestByte.length==0){
                throw new Exception("服务端没有数据返回");
            }
            System.out.println(new String(requestByte));
            //解密成为response对象
            Response response = protocol.decodeResponse(responseByte);
            //是否有异常信息
            if (response.getCode() != "200") {
                throw new Exception(response.getMsg());
            }
            return response.getResult();
        }
    }
}
