package io.kerns.rpc.client.net;

import io.kerns.rpc.common.bean.ServerInfo;

import java.net.Socket;

public class SocketNetClient implements NetClient {

    public byte[] send(ServerInfo serverInfo, byte[] request) {
        try {
            Socket socket = new Socket(serverInfo.getIp(), serverInfo.getPort());
            socket.getOutputStream().write(request.length);
            socket.getOutputStream().write(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}