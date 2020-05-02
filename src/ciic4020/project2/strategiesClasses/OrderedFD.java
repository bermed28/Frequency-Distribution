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

			/** 
			 * Like we did on SortedListFD, we search for e but this time directly in results instead of an auxiliary list. 
			 * If we find a bigger one or reach the end of the list, we stop
			 */
			int i;
			for (i=0; i < results.size() && results.get(i).getKey().compareTo(e) < 0; i++);
			
			/*If we do find an elm equal to e, we add one to the frequency*/
			if(i < results.size() && results.get(i).getKey().compareTo(e) == 0) 
				results.get(i).setValue(results.get(i).getValue()+1);

			else
				/*Else, we just create a new entry for e with a frequency of 1 because it's the first time we count this elm*/
				results.add(new AbstractMap.SimpleEntry<E, Integer>(e, 1)); 
		}


		return results;
	}

}
