package com.vdanyliuk.core.vertices;

/**
 * Abstract point element in network
 */
public interface Vertex {

    /**
     * Represent incoming amount of energy that was transferred through this vertex
     * @return incoming amount of energy in kW
     */
    Long getIncoming();

    /**
     * Represent outgoing amount of energy that was transferred through this vertex
     * @return outgoing amount of energy in kW
     */
    Long getOutgoing();

}
