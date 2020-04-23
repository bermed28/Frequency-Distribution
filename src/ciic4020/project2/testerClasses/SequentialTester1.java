package ciic4020.project2.testerClasses;

import java.util.ArrayList;
import java.util.Map;

import ciic4020.project2.strategiesClasses.SequentialFD;

public class SequentialTester1 {

	public static void main(String[] args) {
		
		ArrayList<Integer> data = TestingUtils.generateListOfIntegers(50); 
		
		TestingUtils.displayListElements("Original Data", data);
		
		SequentialFD<Integer> s = new SequentialFD<Integer>(); 
		ArrayList<Map.Entry<Integer, Integer>> fd = s.computeFDList(data); 

		fd.sort(new EntryIntComparator());
		
		TestingUtils.displayListElements("Frequency Distribution", fd);
	}

}