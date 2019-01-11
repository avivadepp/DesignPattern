package Proxy.Static;

public class StaticMain {
        public static void main(String[] args) {
            //委托类
            Principal principal = new Principal();

            //代理类
            Agent agent = new Agent(principal);

            agent.sellApple();//执行的是代理的方法
        }
}
