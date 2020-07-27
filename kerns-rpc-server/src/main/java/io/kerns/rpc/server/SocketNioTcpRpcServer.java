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
        //设置非阻塞方式
        serverChannel.configureBlocking(false);
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8011);
        ss.bind(address);                                            //1
        Selector selector = Selector.open();                        //2
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);    //3
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
                    //TODO 获取通道信息
                    if (key.isAcceptable()) {
                        ServerSocketChannel server =
                                (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //
                        client.register(selector,
                                SelectionKey.OP_READ);    //7
                        System.out.println("Accepted connection from " + client);
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int readBytes = channel.read(byteBuffer);
                        if (readBytes == -1) {
                            System.out.println("读取不到数据，关闭通道");
                            channel.close();
                        } else {
                            byteBuffer.rewind();//从头开始？
                            int len = byteBuffer.getInt();
                            System.out.println(len);
                            byte[] content = new byte[len];
                            byteBuffer.get(content);
                            /**
                             * 获取数据，通过协议层转换为request的对象，并添加给相应的handler去处理，并把处理结果返回回去。
                             */
                            System.out.println(new String(content));
                        }
                    }
                    if (key.isValid() && key.isWritable()) {                //8
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