package com.dongyang.grpc.demo.server.rpc;

import com.dongyang.grpc.demo.protocol.BaseMessageProto;
import com.dongyang.grpc.demo.protocol.HelloRpcServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author dongyang.hu
 * @date 2023/8/22 10:40 PM
 */
public class HelloRpcImpl extends HelloRpcServiceGrpc.HelloRpcServiceImplBase {

    @Override
    public void sayHello(BaseMessageProto.HelloRequest request, StreamObserver<BaseMessageProto.HelloResponse> responseObserver) {
        System.out.println("receive rpc request, content: " + request.toString());
        BaseMessageProto.HelloResponse response = BaseMessageProto.HelloResponse.newBuilder()
                .setMsg("Request has been Received")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
