package com.shubh4m.datamining.husrm;

import com.shubh4m.datamining.uspan.USpan;

import java.io.IOException;

public class LengthConstraints extends HUSRM {
    private int minLength = 0;
    private int maxLength = Integer.MAX_VALUE;

    public LengthConstraints(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    protected void saveRule(int[] antecedent, int[] consequent,
                          double utility, double support, double confidence) throws IOException {
        if (antecedent.length >= minLength && antecedent.length <= maxLength) {
            super.saveRule(antecedent, consequent, utility, support, confidence);
        } else {
            ruleCount++;
        }
    }
}
