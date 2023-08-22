package com.dongyang.grpc.demo.client;

import com.dongyang.grpc.demo.client.service.HelloService;

/**
 * @author dongyang.hu
 * @date 2023/8/22 11:42 PM
 */
public class Main {

    public static void main(String[] args) {
        HelloService service = new HelloService();
        service.sayHello();
    }
}
