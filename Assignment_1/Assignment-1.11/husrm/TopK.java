package com.shubh4m.datamining.husrm;

import com.shubh4m.datamining.uspan.USpan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TopK extends HUSRM {
    public void runAlgorithm(String input,
                             String output,
                             double minConfidence,
                             double minutil,
                             int maxAntecedentSize,
                             int maxConsequentSize,
                             int maximumNumberOfSequences) throws IOException {
        super.runAlgorithm(input,
                output,
                minConfidence,
                minutil,
                maxAntecedentSize,
                maxConsequentSize,
                maximumNumberOfSequences);
        // close output file
        writer.close();
    }
}
