package ciic4020.project2.strategiesClasses;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class implements the Map/Hash table strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class MapFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public MapFD() {
		super("Map");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a Hash table
	 * It uses a Hash table to count the frequency of each elements inside dataSet instead of Map.Entry
	 * like the previous strategies in this experiment
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		/* Create our structures to count and store information */
		ArrayList<Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		Map<E, Integer> hashtable = new Hashtable<E, Integer>();	
		
		/* We now iterate through our dataSet to see if there is a frequency already in our Hash table */
		
		for (E e : dataSet) {
			/* If there's a frequency created in our hash table, just add one to that count*/
			if (hashtable.containsKey(e)) {
				hashtable.put(e, hashtable.get(e) + 1);
				
			/* if not just create a new one with value 1 meaning it's the first time this element is seen*/
			} else {
				hashtable.put(e, 1);
			}
		}
		
		/*Finally, we iterate through a temporary set that contains our entries and we save them in our results ArrayList so we can return them*/
		for (Entry<E, Integer> entry : hashtable.entrySet()) {
			results.add(entry);
		}
		
		
		return results;
	}

}
