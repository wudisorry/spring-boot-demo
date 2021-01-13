package com.arh.springbootdemo;

import com.arh.springbootdemo.util.DateUtil;
import org.junit.Test;

public class TestJMM {

    @Test
    public void test() {
        ThreadData threadData = new ThreadData();
        threadData.setFlag(true);

        Thread threadA = new Thread(() -> {
            long beginTime = System.currentTimeMillis();
//            int i = 0;
//            while (threadData.isFlag() && i < Integer.MAX_VALUE) {
//                i++;
//            }
//            System.out.println("当前线程：" + Thread.currentThread().getName() + ",i：" + i + ",flag:" + threadData.isFlag());

            while (threadData.isFlag()) {
                //System.out.println("当前线程：" + Thread.currentThread().getName());
            }
            long endTime = System.currentTimeMillis();
            System.out.println("循环时间：" + (endTime - beginTime) + "ms");
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",flag:" + threadData.isFlag());
        }, "threadA");

        threadA.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(() -> {
            while (threadData.isFlag()) {
                threadData.setFlag(false);
            }
        }, "threadB");

        threadB.start();
        System.out.println(Integer.MAX_VALUE);
        threadA.interrupt();
    }

    public static void main(String[] args) {
        ThreadData threadData = new ThreadData();
        threadData.setFlag(true);
        Thread threadA = new Thread(() -> {
            while (threadData.isFlag()) {

            }
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",flag:" + threadData.isFlag());
        }, "threadA");
        threadA.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(() -> {
            while (threadData.isFlag()) {
                threadData.setFlag(false);
            }
        }, "threadB");
        threadB.start();
        System.out.println("结束主线程");
    }
}
