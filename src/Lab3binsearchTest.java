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
public class Lab3binsearchTest {
	
	/**
	 * The array size to test.
	 */
	private static final int TEST_SIZE = 20;
	
	/**
	 * The lower bound of the range of random values to test.
	 */
	private static final int TEST_BOUND_LOWER = 10;
	
	/**
	 * The upper bound of the range of random values to test.
	 */
	private static final int TEST_BOUND_UPPER = 100;
	

	/** Entry point to test the Binarysearch class. 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);		// user input stream from keyboard
		
		Binarysearch binsearch = new Binarysearch();	// Binarysearch object to test
		int[] numbers = null;
		
		/* Main menu loop */
		
		boolean exitFlag = false;	// true when user selects Exit
		
		do {
			// print menu options
			System.out.print(
				"Select your option in the menu below: \n"
				+ "1. Initialize and populate an array of 20 random integers. \n"
				+ "2. Perform a recursive binary search. \n"
				+ "3. Perform a non-recursive binary search. \n"
				+ "4. Exit. \n"
				+ " >");
			
			// get input
			int optionInput = -1;	// invalid value in case of input mismatch
			
			try {
				
				optionInput = keyboard.nextInt();
				
			} catch (InputMismatchException e) {
				
				keyboard.nextLine();	// flush buffer due to scanner bug 
			}
			
			switch (optionInput) {
			
			// generate random array
			case 1:
				numbers = binsearch.generateRandomInts(TEST_SIZE, TEST_BOUND_LOWER, TEST_BOUND_UPPER);
				break;
				
			// recursive binary search
			// TODO: replace placeholder with call to Binarysearch::recursiveBinarySearch()
			case 2:
				System.out.println("Option 2 placeholder");
				break;
				
			// non-recursive binary search
			// TODO: add timers
			case 3:
				if (numbers == null) {
					
					System.out.println("Generate values first.");
					break;
				}
				
				binsearch.nonRecursiveBinarySearch(numbers, 29); // TODO: add searchVal input
				break;
				
			// exit
			case 4:
				exitFlag = true;
				break;
				
			default:
				System.out.println("Invalid input...try again");
				break;
			}
			
		} while (exitFlag == false);	// stop loop if user selects Exit
		
		// print exit greeting
		System.out.println("Exiting...Goodbye");
		
		// close keyboard input stream
		keyboard.close();
	}
}
