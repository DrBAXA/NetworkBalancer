package com.vdanyliuk.core.vertices;


import lombok.Getter;
import lombok.Setter;

public class Counter implements Vertex {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private boolean reverse;

    @Setter
    private long Aplus;

    @Setter
    private long Aminus;

    public Counter(String number) {
        this(number, number);
    }

    public Counter(String name, String number) {
        this(name, number, false);
    }

    public Counter(String name, String number, boolean reverse) {
        this.name = name;
        this.number = number;
        this.reverse = reverse;
    }

    @Override
    public boolean isComputed() {
        return false;
    }

    @Override
    public long getIncoming() {
        return reverse ? Aplus : Aminus;
    }

    @Override
    public long getOutgoing() {
        return reverse ? Aminus : Aplus;
    }

    @Override
    public String toString() {
        return name.equals(number) ? name : name + " " + number;
    }
}
