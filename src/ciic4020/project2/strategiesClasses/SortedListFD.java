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
			/**
			 * We search for our element in our SortedList
			 * Notice that we stop when we reach the end of the list or we find an element that is bigger than e
			 * This is because the list is SORTED, hence the use of a SortedList
			 * By haven the list ordered we can just stop searching when we find an elm bigger than e, because it means e isn't on the list
			 */
			int i;
			for (i = 0; i < sortedlist.size() && sortedlist.get(i).getKey().compareTo(e) < 0; i++);
			
			/*When we find an element that is equal to e, it means it's another frequency of e, so we add 1 to said frequency*/
			if(i < sortedlist.size() && sortedlist.get(i).getKey().compareTo(e) == 0) 
				sortedlist.get(i).setValue(sortedlist.get(i).getValue() + 1);
			
			else 
				/**
				 * If we never find one elm equal to e, it means we haven't created an entry for e, so we create one with value 1
				 * This means that it is the first time we see e in our counting process
				 */
				sortedlist.add(new ComparableEntry<E, Integer>(e, 1)); 
		
		}

		/*Finally, we just go through our entries one y one and add them to our results ArrayList and return it*/
		for (int i = 0; i < sortedlist.size(); i++) {
			results.add(new AbstractMap.SimpleEntry<>(sortedlist.get(i).getKey(), sortedlist.get(i).getValue()));
		}
		return results;
	}

}