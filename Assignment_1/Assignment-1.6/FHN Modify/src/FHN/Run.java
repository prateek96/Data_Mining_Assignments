package FHN;

import java.io.*;

public class Run 
{
	public static void main(String args[])throws IOException
	{
		String input = "input";
		String output = "output";
		int minUtility = 50;
		
		Algo obj = new Algo();
		
		obj.runAlgorithm(input, output, minUtility);
	}
}
