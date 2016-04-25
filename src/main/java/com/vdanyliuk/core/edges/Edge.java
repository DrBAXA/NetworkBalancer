package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract edge that can contain two vertices
 */
public abstract class Edge {
    private final Vertex vertex1;
    private final Vertex vertex2;

    protected Edge(Vertex vertex1, Vertex vertex2) {
        if (vertex1 == null || vertex2 == null) throw new IllegalArgumentException("Vertices can't be null.");
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Collection<Vertex> getVertices() {
        return new ArrayList<Vertex>() {{
            add(vertex1);
            add(vertex2);
        }};
    }
}
