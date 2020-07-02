package io.kerns.rpc.common.protocol;

import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.bean.Response;

/**
 * 数据传输的时候的协议相关
 */
public interface MessageProtocol {
    /**
     * 加密请求
     * @param request
     * @return
     */
    byte[] encodeRequest(Request request);

    /**
     * 主要是服务端解密
     * @param request
     * @return
     */
    Request decodeRequest(byte[] request);

    /**
     * 加密返回结果
     * @param response
     * @return
     */
    byte[] encodeResponse(Response response);

    /**
     * 解密成response
     * @return
     */
    Response decodeResponse(byte[] response);
}
