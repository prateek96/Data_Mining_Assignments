package foshu;


/*
 * This class represents an Element of a utility list as used by the FOSHU algorithm. 
 */

public class Element 
{
	/** tid  (transaction id)**/
	public final int tid;
	/** itemset utility */
	public final int iputils; 
	/** itemset utility */
	public final int inutils; 
	/** remaining utility */
	public final int rutils; 
	
	/**
	 * Constructor.
	 * @param tid  the transaction id
	 * @param iutils  the itemset utility
	 * @param rutils  the remaining utility
	 */
	public Element(int tid, int iputils, int inutils, int rutils)
	{
		this.tid = tid;
		this.iputils = iputils;
		this.inutils = inutils;
		this.rutils = rutils;
	}

}
