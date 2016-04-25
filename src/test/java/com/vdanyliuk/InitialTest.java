package com.vdanyliuk;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class InitialTest {

    private Initial initial;

    @Before
    public void setUp() throws Exception {
        initial = new Initial();
    }

    @Test
    public void testAdd() throws Exception {
        TestCase.assertEquals(4, initial.add(2, 2));
    }

    @Test
    public void testAddMaxOverload() throws Exception {
        TestCase.assertEquals(Integer.MIN_VALUE, initial.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void testAddMinOverload() throws Exception {
        TestCase.assertEquals(Integer.MAX_VALUE, initial.add(Integer.MIN_VALUE, -1));
    }

    @Test
    public void testAddToZero() throws Exception {
        TestCase.assertEquals(0, initial.add(1, -1));
    }

    @Test
    public void testAddToZero1() throws Exception {
        TestCase.assertEquals(0, initial.add(0, 0));
    }

}