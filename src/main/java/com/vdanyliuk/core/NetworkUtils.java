package com.vdanyliuk.core;


import java.util.Collection;

public class NetworkUtils {

    /**
     * Get sum of outgoing energy amount of all specified vertices
     *
     * @param vertices outgoing amount of whose should be computed
     *
     * @return amount of energy
     */
    public static long getOutgoingAmount(Collection<? extends Balanced> vertices) {
        return vertices.stream()
                .mapToLong(Balanced::getOutgoing)
                .sum();
    }

    /**
     * Get sum of incoming energy amount of all specified vertices
     *
     * @param vertices outgoing amount of whose should be computed
     *
     * @return amount of energy
     */
    public static long getIncomingAmount(Collection<? extends Balanced> vertices) {
        return vertices.stream()
                .mapToLong(Balanced::getIncoming)
                .sum();
    }

}
