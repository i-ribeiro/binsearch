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
	
/**** Methods ****/
	
	/**
	 * Non-recursive binary search.
	 * Prints the search result to the screen.
	 * @param array - the array to search within 
	 * @param searchVal - the value to search for
	 * @return the index of the searchVal or -1.
	 */
	public static int nonRecursiveBinarySearch(int array[], int searchVal) {
		
		int firstIndex = 0;
		int lastIndex = array.length - 1;
		int middle;
		
		int index = -1;
		
		remainingElements(array, firstIndex, lastIndex);
		
		// early out if value is out of bounds 
		if (
			array[firstIndex] > searchVal
			|| array[lastIndex] < searchVal)
		{
			System.out.printf("Number %d was not found. \n", searchVal);
			return index;
		}
		
		// loop until index is valid or all values searched
		while (index == -1 && (firstIndex != lastIndex)) {
			
			// check if value is at firstIndex
			if (array[firstIndex] == searchVal)
				index = lastIndex = firstIndex;
			
			// check if value is at lastIndex
			else if (array[lastIndex] == searchVal)
				index = firstIndex = lastIndex;
			
			// otherwise, continue pruning 
			else {
				middle = (lastIndex - firstIndex) / 2 + firstIndex;
				
				if (middle == firstIndex || middle == lastIndex) break;		// check if there are only two elements remaining
				else if (searchVal > array[middle]) firstIndex = middle;	// if searchVal is larger than middle value, prune lower half
				else if (searchVal < array[middle]) lastIndex = middle;		// if searchVal is smaller than middle value, prune upper half
				else index = firstIndex = lastIndex = middle;				// searchVal is at the middle index
			}
			
			remainingElements(array, firstIndex, lastIndex);
		}
		
		return index;
	}
	
	/**
	 * Recursive binary search.
	 * Prints the search result to the screen.
	 * @param array - the array to search within 
	 * @param searchVal - the value to search for
	 * @param firstIndex - the first index of the remaining values (inclusive)
	 * @param lastIndex - the last index of the remaining values (inclusive)
	 * @return the index of the searchVal or -1.
	 */
	public static int recursiveBinarySearch(int array[], int searchVal, int firstIndex, int lastIndex) {

		int middle;
		int index = -1;
		
		remainingElements(array, firstIndex, lastIndex);
		
		// early out if value is out of bounds 
		if (
			array[firstIndex] > searchVal
			|| array[lastIndex] < searchVal)
		{
			return index;
		}
		
		// check if value is at firstIndex
		if (array[firstIndex] == searchVal) {
			
			index = firstIndex;
			remainingElements(array, index, index);
		}
		
		// check if value is at lastIndex
		else if (array[lastIndex] == searchVal) {
			
			index = firstIndex;
			remainingElements(array, index, index);
		}
		
		// otherwise, continue pruning 
		else {
			
			middle = (lastIndex - firstIndex) / 2 + firstIndex;
			
			if (middle == firstIndex || middle == lastIndex)	// check if there are only two elements remaining
				return index;
			
			else if (searchVal > array[middle]) {				// if searchVal is larger than middle value, prune lower half and recurse
				
				firstIndex = middle;
				index = recursiveBinarySearch(array, searchVal, firstIndex, lastIndex);
			}
			else if (searchVal < array[middle]) {				// if searchVal is smaller than middle value, prune upper half and recurse
				
				lastIndex = middle;
				index = recursiveBinarySearch(array, searchVal, firstIndex, lastIndex);
			}
			else {												// searchVal is at the middle index
				
				remainingElements(array, middle, middle);
				index = firstIndex = lastIndex = middle;
			}
		}
		
		return index;
	}
	
	/**
	 * Uses SecureRandom to generate a sorted array of random integers.
	 * Prints the generated array to the screen.
	 * @param size - the size of the array
	 * @param lowerBound - the lower bound of generated values (exclusive)
	 * @param upperBound - the upper bound of generated values (exclusive)
	 * @return the generated array.
	 */
	public static int[] generateRandomInts(int size, int lowerBound, int upperBound) {
		
		// early out if size is invalid ( <1 )
		if (size < 1)
		{
			System.out.println("ERROR: size is less than 1.");
			return null;
		}
		
		// generate random values
		IntStream randInt = new SecureRandom().ints(size, lowerBound + 1, upperBound);
		int[] values = randInt.sorted().toArray();
		
		// print values
		System.out.print(Arrays.toString(values) + "\n\n");
		
		
		randInt.close();
		
		// return values
		return values;
	}
	
	/**
	 * Displays elements remaining each time half of the array is dropped. 
	 * @param array - the array to be printed
	 * @param firstIndex - the first index of the remaining values (inclusive)
	 * @param lastIndex - the last index of the remaining values (inclusive)
	 */
	public static void remainingElements(int array[], int firstIndex, int lastIndex) {
		
		/* calculate spacing.
		 * spacing between elements is the printed size of the largest number, plus a space.
		 */
		final int spacing = Integer.toString(array[array.length-1]).length() + 1;
		
		
		/* calculate the offset*/
		final int fullWidth = array.length * spacing;							// the printed size of the full array
		final int remainingWidth = (1 + lastIndex - firstIndex) * spacing;		// the printed size of the remaining array
		final int offset = (fullWidth - remainingWidth) / 2;					// the left-offset to center the remaining array below the full array
		
		
		/* print offset if not negative or zero */
		
		if (offset > 0)
		{
			String offsetFormat = String.format("%ds", offset);				// #s		-- eg. 20s 
			System.out.printf('%'+offsetFormat, "");						// % + #s 	-- eg. %20s
		}
		
		
		/* print elements */
		
		String format = String.format("%dd", -spacing);						// #d		-- eg. -4d
		
		for (int i = firstIndex; i <= lastIndex; ++i)						// % + #d	-- eg. %-4d
			System.out.printf('%'+format, array[i]);
		
		System.out.print("\n\n");
	}
}
