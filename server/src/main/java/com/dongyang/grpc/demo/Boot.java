package com.dongyang.grpc.demo;

import com.dongyang.grpc.demo.server.rpc.HelloRpcImpl;
import com.dongyang.grpc.demo.server.manager.GrpcServer;

import java.util.Collections;

/**
 * @author dongyang.hu
 * @date 2023/8/22 11:39 AM
 */
public class Boot {
    private static final int SERVER_PORT = 10000;

    public static void main(String[] args) throws Exception {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.init(SERVER_PORT, Collections.singletonList(new HelloRpcImpl()));
        grpcServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                grpcServer.shutdown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.err.println("*** server shut down");
        }));
    }
}