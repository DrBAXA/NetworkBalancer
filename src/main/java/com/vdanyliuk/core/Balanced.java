package com.vdanyliuk.core;


/**
 * Represents object that can be balanced
 * i.e. has incoming and outgoing amount
 */
public interface Balanced {

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
