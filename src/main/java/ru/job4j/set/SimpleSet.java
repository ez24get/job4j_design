package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean containsValue = contains(value);
        if (!containsValue) {
            set.add(value);
        }
        return !containsValue;
    }

    @Override
    public boolean contains(T value) {
        for (T element : set) {
            if (Objects.equals(value, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}