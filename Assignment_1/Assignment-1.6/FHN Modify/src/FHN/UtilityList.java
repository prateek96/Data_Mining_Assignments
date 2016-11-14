package FHN;

import java.util.ArrayList;
import java.util.List;

public class UtilityList 
{
	Integer item;  // the item
	long sumIutils = 0;  // the sum of positive item utilities
	long sumRutils = 0;  // the sum of remaining utilities
	long  sumINutils = 0;	// the sum of negative item utilities

	// the list of elements in this utility list
	public List<Element> elements = new ArrayList<Element>();

	public UtilityList(Integer item)
	{
		this.item = item;
	}

	/*
	 * Method to add an element to this utility list and update the sums at the same time.
	 */
	public void addElement(Element element)
	{
		sumIutils += element.iutils;
		sumRutils += element.rutils;
		sumINutils += element.inutils;
		elements.add(element);
	}
	
}
