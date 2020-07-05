package io.kerns.rpc.server;


import java.net.Socket;

public class SocketNioTcpRpcServerTest {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8011);
            socket.getOutputStream().write("test".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}