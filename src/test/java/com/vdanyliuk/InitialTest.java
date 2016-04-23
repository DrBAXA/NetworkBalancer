package com.vdanyliuk;


import junit.framework.TestCase;
import org.junit.Test;

public class InitialTest {

    @Test
    public void testAdd() throws Exception {
        TestCase.assertEquals(4, Initial.add(2, 2));
    }

    @Test
    public void testAddOverload() throws Exception {
        TestCase.assertEquals(-1, Initial.add(Integer.MAX_VALUE, 1));
    }

}