package com.vdanyliuk.core.vertices;

/**
 * Abstract point element in network
 */
public interface Vertex {

    /**
     * Define if incoming and outgoing data is computed
     * or gotten from real counter device
     * <p>
     * Should return false if data is gotten form some storage
     * and true if is computed based on other network elements
     * <p>
     * In child classes that get data from real device
     * should be overridden to return false
     *
     * @return true by default.
     */
    default boolean isComputed() {
        return true;
    }

    /**
     * Represent incoming amount of energy that was transferred through this vertex
     *
     * @return incoming amount of energy in kW
     */
    long getIncoming();

    /**
     * Represent outgoing amount of energy that was transferred through this vertex
     *
     * @return outgoing amount of energy in kW
     */
    long getOutgoing();

}
