package com.vdanyliuk.core.edges;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Represent electric line parameters
 */
@Getter
@EqualsAndHashCode
public class LineData {
    /**
     * Long of line in kilometers
     */
    private final long lineLength;

    /**
     * section of wire in square millimeters
     */
    private final long section;

    public LineData(long lineLength, long section) {
        if(lineLength < 0 || section < 0)
            throw new IllegalArgumentException("Line Length and section can't be negative.");
        this.lineLength = lineLength;
        this.section = section;
    }
}
