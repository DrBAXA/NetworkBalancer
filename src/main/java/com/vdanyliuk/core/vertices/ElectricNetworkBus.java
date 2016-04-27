package com.vdanyliuk.core.vertices;


import lombok.Getter;
import lombok.Setter;
import org.jgrapht.DirectedGraph;
import com.vdanyliuk.core.edges.Edge;


public class ElectricNetworkBus implements Vertex {

    private final DirectedGraph<Vertex, Edge> parent;

    @Getter
    @Setter
    private String name;

    public ElectricNetworkBus(DirectedGraph<Vertex, Edge> parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    /**
     * Returns incoming electricity amount
     * based on data from connected counter devices
     *
     * @return incoming electrical energy amount
     */
    @Override
    public long getIncoming() {
        long inputFromSources =  parent.incomingEdgesOf(this).stream()
                .map(Edge::getVertex1)
                .mapToLong(Vertex::getIncoming)
                .sum();

        long reverseFromConsumers = parent.outgoingEdgesOf(this).stream()
                .map(Edge::getVertex2)
                .mapToLong(Vertex::getOutgoing)
                .sum();

        return inputFromSources + reverseFromConsumers;
    }

    /**
     * Returns outgoing electrical energy amount
     * based on data from connected counter devices
     *
     * @return outgoing electrical energy amount
     */
    @Override
    public long getOutgoing() {
        long outputToConsumers =  parent.outgoingEdgesOf(this).stream()
                .map(Edge::getVertex2)
                .mapToLong(Vertex::getIncoming)
                .sum();

        long reverseToSources = parent.incomingEdgesOf(this).stream()
                .map(Edge::getVertex1)
                .mapToLong(Vertex::getOutgoing)
                .sum();

        return outputToConsumers + reverseToSources;
    }

    @Override
    public String toString() {
        return name;
    }
}
