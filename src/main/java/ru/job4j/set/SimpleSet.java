package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean addValue = contains(value);
        if (!addValue) {
            set.add(value);
        }
        return addValue;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> it = set.iterator();
        boolean result = false;
        while (it.hasNext()) {
            if (Objects.equals(value, it.next())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}