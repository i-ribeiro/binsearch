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

	/** Entry point to test the Binarysearch class. 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);	// user input stream from keyboard
		
		
		/* Main menu loop */
		
		boolean exitFlag = false;	// true when user selects Exit
		
		do {
			// print menu options
			System.out.println(		// TODO: change to print() so that user input is in line with the prompt 
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
			// TODO: replace placeholder with call to Binarysearch::generateRandomInts()
			case 1:
				System.out.println("Option 1 placeholder");
				break;
				
			// recursive binary search
			// TODO: replace placeholder with call to Binarysearch::recursiveBinarySearch()
			case 2:
				System.out.println("Option 2 placeholder");
				break;
				
			// non-recursive binary search
			// TODO: replace placeholder with call to Binarysearch::nonRecursiveBinarySearch()
			case 3:
				System.out.println("Option 3 placeholder");
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
