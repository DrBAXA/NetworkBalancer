package com.vdanyliuk;


import junit.framework.TestCase;
import org.junit.Test;

public class InitialTest {

    @Test
    public void add() throws Exception {
        TestCase.assertEquals(4, Initial.add(2, 2));
    }

}