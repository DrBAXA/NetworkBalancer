package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import lombok.Getter;

@Getter
public abstract class ElectricNetworkEdge extends Edge{

    private final LineData lineData;

    protected ElectricNetworkEdge(Vertex vertex1, Vertex vertex2, LineData lineData) {
        super(vertex1, vertex2);
        if(lineData == null) throw new IllegalArgumentException("Line data can't be null");
        this.lineData = lineData;
    }
}
