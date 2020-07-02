package io.kerns.rpc.common.protocol;

import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.bean.Response;

/**
 * 数据传输的时候的协议相关
 */
public interface Protocol {
    /**
     * 加密请求
     * @param request
     * @return
     */
    byte[] encode(Request request);
    /**
     * 解密成response
     * @return
     */
    Response decode(byte[] response);
}
