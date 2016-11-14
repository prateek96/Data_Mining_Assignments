package foshu;

import java.io.*;

public class Run
{
	public static void main(String args[])throws IOException
	{
		String input = "input";
		String output = "output";
		Double min_utility_ratio = 0.8d;
		
		Algo obj = new Algo();
		obj.runAlgorithm(input, output, min_utility_ratio);
		
	}
}
