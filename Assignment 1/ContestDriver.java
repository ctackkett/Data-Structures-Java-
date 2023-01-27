import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
public class ContestDriver {
	// Random instantiation so that the random import can be utilized for our randomID's
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		LinkedList<Contestant> contestantsArrayList = new LinkedList<>();
		System.out.println("Starting Simulation");

		// Step -1 Compile the program as is to be sure it runs

		// Step 0 - Uncomment the two lines below, then add code to the Contestant class to make this compile
		// This creates a single Contestant and prints its attributes, just to show it can be done
		// This for loop makes sure that 10 contestants will be created with unique names and unique random ID's
		// The while loop makes sure that the randomID's fall between the range of 169 and 740.
		int moveCount = 0;
		int nameCounter = 0;
		System.out.println("Please enter the number of contestants: ");
		int numContestants = scanner.nextInt();
		// This for loop takes user input and generates random ID for that number of given contestants. Those random ID's are
		// then converted into their last digit so the sorting algorithm can take place easier.
		for (int n = 0; n < numContestants; n++) {
			nameCounter++;
			int randomID = 0;
			int digitRandomID = 0;
			// The while loop maintains the number range to be within my student number. It is then
			// converted into the last digit of said number.
			while (randomID <= 168) {
				randomID = random.nextInt(741);
				digitRandomID = randomID % 10;
			}
			Contestant contestant = new Contestant("Contestant" + nameCounter, digitRandomID, moveCount);
			contestantsArrayList.add(contestant);
		}
		// System.out.println(contestantsArrayList);

		// Step 2 ^^^
		// Create multiple Contestants and add them to an ArrayList
		// Do something simple to get started, but remember, you will need
		// to generate up to 1,000,000 Contestants


		// Steps 3 and 4
		// Play a round
		// For each Contestant, flip a coin, then move the Contestant to
		// one end of the ArrayList or the other.

		// Here some very important variables to make the algorithm work are created. Current Index makes sure that
		// no elements are skipped or gone over more than once, current match is a variable which holds the value of the most
		// recent duplicate pair. Also what can be seen here is the clock that allows us to time how long the algorithm takes to reach completion.
		System.out.println("PLease enter the number of rounds: ");
		int rounds = scanner.nextInt();
		int currentMatch = -1;
		int currentIndex = 0;
		double totalMoveCount = 0;
		final long startTime = System.currentTimeMillis();

		// The first for loop makes sure the algorithm runs for the number of rounds provided. Every round that is new, the index and match counters are reset so that
		// they work properly on the next sort.
		for (int r = 0; r < rounds; r++) {
			//System.out.println(contestantsArrayList);
			currentIndex=0;
			currentMatch=-1;
			// This is the bulk of the algorithm. It goes for the length of the array -1 so we do not run into an out of bounds error
			// currentMatch and currentIndex are what make sure that as the array gets sorted, so we do not lose sight of what has and has not been sorted.
			// currentMatch and the number which the current index is pointing to are checked to see if they are the same, if they are, currentMatch is reset
			// in order for more potential duplicate pairs to be moved.
			for (int i = 0; i < contestantsArrayList.size()-1; i++) {
				if (currentMatch != contestantsArrayList.get(currentIndex).getAge()) {
					currentMatch = -1;
				}
				Contestant currentContestant = contestantsArrayList.get(currentIndex);
				Contestant nextContestant = contestantsArrayList.get(currentIndex+1);
				// The above contestants keep track of the current and next position in the array. This is important since those are the two
				// elements that are constantly being compared.
				// the below elements are ints rather than contestants because they hold the numeric information that is compared to one another.
				int lastDigit = contestantsArrayList.get(currentIndex).getAge();
				int nextLastDigit = contestantsArrayList.get(currentIndex+1).getAge();
				// This is regarding elements in the array that are the same, therefore they will be sent to the back of
				// the array. They are removed and then simply added back since adding them will automatically throw them to the back of the array.
				if (lastDigit == nextLastDigit) {
					contestantsArrayList.remove(nextContestant);
					contestantsArrayList.remove(currentContestant);
					contestantsArrayList.add(currentContestant);
					contestantsArrayList.add(nextContestant);
					currentMatch = lastDigit;
				}
				// This is just the opposite of above. Since these numbers are not the same the first number compared will be sent
				// to the front of the list. It cannot simply be removed and added, so it must be added with a signified index of where it will go.
				// totalMoveCount will be incremented since within this if, contestants are moved to the front, also, since an element has been dealt with, the index counter increments.
				if (lastDigit != nextLastDigit) {
					contestantsArrayList.remove(currentContestant);
					contestantsArrayList.add(0, currentContestant);
					currentIndex++;
					totalMoveCount++;
				}
			}
			// for every position in the array, the contestants moveCount is retrieved and is added to the total variable.
			// this is important because it allows us to calculate the averages later.
			for (int j = 0; j <contestantsArrayList.size(); j++) {
				totalMoveCount += contestantsArrayList.get(currentIndex).getMoveCount();
			}
			//System.out.println(totalMoveCount);
			//System.out.println(contestantsArrayList);
			System.out.println("Finished");
		}
		// Here we see that the time is collected for when the algorithm finished. The start and end times are then compared to see how long it took.
		// The totalMoveCount averages can then also be averaged and output as expected.
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));
		System.out.println("The move count average overall is: " + (totalMoveCount/rounds));
	}
}

// Steps 5 - 7
// Repeat Steps 3 and 4 many times to gather data.
// You will need to modify the Contestant class so each Contestant
// will keep track of its individual statistics. Be smart about how you record its
// position at the end of each round.
