
### 基本包信息

```text
factory : 产生代理类的实现类
proxy :代理类的具体生成方式
discover: 服务发现，发现远程的服务和服务的传输类型的和传输协议
protocol ： 把请求的方法，类，参数，按照传输协议（json or thrift）encode ，decode,protocol的话，因为服务端和客户端都需要用到，所以移动到了common包里面
net : 网络层的传输方式，http传输或者netty方式传输
```


### 扩展包信息，为了后期更灵活的组装和返回request，response

```text
filter : 通过这个可以更新的的封装请求的request，和返回的response .

```


