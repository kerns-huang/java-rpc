package io.kerns.rpc.server;


import java.net.Socket;

public class SocketNioTcpRpcServerTest {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8011);
            byte[] bb= "test".getBytes();
            int len=bb.length;
            socket.getOutputStream().write(len);
            socket.getOutputStream().write(bb);
            socket.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}