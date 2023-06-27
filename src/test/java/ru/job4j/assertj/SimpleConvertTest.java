package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .endsWith("five")
                .containsOnly("first", "second", "three", "four", "five")
                .doesNotContain("seven", "eight")
                .anyMatch(e -> Objects.equals(e, "four"))
                .allMatch(e -> e.length() < 10)
                .noneMatch(e -> e.length() > 10);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> stringList = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(stringList).hasSize(5)
                .contains("second")
                .contains("three", Index.atIndex(2))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
        assertThat(stringList)
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(2);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e).isEqualTo("three");
                });
        assertThat(stringList).filteredOn(e -> e.length() > 5).first().isEqualTo("second");
        assertThat(stringList).filteredOnAssertions(e -> assertThat(e.length()).isLessThan(6))
                .hasSize(4)
                .first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> stringSet = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(stringSet).hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .containsOnly("first", "second", "three", "four", "five")
                .doesNotContain("seven", "eight")
                .anyMatch(e -> Objects.equals(e, "four"))
                .allMatch(e -> e.length() < 10)
                .noneMatch(e -> e.length() > 10);
        assertThat(stringSet).filteredOn(e -> e.length() > 5).first().isEqualTo("second");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> toMap = simpleConvert.toMap("first", "second","three", "four", "five");
        assertThat(toMap).hasSize(5).
                containsKeys("first", "four", "five")
                .containsValues(1, 2, 4)
                .doesNotContainKey("0")
                .doesNotContainValue(7)
                .containsEntry("four", 3);
    }
}