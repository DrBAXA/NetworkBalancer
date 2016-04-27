package com.vdanyliuk.core;


import lombok.Getter;
import lombok.NonNull;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;
import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.edges.ElectricNetworkEdge;
import com.vdanyliuk.core.edges.LineData;
import com.vdanyliuk.core.vertices.Vertex;
import com.vdanyliuk.exceptions.VertexIsSelfBalancedException;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents electric network graph.
 * Base model for this application.
 */
@Getter
public class Network implements Balanced {

    private final DirectedGraph<Vertex, Edge> networkGraph;

    /**
     * Create network model based on {@param networkGraph}
     *
     * @param networkGraph graph that represents this network
     * @throws NullPointerException if the specified graph is <code>null</code>.
     */
    public Network(DirectedGraph<Vertex, Edge> networkGraph) {
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

    /**
     * Add edge (line or just zero connection) between this two specified vertices.
     * In such way that first vertex will be source and second consumer.
     * So Aplus energy will be energy that is going from {@param vertex1} to {@param vertex2}
     * and vice versa with Aminus
     *
     * @param vertex1  source vertex
     * @param vertex2  consumer vertex
     * @param lineData parameters of line that connects this vertices
     * @return true if network did not contain such edge before
     * @throws NullPointerException     if any of the specified vertices or line data is <code>null</code>.
     * @throws IllegalArgumentException if  specified vertices  is equal.
     */
    public boolean addEdge(@NonNull Vertex vertex1, @NonNull Vertex vertex2, @NonNull LineData lineData) {
        Edge edge = new ElectricNetworkEdge(vertex1, vertex2, lineData);
        return addEdge(edge);
    }

    /**
     * Check if this network contains specified vertex.
     *
     * @param vertex that needs to check if network contains
     * @return true if this network contains specified vertex and false otherwise
     */
    public boolean containsVertex(Vertex vertex) {
        return networkGraph.containsVertex(vertex);
    }

    /**
     * Get outside vertices that is connected as incoming
     * (i.e. energy is doing inside this network by default)
     *
     * @return set of vertices
     */
    public Set<Vertex> getOutsideIncomingVertices() {
        return networkGraph.vertexSet().stream()
                .filter(v -> networkGraph.outgoingEdgesOf(v).size() == 1)
                .filter(v -> networkGraph.incomingEdgesOf(v).size() == 0)
                .collect(Collectors.toSet());
    }

    /**
     * Get outside vertices that is connected as outgoing
     * (i.e. energy is doing outside this network by default)
     *
     * @return set of vertices
     */
    public Set<Vertex> getOutsideOutgoingVertices() {
        return networkGraph.vertexSet().stream()
                .filter(v -> networkGraph.outgoingEdgesOf(v).size() == 0)
                .filter(v -> networkGraph.incomingEdgesOf(v).size() == 1)
                .collect(Collectors.toSet());
    }

    /**
     * Get all energy that was transferred inside into this network
     *
     * @return amount of energy in kW
     */
    @Override
    public long getIncoming() {
        return NetworkUtils.getOutgoingAmount(getOutsideIncomingVertices()) +
                NetworkUtils.getIncomingAmount(getOutsideOutgoingVertices());
    }

    /**
     * Get all energy that was transferred outside from this network
     *
     * @return amount of energy in kW
     */
    @Override
    public long getOutgoing() {
        return NetworkUtils.getIncomingAmount(getOutsideIncomingVertices()) +
                NetworkUtils.getOutgoingAmount(getOutsideOutgoingVertices());
    }

    /**
     * Define how well balanced is this network
     * Return percentage value that represent unbalanced amount divided by incoming amount
     *
     * @return <tt>(unbalanced&frasl;incoming)*100 %</tt>
     */
    public float getBalance() {
        return ((float) (getIncoming() - getOutgoing())) / getIncoming() * 100;
    }

    /**
     * Get minimal sub network of this network that can be computed
     * (i.e. all outside vertices has own incoming and outgoing values (is Counters) but not computed)
     * and contains this vertex.
     * Throws exception if specified vertex is Counter
     *
     * @param vertex that should be in result network
     *
     * @return sub network of this network
     *
     * @throws VertexIsSelfBalancedException if specified vertex has own values(i.e. is Counter)
     */
    public Network getComputableSubNetwork(Vertex vertex) throws VertexIsSelfBalancedException {
        if (!vertex.isComputed()) throw new VertexIsSelfBalancedException();

        Deque<Vertex> verticesQueue = new ArrayDeque<>();
        Set<Vertex> verticesSet = new HashSet<>();
        Set<Edge> edgesSet = new HashSet<>();

        verticesQueue.add(vertex);

        while (!verticesQueue.isEmpty()) {
            Vertex current = verticesQueue.poll();
            verticesSet.add(current);
            if (current.isComputed()) {
                Collection<Vertex> unvisitedNeighbors = getNeighbors(current);
                unvisitedNeighbors.removeAll(verticesSet);
                edgesSet.addAll(networkGraph.edgesOf(current));
                verticesQueue.addAll(unvisitedNeighbors);
            }
        }

        DirectedSubgraph<Vertex, Edge> subGraph = new DirectedSubgraph<>(networkGraph, verticesSet, edgesSet);
        return new Network(subGraph);
    }

    /**
     * Get all neighbors of specified vertex
     *
     * @param vertex neighbors of what will be returned
     *
     * @return collection of vertices that is neighbors of specified vertex
     */
    protected Collection<Vertex> getNeighbors(Vertex vertex) {
        Set<Vertex> result = new HashSet<>();

        result.addAll(networkGraph.incomingEdgesOf(vertex).stream()
                .map(Edge::getVertex1)
                .collect(Collectors.toSet()));

        result.addAll(networkGraph.outgoingEdgesOf(vertex).stream()
                .map(Edge::getVertex2)
                .collect(Collectors.toSet()));

        return result;
    }
}
