package ciic4020.project2.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class corresponds to the Sequential strategy to count frequencies in an
 * array list.
 * @author pedroirivera-vega
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SequentialFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public SequentialFD() {
		super("Sequential");
	}
	/**
	 * Method that counts the frequency of a dataSet with a regular ArrayList
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * one by one without any sorting or re-arrangement of the elements
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		
		/*We go though each element of dataSet*/
		for (E e : dataSet) {
			/*For each elm in dataSet, we go through each element in our entries stored*/
			boolean entryFound = false; 
			for (int i=0; i<results.size() && !entryFound; i++) {
				/*If we have found an entry stored in results we just add one to that frequency of that element*/
				Map.Entry<E, Integer> entry = results.get(i); 
		
				if (entry.getKey().equals(e)) { 
					entry.setValue(entry.getValue()+1);
					entryFound = true; 
				}
			}
			
			/**
			 * If we haven't found an entry after searching results entirely, 
			 * we create a new entry for this first instance of e and store it in results with a frequency of 1
			 * This is made so we can continue counting later on without any problem
			 */
			if (!entryFound) { 
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(e, 1); 
				results.add(entry); 
			}
		}
		
		return results;
	}

}
