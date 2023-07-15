package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    public void testRemoveIf() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        Predicate<Integer> filter = n -> n % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    public void testReplaceIf() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        Predicate<Integer> filter = n -> n % 2 == 0;
        ListUtils.replaceIf(input, filter, 7);
        assertThat(input).hasSize(5).containsSequence(1, 7, 3, 7, 5);
    }

    @Test
    public void testRemoveAll() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 5));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }
}