package Proxy.provider;

import Proxy.publicInterface.HelloService;

//委托类
public class HelloServiceImpl implements HelloService {
    public String hello(String msg) {
        return msg!=null?msg+" -----> I am fine." : "I am fine.";
    }
}
