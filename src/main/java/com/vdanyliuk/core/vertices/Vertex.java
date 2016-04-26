package com.vdanyliuk.core.vertices;


import com.vdanyliuk.core.Balanced;

/**
 * Abstract point element in network
 */
public interface Vertex extends Balanced{

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

}
