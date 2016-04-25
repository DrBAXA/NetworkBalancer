package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(MockitoJUnitRunner.class)
public class EdgeTest {

    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;

    @Test
    public void constructorTest() {
        new Edge(vertex1, vertex2) {};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestException1() {
        new Edge(vertex1, null) {};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestException2() {
        new Edge(null, null) {};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestException3() {
        new Edge(null, vertex2) {};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestException4() {
        new Edge(vertex1, vertex1) {};
    }

    @Test
    public void getVerticesTest() {
        Edge testEdge = new Edge(vertex1, vertex2) {};

        assertThat(testEdge.getVertices(), containsInAnyOrder(vertex1, vertex2));
    }
}