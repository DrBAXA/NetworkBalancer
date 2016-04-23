package com.vdanyliuk;


import junit.framework.TestCase;
import org.junit.Test;

public class InitialTest {

    @Test
    public void testAdd() throws Exception {
        TestCase.assertEquals(4, Initial.add(2, 2));
    }

    @Test
    public void testAddMaxOverload() throws Exception {
        TestCase.assertEquals(-1, Initial.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void testAddMinOverload() throws Exception {
        TestCase.assertEquals(0, Initial.add(Integer.MIN_VALUE, -1));
    }

}