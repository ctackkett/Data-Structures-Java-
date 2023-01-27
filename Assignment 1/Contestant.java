/* Contestant.java:  
 * This class extends Person while adding attributes and methods
 * related to recording performance in the specified contest. 
 *     
 * Author: Name and Student ID
 * Last updated: 
 */
public class Contestant extends Person {
	// A Contestant IS-A Person
	// that also needs to keep track of data related to the game
	// Start simple to get your program running, then add attributes and methods as you need them
	
	// Step 1 Add at least one constructor so the driver will compile and run
    // Creation of a Contestant object that contains both a name and a randomID, this allows the Contestant driver
    // to work properly so that the program can run and be tested.
    // Contestant takes in a name, a random ID based on our student numbers
    // and also later on a movecount that counts how many times certain elements in an array have been moved to
    // the front of the array.
	public Contestant(String name, int digitRandomID, int moveCount) {

        super(name, digitRandomID, moveCount);
    }
}
	
	// Step 5 Add attributes and methods to assist with recording game statistics
