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
			int optionInput = inputInt(keyboard, false);	// -1 on input mismatch
			
			int searchVal;
			
			switch (optionInput) {
			
			// generate random array
			case 1:
				numbers = binsearch.generateRandomInts(TEST_SIZE, TEST_BOUND_LOWER, TEST_BOUND_UPPER);
				break;
				
			// recursive binary search
			// TODO: add timers
			// TODO: fix "Number not found" printing twice
			// TODO: fix remaining elements not printing when search value found immediately
			case 2:
				if (numbers == null) {
					
					System.out.println("Generate values first.");
					break;
				}
				
				// input search value
				System.out.print("Please enter an integer value to search: ");
				searchVal = inputInt(keyboard, true);
				
				
				int index = binsearch.recursiveBinarySearch(numbers, searchVal, 0, numbers.length - 1);
				
				
				// if index is valid, the value was found
				if (index >= 0)
					System.out.printf("Number %d was found at index %d. \n\n", searchVal, index);
				
				else System.out.printf("Number %d was not found. \n\n", searchVal);
				
				break;
				
			// non-recursive binary search
			// TODO: add timers
			case 3:
				if (numbers == null) {
					
					System.out.println("Generate values first.");
					break;
				}
				
				// input search value
				System.out.print("Please enter an integer value to search: ");
				searchVal = inputInt(keyboard, true);
				
				binsearch.nonRecursiveBinarySearch(numbers, searchVal);
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
