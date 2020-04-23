package ciic4020.project2.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import ciic4020.project2.sortedlist.SortedList;
import ciic4020.project2.sortedlist.SortedArrayList;

/**
 * This class implements the SortedList strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SortedListFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	/**
	 * Our SortedList classes require the data type to be Comparable.
	 * However, Map.Entry and AbstractMap.SimpleEntry are not Comparable,
	 * so we extend AbstractMap.SimpleEntry and create a Comparable
	 * version that we can use with our SortedList.
	 * Note: The K (key) of this class will be the E of SortedListFD,
	 *       so it will be Comparable.
	 * @author Juan O. Lopez
	 *
	 * @param <K>  The type of the key of each entry
	 * @param <V>  The type of the value of each entry
	 */
	@SuppressWarnings("serial")
	private static class ComparableEntry<K extends Comparable<K>, V> extends AbstractMap.SimpleEntry<K, V>
																	 implements Comparable<Map.Entry<K, V>> {

		public ComparableEntry(K key, V value) {
			super(key, value);
		}

		@Override
		public int compareTo(Map.Entry<K, V> entry) {
			/* Entries will be compared based on their keys, which are Comparable */
			return getKey().compareTo(entry.getKey());
		}

	} // End of ComparableEntry class

	/* Constructor */
	public SortedListFD() {
		super("SortedList");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a SortedList
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		/*We create our structures to sort and count our elements*/
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		SortedList<ComparableEntry<E, Integer>> sortedlist = new SortedArrayList<ComparableEntry<E, Integer>>(1000);

		/*Next, we iterate though our dataSet to see if we have any entries of e created in out SortedList*/
		for (E e : dataSet) { 
			boolean entryFound = false; 
			for (int i=0; i < sortedlist.size() && !entryFound; i++) {
				ComparableEntry<E, Integer> entry = sortedlist.get(i); 
			
				/**
				 * If we created an entry for our elm (AKA the elms are equal), 
				 * we just add one to our frequency and continue searching
				 */
				if(entry.getKey().compareTo(e) == 0) {
					
					entry.setValue(entry.getValue()+1);
					entryFound = true;
					
				/**
				 * If not, we just break to create a new entry for e.
				 * Notice that we just have to find an elm that is bigger than e.
				 * This is because since the list is sorted, we just need to find an elm that 
				 * is bigger than e to conclude that there is no entry created for e.
				 */
				} else if(entry.getKey().compareTo(e) > 0) break;
				
				
			}
			
			/*If we haven't created an entry for our elm, we just create a new one for the first instance of e*/
			if (!entryFound) { 
				ComparableEntry<E, Integer> entry = new ComparableEntry<E, Integer>(e, 1); 
				sortedlist.add(entry); 
			}	
		}

		/*Finally, we just go through our entries one y one and add them to our results ArrayList and return it*/
		for (int i = 0; i < sortedlist.size(); i++) {
			results.add(new AbstractMap.SimpleEntry<>(sortedlist.get(i).getKey(), sortedlist.get(i).getValue()));
		}
		return results;
	}

}