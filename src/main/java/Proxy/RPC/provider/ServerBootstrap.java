package Proxy.RPC.provider;

public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("localhost", 8088);
    }
}
