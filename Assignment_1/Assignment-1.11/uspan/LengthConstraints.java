package com.shubh4m.datamining.uspan;

import java.io.IOException;

public class LengthConstraints extends USpan {
    private int minLength = 0;
    private int maxLength = Integer.MAX_VALUE;

    public LengthConstraints(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    protected void writeOut(int[] prefix, int prefixLength,  int utility) throws IOException {
        if (prefixLength >= minLength && prefixLength <= maxLength) {
            super.writeOut(prefix, prefixLength, utility);
        } else {
            patternCount++;
        }
    }
}
