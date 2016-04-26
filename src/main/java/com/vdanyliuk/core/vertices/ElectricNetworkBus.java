package com.vdanyliuk.core.vertices;

import lombok.Getter;
import lombok.Setter;

public class ElectricNetworkBus implements Vertex {




    @Getter
    @Setter
    private String name;

    /**
     * Returns incoming electricity amount
     * based on data from connected counter devices
     *
     * @return incoming electrical energy amount
     */
    @Override
    public long getIncoming() {
        return 0;
    }

    /**
     * Returns outgoing electrical energy amount
     * based on data from connected counter devices
     *
     * @return outgoing electrical energy amount
     */
    @Override
    public long getOutgoing() {
        return 0;
    }
}
