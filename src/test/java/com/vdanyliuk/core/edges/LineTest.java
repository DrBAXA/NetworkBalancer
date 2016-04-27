package com.vdanyliuk.core.edges;


import org.junit.Test;

public class LineTest {

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
}