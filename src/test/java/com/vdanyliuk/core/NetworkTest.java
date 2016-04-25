package com.vdanyliuk.core;

import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.vertices.Vertex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NetworkTest {

    private Network<Vertex, Edge> network;

    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;

    @Before
    public void setUp() throws Exception {
        network = new Network<>(Edge.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge() throws Exception {
        network.addEdge(vertex1, vertex2);
    }
}