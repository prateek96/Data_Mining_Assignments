package foshu;

import java.util.ArrayList;
import java.util.List;

/*
 * This class represents a UtilityList as used by the FOSHU algorithm.
 */

public class UtilityList 
{

	// the last item of the itemset represented by this utility list
	public Integer item;
	// the sum of iutil values for positive items
	public int sumIutilP = 0;
	// the sum of iutil values for negative items
	public int sumIutilN = 0;
	// A list where each entry correspond to a period.
	// An entry contains the elements corresponding to that period
	private List<Element>[] periodsElements;
	// A list where each entry correspond to a period.
	// An entry contains the sum of iutil and rutil valuess corresponding to that period
	private int periodsSumIutilRutil[];

	/**
	 * Constructor
	 * @param item the last item of the itemset represented by this utility list
	 * @param the number of periods
	 */
	public UtilityList(Integer item, int periodCount) 
	{
		super();
		this.item = item;
		periodsElements = new ArrayList[periodCount];
		periodsSumIutilRutil = new int[periodCount];
	}

	/**
	 * Construct a utility list for an itemset pU{X,Y}
	 * @param periodCount the number of periods
	 * @param pUL the utility-list of p
	 * @param x the utility-list of pU{X}.
.	 * @param y the utility-list of pU{Y}.
	 * @return the utility-list of pU{X,Y}.
	 */
	public UtilityList(int periodCount, UtilityList pUL, UtilityList x, UtilityList y) 
	{
		// Get the item Y
		this.item = y.item;
		// Initialize an array to store the list of elements for each period
		periodsElements = new ArrayList[periodCount];
		// Initialize an array to store the sum of iutil and rutil values for each period
		periodsSumIutilRutil = new int[periodCount];
		// if prefix p is the empty set
		if(pUL == null) 
		{
			// for each period
			for(int i = 0; i < periodCount; i++) 
			{
				// if both X and Y appears in the same period
				if(x.periodsElements[i] != null && y.periodsElements[i] != null) 
				{
					// we construct the elements for that periods for p U {X,Y}
					construct(i, x.periodsElements[i], y.periodsElements[i]);
				}
			}
		}
		else 
		{
			// otherwise if prefix p is not the empty set
			// for each period
			for(int i = 0; i < periodCount; i++) 
			{
				// if both X and Y appears in the same period
				if(x.periodsElements[i] != null && y.periodsElements[i] != null) 
				{
					// we construct the elements for that periods for p U {X,Y}
					construct(i, pUL.periodsElements[i], x.periodsElements[i], y.periodsElements[i]);
				}
			}
		}
	}

	/**
	 * Method to add an element to this utility list and update the sums at the same time.
	 * @param element an element
	 * @param period the time period to which this element should be added.
	 */
	public void addElement(Element element, int period)
	{
		// if this period does not exist yet
		if(periodsElements[period] == null) 
		{
			// create it
			periodsElements[period] = new ArrayList<Element>();
		} 
		// add the element to that period
		periodsElements[period].add(element);
		// make the sum of the utility of positive items
		sumIutilP += element.iputils;
		// make the sum of the utility of negative items
		sumIutilN += element.inutils;
		periodsSumIutilRutil[period]+= element.iputils + element.rutils;
	}

	/**
	 * This method constructs the utility list of pXY
	 * @param pElements :  the utility list of prefix P.
	 * @param x : the utility list of pX
	 * @param periodsElements4 : the utility list of pY
	 * @return the utility list of pXY
	 */
	private void construct(int period, List<Element> pElements, List<Element> pXElements, List<Element> pYElements) 
	{
		// create an empy utility list for pXY
		periodsElements[period] = new ArrayList<Element>();
		
		// for each element in the utility list of pX
		for(Element ex : pXElements){
			// do a binary search to find element ey in py with tid = ex.tid
			Element ey = findElementWithTID(pYElements, ex.tid);
			if(ey == null)
			{
				continue;
			}
			
			// find the element in the utility list of p wih the same tid
			Element e = findElementWithTID(pElements, ex.tid);
			if(e != null)
			{
				// Create new element
				Element eXY = new Element(ex.tid, 
							ex.iputils + ey.iputils - e.iputils,
							ex.inutils + ey.inutils - e.inutils,
							ey.rutils);
				// add the new element to the utility list of pXY
				addElement(eXY, period);
			}
		}
	}
	
	/**
	 * This method constructs the utility list of {x,y}
	 * @param x : the utility list of item {x}
	 * @param y : the utility list of item {y}
	 * @return the utility list of {x,y}
	 */
	private void construct(int period, List<Element> pXElements, List<Element> pYElements) 
	{
		// create an empy utility list for pXY
		periodsElements[period] = new ArrayList<Element>();
		
		// for each element in the utility list of pX
		for(Element ex : pXElements)
		{
			// do a binary search to find element ey in py with tid = ex.tid
			Element ey = findElementWithTID(pYElements, ex.tid);
			if(ey == null)
			{
				continue;
			}
			// Create the new element
			Element eXY = new Element(ex.tid, 
					ex.iputils + ey.iputils, 
					ex.inutils + ey.inutils, 
					ey.rutils);
			// add the new element to the utility list of pXY
			addElement(eXY, period);
		}
	}
	
	/**
	 * Do a binary search to find the element with a given tid in a utility list
	 * @param pYElements the utility list
	 * @param tid  the tid
	 * @return  the element or null if none has the tid.
	 */
	private Element findElementWithTID(List<Element> list, int tid)
	{
		
		// perform a binary search to check if  the subset appears in  level k-1.
        int first = 0;
        int last = list.size() - 1;
       
        // the binary search
        while( first <= last )
        {
        	int middle = ( first + last ) >>> 1; // divide by 2

            if(list.get(middle).tid < tid)
            {
            	first = middle + 1;  //  the itemset compared is larger than the subset according to the lexical order
            }
            else if(list.get(middle).tid > tid)
            {
            	last = middle - 1; //  the itemset compared is smaller than the subset  is smaller according to the lexical order
            }
            else
            {
            	return list.get(middle);
            }
        }
		return null;
	}


	/**
	 * Get the sum of iutil and rutil for that itemset and a given time period
	 * @param period  the period number
	 * @return the sum of iutil and rutil values for that period and that itemset
	 */
	public double getSumIRUtilsInPeriod(int period) 
	{
		return periodsSumIutilRutil[period];
	}
	
	/**
	 * Check if this itemset appears in a given period
	 * @param period  the period number
	 * @return true if the item appears in that period
	 */
	public boolean appearsInPeriod(int period) 
	{
		return periodsElements[period] !=null && periodsElements[period].size() !=0;
	}


	public List<Element> getElementsOfPeriod(int period) 
	{
		return periodsElements[period];
	}

}
