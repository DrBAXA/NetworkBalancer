package com.vdanyliuk.core.vertices;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Counter implements Vertex {

    private String name;

    private String number;

    private long Aplus;
    private long Aminus;

    private boolean reverse;

    @Override
    public boolean isComputed() {
        return false;
    }

    @Override
    public long getIncoming() {
        return reverse ? Aplus : Aminus;
    }

    @Override
    public long getOutgoing() {
        return reverse ? Aminus : Aplus;
    }
}
