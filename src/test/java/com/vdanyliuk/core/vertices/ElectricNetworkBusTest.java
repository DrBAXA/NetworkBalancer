package com.vdanyliuk.core.vertices;


import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.vdanyliuk.core.edges.Edge;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ElectricNetworkBusTest {

    private DirectedGraph<Vertex, Edge> graph;
    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;
    @Mock
    private Vertex vertex3;
    @Mock
    private Edge edge1;
    @Mock
    private Edge edge2;
    @Mock
    private Edge edge3;

    ElectricNetworkBus bus;

    @Before
    public void setUp() throws Exception {
        graph = new SimpleDirectedGraph<>(Edge.class);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        bus = new ElectricNetworkBus(graph, "BUS");
        graph.addVertex(bus);

        graph.addEdge(vertex1, bus, edge1);
        graph.addEdge(bus, vertex2, edge2);
        graph.addEdge(bus, vertex3, edge3);

        when(vertex1.getIncoming()).thenReturn(1000L);
        when(vertex2.getIncoming()).thenReturn(500L);
        when(vertex3.getIncoming()).thenReturn(500L);
        when(vertex1.getOutgoing()).thenReturn(500L);
        when(vertex2.getOutgoing()).thenReturn(500L);
        when(vertex3.getOutgoing()).thenReturn(0L);

        when(edge1.getVertex1()).thenReturn(vertex1);
        when(edge1.getVertex2()).thenReturn(bus);
        when(edge2.getVertex1()).thenReturn(bus);
        when(edge2.getVertex2()).thenReturn(vertex2);
        when(edge3.getVertex1()).thenReturn(bus);
        when(edge3.getVertex2()).thenReturn(vertex3);
    }

    @Test
    public void testGetIncoming() throws Exception {
        assertEquals(1500, bus.getIncoming());
    }

    @Test
    public void testGetOutgoing() throws Exception {
        assertEquals(1500, bus.getOutgoing());
    }
}