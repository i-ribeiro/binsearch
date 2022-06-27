import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Demonstrates and tests the Binarysearch class interactively. 
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Lab4binsearchTest {
	
/* Test Constants */
	
	/**
	 * The array size to test.
	 */
	private static final int TEST_SIZE = 25;
	
	/**
	 * The lower bound of the range of random values to test.
	 */
	private static final int TEST_BOUND_LOWER = 120;
	
	/**
	 * The upper bound of the range of random values to test.
	 */
	private static final int TEST_BOUND_UPPER = 1000;
	

/* Menu Constants */
	
	/**
	 * Menu option value to generate the integer array.
	 */
	private static final int MENU_GENERATE = 1;
	
	/**
	 * Menu option value to search the integer array using the recursive binary search algorithm.
	 */
	private static final int MENU_RSEARCH = 2;
	
	/**
	 * Menu option value to search the integer array using the non-recursive binary search algorithm.
	 */
	private static final int MENU_NRSEARCH = 3;
	
	/**
	 * Menu option value to sort the integer array.
	 */
	private static final int MENU_SORT = 4;
	
	/**
	 * Menu option value to sort the integer array using the Bubble Sort algorithm. 
	 */
	private static final String MENU_SORT_BUBBLE = "B";
	
	/**
	 * Menu option value to sort the integer array using the Insertion Sort algorithm.
	 */
	private static final String MENU_SORT_INSERT = "I";
	
	/**
	 * Menu option value to sort the integer array using the Selection Sort Algorithm.
	 */
	private static final String MENU_SORT_SELECT = "S";
	
	/**
	 * Menu option value to sort the integer array using the Merge Sort algorithm.
	 */
	private static final String MENU_SORT_MERGE = "M";
	
	/**
	 * Menu option value to sort the integer array using the Quick Sort algorithm.
	 */
	private static final String MENU_SORT_QUICK = "Q";
	
	/**
	 * Menu option value to return to the main menu.
	 */
	private static final String MENU_SORT_RETURN = "R";
	
	/**
	 * Menu option value to exit.
	 */
	private static final int MENU_EXIT = 5;
	

/* Methods */

	/** Entry point to test the Binarysearch class. 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);		// user input stream from keyboard
		
		int[] numbers = null,
				numbersSorted = null;
		
		/* Main menu loop */
		
		boolean exitFlag = false;	// true when user selects Exit
		
		do {
			// print menu options
			printMenuOptions();
			
			// get input
			int optionInput = inputInt(keyboard, false);	// -1 on input mismatch
			
			
			int searchVal = 0, index = 0;
			
			// time in
			long timeInNano = System.nanoTime(),
					timeInMillis = System.currentTimeMillis();
			
			switch (optionInput) {
			
			// generate random array
			case MENU_GENERATE:
				System.out.println();
				numbers = SearchAndSort.generateRandomInts(TEST_SIZE, TEST_BOUND_LOWER, TEST_BOUND_UPPER, false);
				numbersSorted = numbers.clone();
				SearchAndSort.quickSort(numbersSorted, 0, numbersSorted.length-1);
				break;
				
			// recursive binary search
			case MENU_RSEARCH:
				if (numbers == null) {
					
					System.out.println("Generate values first.");
					break;
				}
				
				// input search value
				System.out.print("Please enter an integer value to search: ");
				searchVal = inputInt(keyboard, true);
				
				// search
				index = SearchAndSort.recursiveBinarySearch(numbersSorted, searchVal, 0, numbers.length - 1);
				
				System.out.println("\nRecursive binary search: ");
				break;
				
			// non-recursive binary search
			case MENU_NRSEARCH:
				if (numbers == null) {
					
					System.out.println("Generate values first.");
					break;
				}
				
				// input search value
				System.out.print("Please enter an integer value to search: ");
				searchVal = inputInt(keyboard, true);
				
				// search
				index = SearchAndSort.nonRecursiveBinarySearch(numbersSorted, searchVal);
				
				System.out.println("\nNon-recursive binary search: ");
				break;
				
			// sort array
			case MENU_SORT:
				
				if (numbers != null)
					sortMenu(keyboard, numbers);
				
				// warn and abort sort if values haven't been generated
				else System.out.println("Generate values first.");
				
				break;
				
			// exit
			case MENU_EXIT:
				exitFlag = true;
				break;
				
			default:
				System.out.println("Invalid input...try again");
				break;
			}
			
			
			// if a search was done, print the search report 
			
			if (optionInput == MENU_RSEARCH
				|| optionInput == MENU_NRSEARCH) {
				
				// if index is valid, the value was found
				if (index >= 0)
					System.out.printf("Number %d was found at index %d. \n", searchVal, index);
				
				else System.out.printf("Number %d was not found. \n", searchVal);
				
				// print time
				System.out.printf(
						"Time taken in nanoseconds: %d \n"
						+ "Time taken in milliseconds: %d \n\n",
						System.nanoTime()- timeInNano, 
						System.currentTimeMillis() - timeInMillis);
			}
			
		} while (exitFlag == false);	// stop loop if user selects Exit
		
		
		keyboard.close();
		
		// print exit greeting
		System.out.println("Exiting...Goodbye");
	}
	
	/**
	 * Helper method to print the main menu options .
	 */
	private static void printMenuOptions() {
		
		System.out.printf(
				"Select your option in the menu below: \n"
				+ "%d. Initialize and populate an array of %d random integers. \n"
				+ "%d. Perform a recursive binary search. \n"
				+ "%d. Perform a non-recursive binary search. \n"
				+ "%d. Sort the array \n"
				+ "%d. Exit. \n"
				+ " >",
				MENU_GENERATE,
				TEST_SIZE,
				MENU_RSEARCH,
				MENU_NRSEARCH,
				MENU_SORT,
				MENU_EXIT);
	}
	
	/**
	 * Sub-menu to sort the array using several algorithms:
	 * - Bubble sort
	 * - Insertion sort
	 * - Selection sort
	 * - Merge sort
	 * - Quick sort
	 * Does not modify the source array.
	 * @param input - user input stream
	 * @param array - values to sort
	 */
	private static void sortMenu(Scanner input, int[] array) {
		
		String optionInput = ""; // user option selection
		
		// continue prompting until user selects return option
		while (!optionInput.equals(MENU_SORT_RETURN)) {
			
			/* print sub-menu options */
			System.out.printf(
					"Select a sorting algorithm to sort the data array \n\n"
					+ "\t%s. Bubble Sort \n"
					+ "\t%s. Insertion Sort \n"
					+ "\t%s. Selection Sort \n"
					+ "\t%s. Merge Sort \n"
					+ "\t%s. Quick Sort \n"
					+ "\t%s. Return to Main Menu \n\n"
					+ ">",
					MENU_SORT_BUBBLE,
					MENU_SORT_INSERT,
					MENU_SORT_SELECT,
					MENU_SORT_MERGE,
					MENU_SORT_QUICK,
					MENU_SORT_RETURN);
			
			
			/* get option selection */
			
			optionInput = input.next();
			
			
			/* switch on option selection */
			
			int[] sorted = null;			// throwaway clones of array parameter
			if (sorted == null) sorted = array.clone();	// create a new clone if array has been sorted and dereferenced
			
			boolean isSorted = false;			// whether the array has been sorted
			
			long timeNano = 0, timeMillis = 0;	// start time for sort duration
			
			// print initial values and set start time
			switch (optionInput) {	// if any of the sort options are selected,
			case MENU_SORT_BUBBLE:
			case MENU_SORT_INSERT:
			case MENU_SORT_SELECT:
			case MENU_SORT_MERGE:
			case MENU_SORT_QUICK:
				
				isSorted = true;					// the array will be sorted
				System.out.print("\n"
						+ Arrays.toString(sorted)
						+ "\n\n");					// print the unsorted values
				
				timeNano = System.nanoTime();
				timeMillis = System.currentTimeMillis();
				
				break;
			}
			
			
			switch (optionInput) {
			
			// Exit sub-menu
			case MENU_SORT_RETURN:
				
				isSorted = false;
				break;	// handled by outer loop
			
			// Bubble sort 
			case MENU_SORT_BUBBLE:
				
				System.out.print("Bubble sort: Simple sorting algorithm - O(n2) Complexity - in-place \n\n");
				SearchAndSort.bubbleSort(sorted);
				break;
				
			// Insertion sort
			case MENU_SORT_INSERT:
				
				System.out.print("Insertion sort: Simple sorting algorithm - O(n2) Complexity - in-place \n\n");
				SearchAndSort.insertionSort(sorted);
				break;
				
			// Selection sort
			case MENU_SORT_SELECT:
				
				System.out.print("Selection sort: Simple sorting algorithm - O(n2) Complexity - in-place \n\n");
				SearchAndSort.selectionSort(sorted);
				break;
				
			// Merge sort
			case MENU_SORT_MERGE:
				
				System.out.print("Merge sort: Recursive Divide-and-conquer - O(n log n) Complexity - not in-place \n\n");
				SearchAndSort.mergeSort(sorted, 0, sorted.length-1);
				break;
			
			// Quick sort
			case MENU_SORT_QUICK:
				
				System.out.print("Quick sort: Recursive Divide-and-conquer - O(n log n) Complexity - in-place \n\n");
				SearchAndSort.quickSort(sorted, 0, sorted.length-1);
				break;
			
			// Invalid option
			default:
				isSorted = false;
				System.out.print("Invalid option. Try again. \n\n");
				break;
			}
			
			
			/* print sorted array and time elapsed */
			
			if (isSorted) {		// (if a sort occurred) 
				
				System.out.print(Arrays.toString(sorted) + "\n\n");
				
				System.out.printf(
						"Time taken in nanoseconds: %d \n"
						+ "Time taken in milliseconds: %d \n\n",
						System.nanoTime() - timeNano,
						System.currentTimeMillis() - timeMillis);
				
				sorted = null;	// dereference sorted array to create a new clone
			}
		} 
	}
	
	/**
	 * Helper method to input an integer.
	 * Includes functionality to loop until a valid value is entered.
	 * @param input - user input stream
	 * @param loop - whether to loop until a valid value is entered
	 * @return a valid integer or -1 if loop is false
	 */
	private static int inputInt(Scanner input, boolean loop) {
		
		int n = -1;
		boolean inputValid = false;	// input invalid by default
		
		do {
			
			try {
				
				n = input.nextInt();
				inputValid = true;		// only called if nextInt() succeeds
				
			} catch (InputMismatchException e) {
				
				if (loop) System.out.print("Not a valid value. Try again > ");
				input.nextLine();			// flush buffer due to scanner bug 
			}
			
		} while (inputValid == false && loop == true);
		
		return n;
	}
}
