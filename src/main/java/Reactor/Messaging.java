package Reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Messaging {
    public static void main(String[] args){
        //concatWith顺序合并
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);

        //错误处理之一：通过onErrorReturn返回一个值
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(1)
                .subscribe(System.out::println);

        //错误处理之二：通过switchOnError()方法来使用另外的流来产生元素。
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .switchOnError(Mono.just(0))
                .subscribe(System.out::println);

        //错误处理之三：通过 onErrorResumeWith()方法来根据不同的异常类型来选择要使用的产生元素的流。
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResumeWith(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);

        //错误处理之四：通过retry进行重试，不仅仅是重试异常那步，而是整个序列都重试
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);
    }
}
