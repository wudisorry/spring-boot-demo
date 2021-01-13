package com.arh.springbootdemo;

import org.apache.catalina.Executor;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TestThread {

    @Test
    public void testThread() {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("在callable内部，当前线程：" + Thread.currentThread().getName());
                return "success";
            }
        });

        Thread thread = new Thread(futureTask, "线程1号");
        //通过start()来启动FutureTask表示的计算，否则下面get()会阻塞
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println(result + "，当前线程：" + Thread.currentThread().getName());
            String result2 = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testExecutor() throws InterruptedException {
//        Executor executor = Executors.newFixedThreadPool(10);
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                //处理异步任务
//            }
//        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<String> callable = () -> {
            System.out.println("在callable内部，当前线程：" + Thread.currentThread().getName());
            return "success";
        };
        Future<String> future = executorService.submit(callable);
        System.out.println("当前线程开始睡眠");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("当前线程结束睡眠");
        try {
            String result = future.get();
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAtmoic() {

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        int expect = 1, update = 2;
        atomicInteger.compareAndSet(expect, update);

        AtomicReference<String> atomicReference = new AtomicReference<>("aaa");
        String expectStr = "aaa", updateStr = "bbb";
        boolean result = atomicReference.compareAndSet(expectStr, updateStr);
        System.out.println(result);
        System.out.println(atomicReference.get());

    }
}
