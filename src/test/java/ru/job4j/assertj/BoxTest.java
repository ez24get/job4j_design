package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 20);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(5, 110);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void tetrahedronSquare() {
        Box box = new Box(4, 10);
        double tSquare = box.getArea();
        assertThat(tSquare).isEqualTo(173.20508075688772);
    }

    @Test
    void sphereSquare() {
        Box box = new Box(0, 10);
        double tSquare = box.getArea();
        assertThat(tSquare).isEqualTo(1256.6370614359173);
    }

    @Test
    void numberOfVertex() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4);
    }

    @Test
    void numberOfVertexWrong() {
        Box box = new Box(5, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void figureNotExists() {
        Box box = new Box(7, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void figureExists() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }
}