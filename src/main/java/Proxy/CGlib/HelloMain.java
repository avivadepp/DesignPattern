package Proxy.CGlib;

import net.sf.cglib.proxy.Enhancer;

public class HelloMain {
    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new HelloInterceptor());

        Hello hello = (Hello)enhancer.create();
        hello.sayHello("Hello!You idiot!");
    }
}
