package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            /*while (iterator().hasNext()) {
                if (head.next == null) {
                    head.next = newNode;
                    break;
                }
                iterator().next();
            }*/
            for (int i = 0; i < size - 1; i++) {
                if (get(i) == null) {
                    head.next = newNode;
                }
            }
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = head;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;
            final int expectedModcount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModcount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No element found");
                }
                E value = node.item;
                node = node.next;
                return value;
            }
        };
    }
}