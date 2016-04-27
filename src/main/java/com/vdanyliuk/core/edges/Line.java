package com.vdanyliuk.core.edges;



import lombok.Data;
import lombok.EqualsAndHashCode;
import com.vdanyliuk.core.vertices.Vertex;


/**
 * Represents electrical line
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Line extends ElectricNetworkEdge {

    private String name;

    /**
     * Create line that starts at {@param vertex1} and ends at {@param vertex2}
     * with {@param lineData} parameters and {@param name}
     *
     * @param vertex1  start of line
     * @param vertex2 end of line
     * @param lineData line parameters
     * @param name name of line
     *
     * @throws IllegalArgumentException if any line parameter is less or equal than zero
     */
    public Line(Vertex vertex1, Vertex vertex2, LineData lineData, String name) {
        super(vertex1, vertex2, lineData);
        checkLineData(lineData);
        this.name = name;
    }

    static void checkLineData(LineData lineData) {
        if(lineData.getLineLength() <= 0) throw new IllegalArgumentException("Line length should be greater than zero.");
        if(lineData.getSection() <= 0) throw new IllegalArgumentException("Line section should be greater than zero.");
    }

    @Override
    public String toString() {
        return name;
    }
}
