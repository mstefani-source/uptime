package com.zmey.uptime.controllers;

public class Counter {

    private static volatile Counter instance;

    private int value;

    private Counter() {
        value = 0;
    }

    public static Counter getInstance() {
        if (instance == null) {
            synchronized (Counter.class) {
                if (instance == null) {
                    instance = new Counter();
                }
            }
        }
        return instance;
    }

    public int getValue() {
        return value;
    }

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public synchronized void reset() {
        value = 0;
    }
}
