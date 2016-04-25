package com.vdanyliuk.core;

import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.vertices.Vertex;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkTest {

    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;
    @Mock
    private Graph<Vertex, Edge> graph;

    private Edge edge;
    private Network network;

    @Before
    public void setUp() throws Exception {
        network = new Network(graph);
        edge = new Edge(vertex1, vertex2) {};

        when(graph.addEdge(vertex1, vertex2, edge)).thenReturn(true);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullVertex() throws Exception {
        network.addVertex(null);
    }

    @Test
    public void testAddVertex() throws Exception {
        network.addVertex(vertex1);
        verify(graph, times(1)).addVertex(vertex1);
    }

    @Test
    public void testAddEdge() throws Exception {
        assertTrue(network.addEdge(edge));
        verify(graph, times(1)).addVertex(vertex1);
        verify(graph, times(1)).addVertex(vertex2);
        verify(graph, times(1)).addEdge(vertex1, vertex2, edge);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullEdge() throws Exception {
        assertTrue(network.addEdge(null));
        verify(graph, times(0)).addVertex(vertex1);
        verify(graph, times(0)).addVertex(vertex2);
        verify(graph, times(0)).addEdge(vertex1, vertex2, edge);
    }
}