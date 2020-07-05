package io.kerns.rpc.server.register;

import io.kerns.rpc.common.bean.ServerInfo;
import lombok.Setter;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @author xiaohei
 * @create 2020-07-05 下午1:53
 **/
@Setter
public class ZookeeperRegister implements Register {

    private ZkClient zkClient;

    public void register(ServerInfo serverInfo) {
       zkClient.create("/kerns-prc-register",serverInfo, CreateMode.EPHEMERAL);
    }
}
