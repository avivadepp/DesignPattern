package Reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Thread.sleep;

public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    public static void main(String[] args){
        //just():可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
        //Flux<String> fewWords = Flux.just("Hello", "World");
        //fromArray()，fromIterable()和 fromStream()可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
        //Flux<String> manyWords = Flux.fromIterable(words);
        //fewWords.subscribe(System.out::println);
        //System.out.println();
        //manyWords.subscribe(System.out::println);

        //empty()创建一个不包含任何元素，只发布结束消息的队列，输出为空
        //Flux.empty().subscribe(System.out::println);

        //创建指定数量的队列，输出为12345678910
        //Flux.range(1, 10).subscribe(System.out::println);

        //创建一个队列，以指定时间间隔来发布消息,无输出
        //Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);
        //sleep期间可以输出上面一条
        /*try{
        sleep(10000);}
        catch (Exception e){
            e.printStackTrace();
        }*/
        //序列的生成是异步的，而转换成 Stream 对象可以保证主线程在序列生成完成之前不会退出，从而可以正确地输出序列中的所有元素。
        //输出0，1，2，3……间隔两秒
        //Flux.interval(Duration.of(2, ChronoUnit.SECONDS)).toStream().forEach(System.out::println);

        //使用generate方法生成Flux序列，调用的是SynchronousSink对象，next()方法最多只能被调用一次，输出Hello
        /*Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);*/

        //没有complete方法，生成的是无限序列，循环输出Hello
        //Flux.generate(sink->sink.next("Hello")).subscribe(System.out::println);

        //输出10个随机数
        /*final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);*/

        //create方法调用的是FluxSink对象。FluxSink支持同步和异步的消息产生，并且可以在一次调用中产生多个元素。
        /*Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);*/

        //Flux可以发布0-N个元素，Mono只能发布0-1个元素。
        //Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);

        //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        //Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        //Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

        //buffer 和 bufferTimeout ，bufferUntil 和 bufferWhile 是把当前流中的元素收集到集合中，并把集合对象作为流中的新元素。
        //输出[1, 2]
        //[3, 4]
        //[5, 6]
        //[7, 8]
        //[9, 10]，每当遇到一个偶数就会结束当前的收集
        //Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        //输出[2]
        //[4]
        //[6]
        //[8]
        //[10]，当条件为true时才会进行搜集
        //Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

        //filter对流中元素进行过滤，只留下满足指定条件的元素，输出246810
        //Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

        //window的作用和buffer类似，但是是把元素收集到另一个Flux对象中，因此返回的是Flux<Flux<T>>
        //输出5个“UnicastProcessor”
        //Flux.range(1, 100).window(20).subscribe(System.out::println);

        //zipWith操作符把当前流中的元素与另外一个流中的元素按照一对一的方式进行合并。
        //在合并时可以不做任何处理，由此得到的是一个元素类型为 Tuple2 的流；
        //输出[a,c]
        //[b,d]
        //Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
        // 也可以通过一个 BiFunction 函数对合并的元素进行处理，所得到的流的元素类型为该函数的返回值。
        //输出a-c，b-d，元素类型为String
        /*Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);*/
        //take，takeLast，takeWhile，takeUntil用来从当前流中提取元素，输出123456789
        //Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);

        //reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列。
        //输出5150
        //Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

        //merge 和 mergeSequential 操作符用来把多个流合并成一个 Flux 序列。
        // 不同之处在于 merge 按照所有流中元素的实际产生顺序来合并，而 mergeSequential 则按照所有流被订阅的顺序，以流为单位进行合并。
        //输出0011223344
        Flux.merge(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5))
                .toStream()
                .forEach(System.out::println);

        //输出0123401234
        Flux.mergeSequential(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5))
                .toStream()
                .forEach(System.out::println);

        //flatMap 和 flatMapSequential 操作符把流中的每个元素转换成一个流，再把所有流中的元素进行合并。
        //输出001122334456789
        Flux.just(5, 10)//输出0-4
                .flatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))//输出0-9
                .toStream()
                .forEach(System.out::println);

        //concatMap 会根据原始流中的元素顺序依次把转换之后的流进行合并；与 flatMapSequential
        //不同的是，concatMap 对转换之后的流的订阅是动态进行的，而 flatMapSequential 在合并之前就已经订阅了所有的流。
       //输出012340123456789
        Flux.just(5, 10)
                .concatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
                .toStream()
                .forEach(System.out::println);

        //combineLatest 操作符把所有流中的最新产生的元素合并成一个新的元素，作为返回结果流中的元素。
        // 只要其中任何一个流中产生了新的元素，合并操作就会被执行一次，结果流中就会产生新的元素。
        /*          序列1        序列2        合并
          0ms:       0
          50ms:      0            0          [0,0]
          100ms：    1            0           [1,0]
          150ms:     1            1           [1,1]
          200ms:      2            1           [2,1]
          250ms:      2            2           [2,2]
          300ms:      3            2           [3,2]
          350ms:      3            3           [3,3]
          400ms:      4            3           [4,3]
          450ms:      4            4           [4,4]
         */
        //推测结果和实际结果顺序相反，不晓得为什么
        Flux.combineLatest(
                Arrays::toString,
                Flux.intervalMillis(100).take(5),
                Flux.intervalMillis(50, 100).take(5)
        ).toStream().forEach(System.out::println);
    }

}
