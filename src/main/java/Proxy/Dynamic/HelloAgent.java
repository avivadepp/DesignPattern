package Proxy.Dynamic;

import java.lang.reflect.Proxy;
import java.lang.*;

public class HelloAgent {
    // 2. 然后在需要使用Hello的时候，通过JDK动态代理获取Hello的代理对象。
    public static void main(String[] args) {
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 1. 类加载器
                new Class<?>[]{Hello.class}, // 2. 代理需要实现的接口，可以有多个
                new HelloInvocationHandler(new HelloImpl()));// 3. 方法调用的实际处理者
        hello.sayHello(); //实际上会调用invoke
        System.out.println("You idiot!");
    }
}
