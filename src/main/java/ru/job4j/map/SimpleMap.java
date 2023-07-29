package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean rsl = table[indexOf(key)] == null;
        if (table[indexOf(key)] == null) {
            table[indexOf(key)] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(K key) {
        int hashCode = key == null ? 0 : key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexOf(K key) {
        return indexFor(hash(key));
    }

    private boolean hashCodeOf(K key) {
        return Objects.hashCode(table[indexOf(key)].key) == Objects.hashCode(key)
                && Objects.equals(table[indexOf(key)].key, key);
    }

    private void expand() {
        if (count >= table.length * LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> kvMapEntry : table) {
                if (kvMapEntry != null) {
                    int index = indexFor(hash(kvMapEntry.key));
                    newTable[index] = kvMapEntry;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        if (table[indexOf(key)] != null) {
            if (hashCodeOf(key)) {
                value = table[indexOf(key)].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key));
        if (table[index] != null && hash(table[index].key)
                == hash(key) && Objects.equals(table[index].key, key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<>() {
            final int expectModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }
}