package com.shubh4m.datamining.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import com.shubh4m.datamining.uspan.USpan;

/**
 * This file is for testing the USpan algorithm
 * @see Sequence
 * @see SequenceDatabase
 * @author Philippe Fournier-Viger, 2015
 */
public class MainTestUSpan {

	public static void main(String [] arg) throws IOException{
		 // the input database
		String input = fileToPath("DataBase_USPAN.txt");
		// the path for saving the patterns found
		String output = ".//output.txt";

		// the minimum utility threshold
		int minutil = 35;

		USpan algo = new USpan();

		// set the maximum pattern length (optional)
		algo.setMaxPatternLength(4);

		// run the algorithm
		algo.runAlgorithm(input, output, minutil);


		// print statistics
		algo.printStatistics();
	}

	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestUSpan.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
