package Proxy.RPC.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * Callable 接口类似于 Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。
 * 但是 Runnable 不会返回结果，并且无法抛出经过检查的异常。
 * 而Callable可以返回一个结果，这个返回值可以被Future拿到
 * Future是一个代理或者一个对象的包装，不是真实的目标对象。
 * 一旦异步计算完成，你就可以提取它。
 */
public class HelloClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;
    private String para;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        context = ctx;
    }

    /**
     * 收到服务端数据，唤醒等待线程
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg.toString();
        notify();
    }

    /**
     * 写出数据，开始等待唤醒
     */
    public synchronized Object call() throws InterruptedException {
        context.writeAndFlush(para);
        wait();
        return result;
    }

    void setPara(String para) {
        this.para = para;
    }
}

