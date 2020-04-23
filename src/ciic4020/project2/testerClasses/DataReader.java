package ciic4020.project2.testerClasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface DataReader<E> {
	ArrayList<E> readDataFromFile() throws FileNotFoundException; 
}
