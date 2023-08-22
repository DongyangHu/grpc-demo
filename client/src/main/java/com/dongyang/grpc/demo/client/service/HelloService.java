package com.dongyang.grpc.demo.client.service;

import com.dongyang.grpc.demo.client.config.ServerConfig;
import com.dongyang.grpc.demo.protocol.BaseMessageProto;
import com.dongyang.grpc.demo.protocol.HelloRpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

/**
 * @author dongyang.hu
 * @date 2023/8/22 11:43 PM
 */
public class HelloService {

    private static final ManagedChannel CHANNEL = NettyChannelBuilder.forAddress(ServerConfig.GRPC_SERVER_URL, ServerConfig.GRPC_SERVER_PORT)
            .usePlaintext().directExecutor().build();
    private static HelloRpcServiceGrpc.HelloRpcServiceBlockingStub blockingStub = HelloRpcServiceGrpc.newBlockingStub(CHANNEL);


    public void sayHello() {
        BaseMessageProto.HelloRequest request = BaseMessageProto.HelloRequest.newBuilder()
                .setMsg("Hello Grpc")
                .build();
        BaseMessageProto.HelloResponse response = blockingStub.sayHello(request);
        System.out.println("Receive response from grpc server, content: " + response.toString());
    }

}
