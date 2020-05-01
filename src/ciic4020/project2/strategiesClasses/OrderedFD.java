package ciic4020.project2.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * This class implements the Ordered strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class OrderedFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public OrderedFD() {
		super("Ordered");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a SORTED COPY of dataSet
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		/*Create our list to store our entries*/
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		
		/*Next, we just sort the dataSet using the .sort() from ArrayList, given the fact that we are using an ORDERED strategy*/
		dataSet.sort(null);

		/**
		 * Now, we just do the exact same thing we did for the SortedLis Strategy, 
		 * only that we just pass the frequency entries to results directly instead of putting them in a SortedList first.
		 *
		 * Notice we don't have to put them inside a SortedList first because the list is already sorted,
		 * if we do this we would just be wasting space because we would be doing the exact same thing as putting the 
		 * entry inside results
		 *
		 **/
		for (E e : dataSet) { 
			
			/*While no entry hasn't been created we loop through our data set */
			boolean entryFound = false; 
			for (int i=0; i < results.size() && !entryFound; i++) {
				Map.Entry<E, Integer> entry = results.get(i); 
				/*If we find an entry created for e, we just increase its frequency by 1*/
				if(entry.getKey().compareTo(e) == 0) {

					entry.setValue(entry.getValue()+1);
					entryFound = true;

				/*If we find an entry greater than e, we stop searching because it means the entry isn't created in the list*/
				} else if(entry.getKey().compareTo(e) > 0) break;


			}
			/*If we break out of the loop it means there is no entry for e, so we must create one*/
			if (!entryFound) { 
				//need to create a new entry for the first instance found of object e
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(e, 1); 
				results.add(entry); 
			}	
		}


		return results;
	}

}
