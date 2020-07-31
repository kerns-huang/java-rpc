package io.kerns.rpc.server;


import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.enums.ProtocolType;
import io.kerns.rpc.common.protocol.JavaSerializableMessageProtocol;
import io.kerns.rpc.common.protocol.MessageProtocol;

import java.net.Socket;
import java.nio.ByteBuffer;

public class SocketNioTcpRpcServerTest {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8011);
            Request request=new Request();
            request.setTarget("io.kern.prc.UserRpc");
            request.setMethod("hello");
            MessageProtocol protocol=new JavaSerializableMessageProtocol();
            byte[] bb=  protocol.encodeRequest(request);
            int len=bb.length;
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(4 + len);
                byteBuffer.putInt(len);
                byteBuffer.put(bb);
                byteBuffer.flip();
                ByteBuffer byteBuffer1 = byteBuffer.duplicate();
                socket.getOutputStream().write(byteBuffer1.array());
                socket.getOutputStream().flush();
                Thread.sleep(1000l);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}