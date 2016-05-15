package com.vdanyliuk.core.vertices;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

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

    @Test
    public void testToString() {
        Counter counter = new Counter("A1", "11", true);
        assertEquals("A1 11", counter.toString());
        counter.setNumber("123456");
        assertTrue(counter.toString().endsWith(counter.getNumber()));

        counter = new Counter("A1");
        assertEquals(counter.getName(), counter.toString());
        assertEquals("A1", counter.toString());

        counter.setName("counter");
        assertEquals("counter A1", counter.toString());
    }

    @Test
    public void testReverse() {
        Counter counter = new Counter("A1", "11", true);

        counter.setAplus(1000);
        counter.setAminus(500);

        assertEquals(1000, counter.getIncoming());
        assertEquals(500, counter.getOutgoing());

        counter.setReverse(false);

        assertEquals(500, counter.getIncoming());
        assertEquals(1000, counter.getOutgoing());
    }

    @Test
    public void testIsComputed() {
        Counter counter = new Counter("A1", "11", true);
        assertFalse(counter.isComputed());
    }
}