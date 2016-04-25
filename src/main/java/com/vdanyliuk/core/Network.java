package com.vdanyliuk.core;

import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.edges.ElectricNetworkEdge;
import com.vdanyliuk.core.edges.LineData;
import com.vdanyliuk.core.vertices.Vertex;
import lombok.Getter;
import lombok.NonNull;
import org.jgrapht.Graph;

/**
 * Represents electric network graph.
 * Base model for this application.
 */
@Getter
public class Network {

    private final Graph<Vertex, Edge> networkGraph;

    /**
     * Create network model based on {@param networkGraph}
     *
     * @param networkGraph graph that represents this network
     * @throws NullPointerException if the specified graph is <code>null</code>.
     */
    public Network(Graph<Vertex, Edge> networkGraph) {
        if (networkGraph == null) throw new NullPointerException("Graph can't be null.");
        this.networkGraph = networkGraph;
    }

    /**
     * Add one vertex to this graph
     *
     * @param vertex vertex to be added to this graph.
     * @return <tt>true</tt> if this graph did not already contain the specified vertex.
     * @throws NullPointerException if the specified vertex is <code>null</code>.
     */
    public <V extends Vertex> boolean addVertex(@NonNull V vertex) {
        return networkGraph.addVertex(vertex);
    }

    /**
     * Add edge to model of network. Edge can be line
     * or just zero length connection
     *
     * @param edge edge to be added to network
     * @param <E>  type of edge
     * @return true if network did not already contain this edge
     * @throws ClassCastException   if the specified edge is not assignment
     *                              compatible with the class of edges produced by the edge factory of this
     *                              graph.
     * @throws NullPointerException if any of the specified vertices is <code>
     *                              null</code>.
     */
    public <E extends Edge> boolean addEdge(@NonNull E edge) {
        if (!networkGraph.containsVertex(edge.getVertex1()))
            networkGraph.addVertex(edge.getVertex1());
        if (!networkGraph.containsVertex(edge.getVertex2()))
            networkGraph.addVertex(edge.getVertex2());
        return networkGraph.addEdge(edge.getVertex1(), edge.getVertex2(), edge);
    }

    public boolean addEdge(@NonNull Vertex vertex1, @NonNull Vertex vertex2, LineData lineData) {
        Edge edge = new ElectricNetworkEdge(vertex1, vertex2, lineData);
        return addEdge(edge);
    }

}
