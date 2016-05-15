package com.vdanyliuk.core.edges;

import com.vdanyliuk.core.vertices.Vertex;
import lombok.Getter;

@Getter
public class ElectricNetworkEdge extends Edge{

    public static final LineData ZERO_LINE_DATA = new LineData(0L, Long.MAX_VALUE);

    private final LineData lineData;

    public ElectricNetworkEdge(Vertex vertex1, Vertex vertex2, LineData lineData) {
        super(vertex1, vertex2);
        if(lineData == null) throw new NullPointerException("Line data can't be null.");
        this.lineData = lineData;
    }

    public static ElectricNetworkEdge line(Vertex vertex1, Vertex vertex2, Long length, Long section) {
        return new ElectricNetworkEdge(vertex1, vertex2, new LineData(length, section));
    }

    public static ElectricNetworkEdge zeroConnection(Vertex vertex1, Vertex vertex2) {
        return new ElectricNetworkEdge(vertex1, vertex2, ZERO_LINE_DATA);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ElectricNetworkEdge that = (ElectricNetworkEdge) o;

        return lineData.equals(that.lineData);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + lineData.hashCode();
        return result;
    }
}
