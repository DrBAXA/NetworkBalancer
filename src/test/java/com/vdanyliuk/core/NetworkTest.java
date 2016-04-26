package com.vdanyliuk.core;

import org.jgrapht.DirectedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.edges.LineData;
import com.vdanyliuk.core.vertices.Vertex;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
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
    private DirectedGraph<Vertex, Edge> graph;
    @Mock
    private LineData lineData;

    private Edge edge;
    private Network network;

    @Before
    public void setUp() throws Exception {
        network = new Network(graph);
        edge = new Edge(vertex1, vertex2) {};

        when(graph.addEdge(any(), any(), any())).thenReturn(true);
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

    @Test
    public void testAddEdgeByVertices() throws Exception {
        assertTrue(network.addEdge(vertex1, vertex2, lineData));
        verify(graph, times(1)).addVertex(vertex1);
        verify(graph, times(1)).addVertex(vertex2);
        verify(graph, times(1)).addEdge(any(), any(), any());
    }
}