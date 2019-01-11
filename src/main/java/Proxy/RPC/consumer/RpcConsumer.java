package Proxy.RPC.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *除了设置project的语言级别为8时，module的级别也要设置成8.否则不支持lamda语言
 *模块的优先级高于项目的优先级
 */
public class RpcConsumer {

    private static ExecutorService executor = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static HelloClientHandler client;

    /**
     * 创建一个代理对象
     */
    public Object createProxy(final Class<?> serviceClass,
                              final String providerName) {
        //调用代理对象时，需要先初始化客户端
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass}, (proxy, method, args) -> {
                    if (client == null) {
                        initClient();
                    }
                    // 设置参数
                    client.setPara(providerName + args[0]);
                    /**
                     * 往线程池中提交一个runnable实例，并返回一个Future对象，
                     * 通过返回的Future对象，可以检查任务是否完成，Future.get()返回值为null
                     * 往线程池中提交一个callable实例，并返回一个Future对象，
                     * Future.get()返回值为任务执行结果
                     */

                    return executor.submit(client).get();
                });
    }

    /**
     * 初始化客户端
     */
    private static void initClient() {
        client = new HelloClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new StringDecoder());
                        p.addLast(new StringEncoder());
                        p.addLast(client);
                    }
                });
        try {
            b.connect("localhost", 8088).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

