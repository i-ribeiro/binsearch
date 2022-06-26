import java.util.Scanner;
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
	private static final int TEST_SIZE = 1000;
	
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
		
		int[] numbers = null;
		
		/* Main menu loop */
		
		boolean exitFlag = false;	// true when user selects Exit
		
		do {
			// print menu options
			printMenuOptions();
			
			// get input
			int optionInput = inputInt(keyboard, false);	// -1 on input mismatch
			
			
			int searchVal, index;
			long timeInNano, timeOutNano;
			long timeInMillis, timeOutMillis;
			
			switch (optionInput) {
			
			// generate random array
			case MENU_GENERATE:
				System.out.println();
				numbers = Binarysearch.generateRandomInts(TEST_SIZE, TEST_BOUND_LOWER, TEST_BOUND_UPPER);
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
				
				
				// time in
				timeInNano = System.nanoTime();
				timeInMillis = System.currentTimeMillis();
				
				// search
				index = Binarysearch.recursiveBinarySearch(numbers, searchVal, 0, numbers.length - 1);
				
				//time out
				timeOutNano = System.nanoTime();
				timeOutMillis = System.currentTimeMillis();
				
				
				// if index is valid, the value was found
				if (index >= 0)
					System.out.printf("Number %d was found at index %d. \n", searchVal, index);
				
				else System.out.printf("Number %d was not found. \n", searchVal);
				
				// print time
				System.out.printf("Time taken in nanoseconds: %d \n", timeOutNano - timeInNano);
				System.out.printf("Time taken in milliseconds: %d \n\n", timeOutMillis - timeInMillis);
				
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

				
				// time in
				timeInNano = System.nanoTime();
				timeInMillis = System.currentTimeMillis();
				
				// search
				index = Binarysearch.nonRecursiveBinarySearch(numbers, searchVal);

				//time out
				timeOutNano = System.nanoTime();
				timeOutMillis = System.currentTimeMillis();
				
				
				// if index is valid, the value was found
				if (index >= 0)
					System.out.printf("Number %d was found at index %d. \n", searchVal, index);
				
				else System.out.printf("Number %d was not found. \n", searchVal);
				
				// print time
				System.out.printf("Time taken in nanoseconds: %d \n", timeOutNano - timeInNano);
				System.out.printf("Time taken in milliseconds: %d \n\n", timeOutMillis - timeInMillis);
				
				break;
				
			// sort array
			case MENU_SORT:
				sortMenu(keyboard);
				break;
				
			// exit
			case MENU_EXIT:
				exitFlag = true;
				break;
				
			default:
				System.out.println("Invalid input...try again");
				break;
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
	 * @param input - user input stream
	 */
	private static void sortMenu(Scanner input) {
		
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
			
			optionInput = input.next();
			
			
			/* switch on option selection */
			
			switch (optionInput) {
			
			// Exit sub-menu
			case MENU_SORT_RETURN: break;	// handled by outer loop
			
			// TODO: Bubble sort 
			case MENU_SORT_BUBBLE:
				break;
				
			// TODO: Insertion sort
			case MENU_SORT_INSERT:
				break;
				
			// TODO: Selection sort
			case MENU_SORT_SELECT:
				break;
				
			// TODO: Merge sort
			case MENU_SORT_MERGE:
				break;
			
			// TODO: Quick sort
			case MENU_SORT_QUICK:
				break;
			
			// Invalid option
			default:
				System.out.print("Invalid option. Try again. \n\n");
				break;
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
