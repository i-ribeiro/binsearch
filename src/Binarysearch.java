import java.util.stream.IntStream;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Recursive and non-recursive binary search algorithms.
 * Helper method to generate sorted random integer arrays included. 
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Binarysearch {
	
/**** Constructors ****/

	/**
	 * Default constructor.
	 */
	public Binarysearch() {
		// TODO: Default constructor
		
	}
	
	
/**** Methods ****/
	
	/**
	 * Non-recursive binary search.
	 * Prints the search result to the screen.
	 * @param array - the array to search within 
	 * @param searchVal - the value to search for
	 * @return the index of the searchVal or -1.
	 */
	public void nonRecursiveBinarySearch(int array[], int searchVal) {
		// TODO: Non-recursive binary search
		
	}
	
	/**
	 * Recursive binary search.
	 * Prints the search result to the screen.
	 * @param array - the array to search within 
	 * @param searchVal - the value to search for
	 * @param firstIndex - the first index of the remaining values (inclusive)
	 * @param lastIndex - the last index of the remaining values (inclusive)
	 */
	public void recursiveBinarySearch(int array[], int searchVal, int firstIndex, int lastIndex) {
		// TODO: Recursive binary search
		
	}
	
	/**
	 * Uses SecureRandom to generate a sorted array of random integers.
	 * Prints the generated array to the screen.
	 * @param size - the size of the array
	 * @param lower - the lower bound of generated values (exclusive)
	 * @param upper - the upper bound of generated values (exclusive)
	 * @return the generated array.
	 */	// TODO: fix param name / comment mismatch for bounds params
	public int[] generateRandomInts(int size, int min, int max) {
		// TODO: Generate sorted random ints
		
		// early out if size is invalid ( <1 )
		if (size < 1)
		{
			System.out.println("ERROR: size is less than 1.");
			return null;
		}
		
		// generate random values
		IntStream randInt = new SecureRandom().ints(size, min + 1, max);
		int[] values = randInt.sorted().toArray();
		
		// print values
		System.out.println(Arrays.toString(values));	// TODO: add another newline
		
		// return values
		return values;
	}
	
	/**
	 * Displays elements remaining each time half of the array is dropped. 
	 * @param array - the array to be printed
	 * @param firstIndex - the first index of the remaining values (inclusive)
	 * @param lastIndex - the last index of the remaining values (inclusive)
	 */
	private void remainingElements(int array[], int firstIndex, int lastIndex) {
		// TODO: print remaining elements
		
	}
}
