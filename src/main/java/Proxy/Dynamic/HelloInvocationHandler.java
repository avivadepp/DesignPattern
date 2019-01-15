package Proxy.Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//中介类
public class HelloInvocationHandler implements InvocationHandler {

    private Hello hello;
    public HelloInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    //@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        return method.invoke(hello, args);
    }
}
