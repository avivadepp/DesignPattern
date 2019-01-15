package Proxy.CGlib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloInterceptor implements MethodInterceptor {

    //@Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(obj, args);
    }
}
