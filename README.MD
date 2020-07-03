### 初衷
```text
体验面向接口编程的快感。学习从繁杂的rpc框架里面学最核心的东西
```
### client 基本包信息

```text
factory : 产生代理类的实现类
proxy :代理类的具体生成方式
discover: 服务发现，发现远程的服务和服务的传输类型的和传输协议
net : 网络层的传输方式，http传输或者netty方式传输
```


### common 基本包信息

```text
protocol ： 把请求的方法，类，参数，按照传输协议（json or thrift，proto buffer,基本的jdk）encode ，decode,protocol的话，因为服务端和客户端都需要用到，
bean ：服务端和客户端都需要用到的对象
enums：服务端和客户端都需要用到的枚举
```

### 服务端基本包说明

```text
register: 注册器，服务启动的时候需要把自己注册到注册中心，让客户端discover，extra 这有两种方式
receiver: 接收器，接受远程数据传输
handler : 具体的业务实现类
```



