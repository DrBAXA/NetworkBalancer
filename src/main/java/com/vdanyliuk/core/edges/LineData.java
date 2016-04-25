package com.vdanyliuk.core.edges;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represent electric line parameters
 */
@AllArgsConstructor
@Getter
public class LineData {
    /**
     * Long of line in kilometers
     */
    private final Long lineLong;

    /**
     * section of wire in square millimeters
     */
    private final Long section;

}
