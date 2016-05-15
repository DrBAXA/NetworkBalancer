package com.vdanyliuk.core.edges;


import com.vdanyliuk.core.vertices.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class LineTest {

    @Mock
    private Vertex vertex1;
    @Mock
    private Vertex vertex2;

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLineData() throws Exception {
        Line.checkLineData(new LineData(0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLineData2() throws Exception {
        Line.checkLineData(new LineData(-100, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLineData3() throws Exception {
        Line.checkLineData(new LineData(-100, -100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLineData4() throws Exception {
        Line.checkLineData(new LineData(100, -100));
    }

    @Test
    public void testCheckLineData5() throws Exception {
        Line.checkLineData(new LineData(200, 1));
    }

    @Test
    public void toStringTest() {
        Edge edge = new Line(vertex1, vertex2, new LineData(200, 1), "some line");
        assertEquals("some line", edge.toString());
        assertEquals("some line", ((Line)edge).getName());
    }
}