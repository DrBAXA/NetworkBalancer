package com.vdanyliuk.core;

import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.vertices.Vertex;
import org.jgrapht.graph.SimpleGraph;

/**
 * Represents electric network graph.
 * Base model for this application.
 */
public class Network<V extends Vertex, E extends Edge> extends SimpleGraph<V, E> {

    public Network(Class<? extends E> edgeClass) {
        super(edgeClass);
    }

    @Override
    public E addEdge(V v1, V v2) {
        throw new IllegalArgumentException("Can't add edge without parameters. Add edge to arguments.");
    }
}
