package com.vdanyliuk.core;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkUtilsTest {

    @Mock
    Balanced balanced1;
    @Mock
    Balanced balanced2;
    @Mock
    Balanced balanced3;

    @Before
    public void setUp() throws Exception {
        when(balanced1.getIncoming()).thenReturn(200L);
        when(balanced2.getIncoming()).thenReturn(300L);
        when(balanced3.getIncoming()).thenReturn(500L);

        when(balanced1.getOutgoing()).thenReturn(200L);
        when(balanced2.getOutgoing()).thenReturn(300L);
        when(balanced3.getOutgoing()).thenReturn(500L);
    }

    @Test
    public void testGetOutgoingAmount1() throws Exception {
        assertEquals(0, NetworkUtils.getOutgoingAmount(Collections.emptyList()));
    }

    @Test
    public void testGetIncomingAmount1() throws Exception {
        assertEquals(0, NetworkUtils.getIncomingAmount(Collections.emptyList()));
    }

    @Test
    public void testGetOutgoingAmount2() throws Exception {
        assertEquals(1000, NetworkUtils.getOutgoingAmount(new ArrayList<Balanced>(){{
            add(balanced1);
            add(balanced2);
            add(balanced3);
        }}));
    }

    @Test
    public void testGetIncomingAmount2() throws Exception {
        assertEquals(1000, NetworkUtils.getIncomingAmount(new ArrayList<Balanced>(){{
            add(balanced1);
            add(balanced2);
            add(balanced3);
        }}));
    }
}