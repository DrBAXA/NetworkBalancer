package com.vdanyliuk.core.vertices;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CounterTest {

    @Test
    public void testGetIncoming() throws Exception {
        Counter counter = new Counter("A1", "11");


        counter.setAplus(1000);
        counter.setAminus(500);

        assertEquals(500, counter.getIncoming());
        assertEquals(1000, counter.getOutgoing());
    }

    @Test
    public void testGetOutgoing() throws Exception {
        Counter counter = new Counter("A1", "11", true);

        counter.setAplus(1000);
        counter.setAminus(500);

        assertEquals(1000, counter.getIncoming());
        assertEquals(500, counter.getOutgoing());
    }
}