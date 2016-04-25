package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract edge that can contain two vertices
 */
@Getter
public abstract class Edge {
    private final Vertex vertex1;
    private final Vertex vertex2;

    protected Edge(Vertex vertex1, Vertex vertex2) {
        if (vertex1 == null || vertex2 == null)
            throw new IllegalArgumentException("Vertices can't be null.");
        if(vertex1.equals(vertex2))
            throw new IllegalArgumentException("Vertices can't be equal.");
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Set<Vertex> getVertices() {
        return new HashSet<Vertex>() {{
            add(vertex1);
            add(vertex2);
        }};
    }
}
