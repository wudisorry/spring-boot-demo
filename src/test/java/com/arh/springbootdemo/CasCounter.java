package com.arh.springbootdemo;

//非阻塞计数器
public class CasCounter {

    private SimulatedCAS simulatedCAS;

    public int getValue() {
        return simulatedCAS.get();
    }

    public int increment() {
        int v;
        do {
            v = simulatedCAS.get();

        } while (v != simulatedCAS.compareAndSwap(v, v + 1));
        return v + 1;
    }
}

//模拟cas类
class SimulatedCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    //如果预期值与当前值不相同，则不作修改，并返回当前值
    //如果预期值与当前值相同，则修改，并返回已经变成旧值的当前值
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
