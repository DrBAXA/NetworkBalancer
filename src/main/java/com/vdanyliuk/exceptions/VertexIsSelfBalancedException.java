package com.vdanyliuk.exceptions;

public class VertexIsSelfBalancedException extends Exception {

    public VertexIsSelfBalancedException() {
        super("This vertex is self balanced.");
    }
}
