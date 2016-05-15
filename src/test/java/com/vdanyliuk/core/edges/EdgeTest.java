package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
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

    @Test
    public void equalsTest() {
        assertTrue(new Edge(vertex1, vertex2){}.equals(new Edge(vertex1, vertex2) {
        }));

        assertTrue(new Edge(vertex1, vertex2){}.equals(ElectricNetworkEdge.zeroConnection(vertex1, vertex2)));

        assertFalse(new Edge(vertex1, vertex2){}.equals(new Edge(vertex2, vertex1){}));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(new Edge(vertex1, vertex2){}.equals(new Edge(vertex1, vertex2) {
        }));

        assertEquals(new Edge(vertex1, vertex2){}.hashCode(), (new Edge(vertex1, vertex2) {
        }.hashCode()));
    }

}