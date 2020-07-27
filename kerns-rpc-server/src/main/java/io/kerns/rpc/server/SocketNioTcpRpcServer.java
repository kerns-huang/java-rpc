package io.kerns.rpc.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 不基于其它框架，使用 java 默认的socket 机制获取数据，呈现一个简单的demo
 */
public class SocketNioTcpRpcServer implements RpcServer {

    public static void main(String[] args) {
        try {
            SocketNioTcpRpcServer socketNioTcpRpcServer = new SocketNioTcpRpcServer();
            socketNioTcpRpcServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void start() throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8011);
        ss.bind(address);                                            //1
        Selector selector = Selector.open();                        //2
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);    //3
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        for (; ; ) {
            try {
                selector.select();                                    //4
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();    //5
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {                //准备接受数据
                        //TODO 获取通道信息
                        ServerSocketChannel server =
                                (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE |
                                SelectionKey.OP_READ, msg.duplicate());    //7
                        System.out.println("Accepted connection from " + client);
                        ByteBuffer byteBuffer=ByteBuffer.allocate(4);
                        client.read(byteBuffer);//会修改position ？
                        byteBuffer.rewind();//从头开始？
                        int len= byteBuffer.getInt();
                        System.out.println(len);
                        ByteBuffer content=ByteBuffer.allocate(len);
                        client.read(content);
                        content.rewind();
                        /**
                         * 获取数据，通过协议层转换为request的对象，并添加给相应的handler去处理，并把处理结果返回回去。
                         */
                        System.out.println(new String(content.array()));

                    }
                    if (key.isWritable()) {                //8
                        SocketChannel client =
                                (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {        //9
                                break;
                            }
                        }
                        client.close();                    //10
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        // 在关闭时忽略
                    }
                }
            }
        }

    }

}