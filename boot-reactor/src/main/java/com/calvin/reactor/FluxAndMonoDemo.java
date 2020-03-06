package com.calvin.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FluxAndMonoDemo {
    public static void main(String[] args) {
        //just()就是一种比较直接的声明数据流的方式，其参数就是数据元素。
        Flux.just(1, 2, 3, 4, 5, 6);
        Mono.just(1);

        Integer[] array = new Integer[]{1,2,3,4,5,6};
        //声明数据流的方式-基于数组生成
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        //声明数据流的方式-基于集合生成
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        //声明数据流的方式-基于Stream生成
        Flux.fromStream(stream);

        //subscribe 订阅
        Flux.just(array).subscribe(System.out::print);
        System.out.println();
        Mono.just(1).subscribe(System.out::print);
        /**
         * // 订阅并触发数据流
         *     subscribe();
         * // 订阅并指定对正常数据元素如何处理
         *      subscribe(Consumer<? super T> consumer);
         * // 订阅并定义对正常数据元素和错误信号的处理
         *     subscribe(Consumer<? super T> consumer,
         *                 Consumer<? super Throwable> errorConsumer);
         * // 订阅并定义对正常数据元素、错误信号和完成信号的处理
         *     subscribe(Consumer<? super T> consumer,
         *                 Consumer<? super Throwable> errorConsumer,
         *                 Runnable completeConsumer);
         * // 订阅并定义对正常数据元素、错误信号和完成信号的处理，以及订阅发生时的处理逻辑
         *         subscribe(Consumer<? super T> consumer,
         *                 Consumer<? super Throwable> errorConsumer,
         *                 Runnable completeConsumer,
         *                 Consumer<? super Subscription> subscriptionConsumer);
         */
        Flux.just(array).subscribe(System.out::println,
                System.err::println,
                ()-> {System.out.println("Completed");});


        /****************操作符****/
        //1）map - 元素映射为新元素
        // map操作可以将数据元素进行转换/映射，得到一个新元素。
        // public final <V> Flux<V> map(Function<? super T,? extends V> mapper)
        //public final <R> Mono<R> map(Function<? super T, ? extends R> mapper)
        // map接受一个Function的函数式接口为参数，这个函数式的作用是定义转换操作的策略
        StepVerifier.create(Flux.range(1,6).map(i-> i*i ))
                .expectNext(1,4,9,16,25,36)
                .expectComplete().verify();
        //2）flatMap - 元素映射为流
        //flatMap操作可以将每个数据元素转换/映射为一个流，然后将这些流合并为一个大的数据流。
        //!!!!注意:流的合并是异步的，先来先到，并非是严格按照原始序列的顺序
        //public final <R> Flux<R> flatMap(Function<? super T, ? extends Publisher<? extends R>> mapper)
        //public final <R> Mono<R> flatMap(Function<? super T, ? extends Mono<? extends R>> transformer)
        //flatMap也是接收一个Function的函数式接口为参数，这个函数式的输入为一个T类型数据值，对于Flux来说输出可以是Flux和Mono，对于Mono来说输出只能是Mono
        StepVerifier.create(Flux.just("flux","mono").flatMap(s->Flux.fromArray(s.split("\\s*"))
                .delayElements(Duration.ofMillis(100)))
                .doOnNext(System.out::println))
                .expectNextCount(8)
                .verifyComplete();
        //3）filter - 过滤
        //filter操作可以对数据元素进行筛选。
        //public final Flux<T> filter(Predicate<? super T> tester)
        //public final Mono<T> filter(Predicate<? super T> tester)
        //filter接受一个Predicate的函数式接口为参数，这个函数式的作用是进行判断并返回boolean
        StepVerifier.create(Flux.range(1,9)
        .filter(i -> i%2 == 1)
        .map(i->i*i))
                .expectNext(1,9,25,49,81)
                .verifyComplete();
        //4）zip: 一对一合并
        //看到zip这个词可能会联想到拉链，它能够将多个流一对一的合并起来。zip有多个方法变体
        //最常见的变体：它对两个Flux/Mono流每次各取一个元素，合并为一个二元组（Tuple2）
        //public static <T1,T2> Flux<Tuple2<T1,T2>> zip(Publisher<? extends T1> source1,  Publisher<? extends T2> source2)
        //public static <T1, T2> Mono<Tuple2<T1, T2>> zip(Mono<? extends T1> p1, Mono<? extends T2> p2)

        //Flux的zip方法接受Flux或Mono为参数，Mono的zip方法只能接受Mono类型的参数



    }
    /**
     * 单元测试工具——StepVerifier
     */
    private Flux<Integer> generateFluxFrom1To6(){
        return Flux.just(1,2,3,4,5,6);
    }

    private Mono<Integer> generateMonoWithError(){
        return Mono.error(new Exception("some error"));
    }

    @Test
    public void testStepVerifier(){
        StepVerifier.create(generateFluxFrom1To6())
                .expectNext(1,2,3,4,5,6)
                .expectComplete()
                .verify();
        StepVerifier.create(generateMonoWithError())
                .expectErrorMessage("some error")
                .verify();
    }

    private String getStringSync(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello Reactor";
    }

    @Test
    public void testSyncToAsync() throws InterruptedException {
        System.out.println("11111   "+System.currentTimeMillis());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(()->getStringSync())
                .subscribeOn(Schedulers.elastic())
                .subscribe(System.out::println,null,countDownLatch::countDown);
        countDownLatch.await(10,TimeUnit.SECONDS);
        System.out.println("222   "+System.currentTimeMillis());
    }

    @Test
    public void testBuckPressure(){
        Flux.range(1,10)
                .doOnRequest(n-> System.out.println("Request "+n+" values..."))
                .subscribe(new BaseSubscriber<Integer>() {
                    //hookOnSubscribe定义在订阅的时候执行的操作
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed and make a request...");
                        request(2);
                    }

                    //hookOnNext定义每次在收到一个元素的时候的操作；
                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 7
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");    // 8
                        request(2); // 9
                    }
                });
    }
}
