package com.shubh4m.datamining.uspan;

import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class TopK extends USpan {
    private int k = 0;

    public TopK(int k) {
        this.k = k;
        this.data = new ArrayList<WriteData>();
    }

    class WriteData implements Comparator<WriteData> {
        int[] prefix = null;
        int prefixLength = 0;
        public int utility = 0;

        WriteData() {}

        WriteData(int[] p, int pl, int util) {
            this.prefix = p;
            this.prefixLength = pl;
            this.utility = util;
        }

        public int compare(WriteData w1, WriteData w2) {
            if (w1.utility < w2.utility)
                return 1;
            else if (w1.utility > w2.utility)
                return -1;
            else
                return 0;
        }
    }

    private ArrayList<WriteData> data = null;

    protected void writeOut(int[] prefix, int prefixLength,  int utility) throws IOException {
        patternCount++;

        data.add(new WriteData(prefix, prefixLength, utility));
    }

    private void writeOutFinal() throws IOException {
        data.sort(new WriteData());

        int i = 0;
        for(WriteData d : data) {
            if (i < k) {
                super.writeOut(d.prefix, d.prefixLength, d.utility);
            } else
                break;
        }
    }


    public void runAlgorithm(String input, String output, int minUtility) throws IOException {
        super.runAlgorithm(input, output, minUtility);
        writeOutFinal();

        // close output file
        writer.close();
    }
}
