package com.vdanyliuk.core.edges;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LineDataTest {

    @Test
    public void constrictorTest() {
        LineData lineData = new LineData(1, 1);
        assertEquals(1L, lineData.getLineLength());
        assertEquals(1L, lineData.getSection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constrictorTest1() {
        LineData lineData = new LineData(-1, 1);
    }
}