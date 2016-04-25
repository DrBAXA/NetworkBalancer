package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ElectricNetworkEdgeTest {

    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;
    @Mock
    private LineData lineData;

    @Test
    public void constructorTest() {
        new ElectricNetworkEdge(vertex1, vertex2, lineData){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest1() {
        new ElectricNetworkEdge(vertex1, vertex2, null){};
    }

    @Test
    public void testGetLineData() throws Exception {
        ElectricNetworkEdge electricNetworkEdge = new ElectricNetworkEdge(vertex1, vertex2, lineData) {};
        assertEquals(lineData, electricNetworkEdge.getLineData());
    }
}