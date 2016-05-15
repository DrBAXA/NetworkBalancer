package com.vdanyliuk.core.vertices;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class VertexTest {
    @Test
    public void isComputed() throws Exception {
        assertTrue(new Vertex(){
            @Override
            public long getIncoming() {
                return 0;
            }

            @Override
            public long getOutgoing() {
                return 0;
            }
        }.isComputed());
    }

}