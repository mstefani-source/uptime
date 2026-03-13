package com.zmey.uptime.tools;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

@Component
class AppStack<T extends Comparable<T>> {
    private T max = null;
    private LinkedList<T> stack = new LinkedList<>();

    public void push(T item) {
        if (max == null || item.compareTo(max) > 0) {
            this.max = item;
        }
        stack.addFirst(item);
    }

    public T pop() {
        T popped = stack.pop();
        if (popped.equals(max)) {
            this.max = findmax(stack);
        }
        return popped;
    }

    public T peekMax() {
        if (max == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return this.max;
    }

    private T findmax(LinkedList<T> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        T max = stack.getFirst();

        for (T item : stack) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

}