package io.kerns.rpc.server;

/**
 * rpc 服务器，可以使用netty 或者 mina,或者自定义的socket server
 */
public interface RpcServer {


    void start() throws Exception;
}
