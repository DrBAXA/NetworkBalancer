package com.vdanyliuk.core;


import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.vertices.Vertex;
import com.vdanyliuk.exceptions.VertexIsSelfBalancedException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubNetworkTest {

    @Mock
    Vertex vertex1;
    @Mock
    Vertex vertex2;
    @Mock
    Vertex vertex3;
    @Mock
    Vertex vertex4;
    @Mock
    Vertex vertex5;
    @Mock
    Vertex vertex6;
    @Mock
    Vertex vertex7;

    @Mock
    Edge edge1;
    @Mock
    Edge edge2;
    @Mock
    Edge edge3;
    @Mock
    Edge edge4;
    @Mock
    Edge edge5;
    @Mock
    Edge edge6;

    DirectedGraph<Vertex, Edge> graph;

    Network network;

    @Before
    public void setUp() throws Exception {
        graph = new DefaultDirectedGraph<>(Edge.class);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);

        graph.addEdge(vertex1, vertex2, edge1);

        graph.addEdge(vertex2, vertex3, edge2);
        graph.addEdge(vertex2, vertex4, edge3);

        graph.addEdge(vertex3, vertex5, edge4);

        graph.addEdge(vertex5, vertex6, edge5);
        graph.addEdge(vertex5, vertex7, edge6);

        when(vertex1.isComputed()).thenReturn(false);
        when(vertex2.isComputed()).thenReturn(true);
        when(vertex3.isComputed()).thenReturn(false);
        when(vertex4.isComputed()).thenReturn(false);
        when(vertex5.isComputed()).thenReturn(true);
        when(vertex6.isComputed()).thenReturn(false);
        when(vertex7.isComputed()).thenReturn(false);

        when(edge1.getVertex1()).thenReturn(vertex1);
        when(edge2.getVertex1()).thenReturn(vertex2);
        when(edge3.getVertex1()).thenReturn(vertex2);
        when(edge4.getVertex1()).thenReturn(vertex3);
        when(edge5.getVertex1()).thenReturn(vertex5);
        when(edge6.getVertex1()).thenReturn(vertex5);

        when(edge1.getVertex2()).thenReturn(vertex2);
        when(edge2.getVertex2()).thenReturn(vertex3);
        when(edge3.getVertex2()).thenReturn(vertex4);
        when(edge4.getVertex2()).thenReturn(vertex5);
        when(edge5.getVertex2()).thenReturn(vertex6);
        when(edge6.getVertex2()).thenReturn(vertex7);

        network = new Network(graph);
    }

    @Test
    public void testGetSubNetwork() throws Exception {
        Network subNetwork = network.getComputableSubNetwork(vertex2);

        assertTrue(subNetwork.containsVertex(vertex1));
        assertTrue(subNetwork.containsVertex(vertex2));
        assertTrue(subNetwork.containsVertex(vertex3));
        assertTrue(subNetwork.containsVertex(vertex4));

        assertFalse(subNetwork.containsVertex(vertex5));
        assertFalse(subNetwork.containsVertex(vertex6));
        assertFalse(subNetwork.containsVertex(vertex7));

    }

    @Test(expected = VertexIsSelfBalancedException.class)
    public void testGetSubNetwork1() throws Exception {
        network.getComputableSubNetwork(vertex1);
    }

    @Test(expected = VertexIsSelfBalancedException.class)
    public void testGetSubNetwork2() throws Exception {
        network.getComputableSubNetwork(vertex3);
    }

    @Test(expected = VertexIsSelfBalancedException.class)
    public void testGetSubNetwork3() throws Exception {
        network.getComputableSubNetwork(vertex7);
    }

    @Test
    public void getNeighborsTest() {
        assertThat(network.getNeighbors(vertex2), containsInAnyOrder(vertex1, vertex3, vertex4));
    }

    @Test
    public void getNeighborsTest2() {
        assertThat(network.getNeighbors(vertex4), containsInAnyOrder(vertex2));
    }

    @Test
    public void getNeighborsTest3() {
        assertThat(network.getNeighbors(vertex5), containsInAnyOrder(vertex6, vertex3, vertex7));
    }
}