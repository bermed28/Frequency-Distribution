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
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		dataSet.sort(null);

		for (E e : dataSet) { 
			boolean entryFound = false; 
			for (int i=0; i < results.size() && !entryFound; i++) {
				Map.Entry<E, Integer> entry = results.get(i); 

				if(entry.getKey().compareTo(e) == 0) {

					entry.setValue(entry.getValue()+1);
					entryFound = true;

				} else if(entry.getKey().compareTo(e) > 0) break;


			}
			if (!entryFound) { 
				//need to create a new entry for the first instance found of object e
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(e, 1); 
				results.add(entry); 
			}	
		}


		return results;
	}

}
