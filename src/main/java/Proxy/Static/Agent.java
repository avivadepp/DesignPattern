package Proxy.Static;

//代理类
public class Agent implements SellApple {
    private SellApple apple;
    public Agent(SellApple apple){
        this.apple=apple;
    }

    @Override
    public void sellApple(){
        System.out.println("before");
        apple.sellApple();
        System.out.println("after");
    }
}
