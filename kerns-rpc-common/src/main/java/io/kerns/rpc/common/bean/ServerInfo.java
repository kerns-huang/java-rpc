package io.kerns.rpc.common.bean;

import io.kerns.rpc.common.enums.ProtocolType;
import lombok.Data;

/**
 * 服务端信息,包含基本的调用信息
 *
 * @author xiaohei
 * @create 2020-07-02 上午11:17
 **/
@Data
public class ServerInfo {

    private String ip;

    private Integer port;
    /**
     * 服务的模块名字，ps 例如 tomcat 里面 http://localhost:8080/app 里面的app
     */
    private String serverName;

    private ProtocolType protocol;
}
