package com.dongyang.grpc.demo.server.manager;

import com.dongyang.grpc.demo.server.rpc.HelloRpcImpl;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dongyang.hu
 * @date 2023/8/22 10:37 PM
 */
public class GrpcServer {

    private int port;
    private Server server;

    public void init(int port, List<BindableService> services) {
        this.checkPort(port);
        this.checkServices(services);
        this.port = port;
        NettyServerBuilder serverBuilder = NettyServerBuilder
                .forPort(this.port)
                .addService(new HelloRpcImpl())
                .withChildOption(ChannelOption.SO_SNDBUF, 2097152)
                .withChildOption(ChannelOption.TCP_NODELAY, true)
                .directExecutor();
        services.forEach(serverBuilder::addService);
        this.server = serverBuilder.build();
    }


    public Server start() throws IOException, InterruptedException {
        System.out.println("server started at port: " + this.port);
        Server start = this.server.start();
        this.blockUntilShutdown();
        return start;
    }

    public void shutdown() throws InterruptedException {
        if (server != null) {
            this.server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("server shutdown");
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private void checkPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("port should be in the range of 0 to 65535");
        }
    }

    private void checkServices(List<BindableService> services) {
        if (services == null || services.isEmpty()) {
            throw new IllegalArgumentException("none service");
        }
    }

}
