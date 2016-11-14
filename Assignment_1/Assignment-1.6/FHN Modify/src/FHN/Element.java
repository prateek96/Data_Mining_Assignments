/*
 * This class represents an Element of a utility list as used by the FHN algorithm.
 */

package FHN;

public class Element 
{
	// The three variables as described in the paper:
	/* transaction id */
	final int tid ;   
	/* itemset utility */
	final int iutils;   
	/* remaining utility */
	int rutils; 
	public final int inutils;  	
	
	/*
	 * Constructor. 
	 * @param tid  the transaction id
	 * @param iutils  the itemset utility (for positive items)
	 * @param inutils  the itemset utility (for negative items)
	 * @param rutils  the remaining utility
	 */
	
	public Element(int tid, int iutils, int rutils, int inutils){
		this.tid = tid;
		this.iutils = iutils;
		this.rutils = rutils;
		this.inutils = inutils;
	}	
}
