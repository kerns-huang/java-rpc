package io.kerns.rpc.client.factory;

import io.kerns.rpc.client.discover.Discover;
import io.kerns.rpc.client.net.NetClient;
import io.kerns.rpc.common.protocol.MessageProtocol;
import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.bean.Response;
import io.kerns.rpc.common.bean.ServerInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaohei
 * @create 2020-07-02 上午10:36
 **/
public class JdkProxyFactory implements ProxyFactory {


    private NetClient netClient;

    private MessageProtocol protocol;

    /**
     * 返回jkd代理类
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clz){
       return (T)Proxy.newProxyInstance(clz.getClassLoader(),clz.getInterfaces(),new JdkInvocationHandler());
    }

     static class JdkInvocationHandler implements InvocationHandler{
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

         /**
          * 执行代理方法
          * @param proxy
          * @param method
          * @param args
          * @return
          * @throws Throwable
          */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Request request=new Request();
            request.setTarget(proxy.getClass().getName());
            request.setMethod(method.getName());
            request.setArgs(args);
            //协议加密
            byte[] requestByte=  protocol.encodeRequest(request);
            //发现服务信息，从多个服务按照负载规则获取一个服务信息
            ServerInfo serverInfo= discover.discover(proxy);
            //通过网络包发送数据
             byte[] responseByte= netClient.send(serverInfo,requestByte);
             //解密成为response对象
             Response response= protocol.decodeResponse(responseByte);
             //是否有异常信息
             if(response.getException()!=null){
                 throw  response.getException();
             }
             return response.getResult();
        }
    }
}
