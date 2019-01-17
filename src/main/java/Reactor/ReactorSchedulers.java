package Reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorSchedulers {
    public static void main(String[] args){

        //冷序列的含义是不论订阅者在何时订阅该序列，总是能收到序列中产生的全部消息。
        //Flux对象包含的唯一元素是当前线程的名称
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                //切换执行时的调度器
                .publishOn(Schedulers.single())
                //把当前的线程名称作为前缀添加。
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                //改变流产生时的执行方式
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
        //输出[elastic-2] [single-1] parallel-1
        //paraellel1来自Schedulers.parallel()， [single-1] 来自Schedulers.single()，
        //[elastic-2]来自Schedulers.elastic()

        //热序列，则是在持续不断地产生消息，订阅者只能获取到在其订阅之后产生的消息。
        //autoConnect()的作用是当 ConnectableFlux 对象有一个订阅者时就开始产生消息。
        final Flux<Long> source = Flux.intervalMillis(1000)
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();
        try{
        Thread.sleep(5000);}
        catch (Exception e){
            e.printStackTrace();
        }
        source
                .toStream()
                .forEach(System.out::println);


    }
}
