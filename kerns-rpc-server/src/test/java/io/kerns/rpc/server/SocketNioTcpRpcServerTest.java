package io.kerns.rpc.server;


import java.net.Socket;
import java.nio.ByteBuffer;

public class SocketNioTcpRpcServerTest {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8011);
            byte[] bb= "test".getBytes();
            int len=bb.length;
            ByteBuffer byteBuffer=ByteBuffer.allocate(4+len);
            byteBuffer.putInt(len);
            byteBuffer.put(bb);
            byteBuffer.flip();
            ByteBuffer byteBuffer1=  byteBuffer.duplicate();
            socket.getOutputStream().write(byteBuffer1.array());
            socket.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}