package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;
    private int count = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (count != size) {
            out.push(in.pop());
            count++;
        }
        size = 0;
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}