package com.zmey.uptime.services;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> {

    List<T> stack = new LinkedList<>();
    T max = null;

    public void push(T obj) {

        if (obj == null) {
            throw new IllegalArgumentException("cannot push null into stack");
        }
        stack.add(0, obj);
        if (max == null || max.compareTo(obj) > 0) {
            max = obj;
        }
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T removed = stack.remove(0);
        if (removed.equals(max)) {
            findAndUpdateMax();
        }
        return removed;
    }

    public T peekMax() {
        if (stack.isEmpty())
            return null;
        return max;
    }

    private T findAndUpdateMax() {
        if (stack.isEmpty())
            max = null;

        return stack.stream().max(Comparable::compareTo)
                .orElseThrow(() -> new IllegalStateException());
    }
}
