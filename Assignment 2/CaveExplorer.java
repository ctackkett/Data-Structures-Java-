///////////////////////////////////////////////////////////////////////////////////////////////
//CaveExplorer.java
//Author:Connor Tackkett
//Date:9/25/2022
//Description: This is where all methods for the path solving CaveExplorer algorithm are created.
///////////////////////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CaveExplorer {
	//Below can be found the fields that are used in the methods within this CaveExplorer class.
	//directionalPath is used to store the directions, if there is a path through a cave. dotFound and pFound are used
	//for checks dealing with searching the cave for a path. currentRow and Col are the current position in the cave.
	//numArrayRows and Cols is for dealing with the bounds of the cave as well as what positions have been visited
	//beenVisited checks stores positions in the cave that have already been checked. fileName is a string for the file to
	//be input. isPath is useful within methods such as solve and store whether there is a path in the cave. default cave is the
	//declaration of what will become the cave.
	static String directionalPath = "";
	boolean dotFound = false;
	boolean pFound = false;
	int currentRow;
	int currentCol;

	static int numArrayRows = 0;
	static int numArrayCols = 0;

	static boolean[][] beenVisited = null;
	//boolean[][] beenVisited = new boolean[6][5];

	static String fileName = "";

	boolean isPath = false;

	static char defaultCave[][] = null;

	//Constructor for cave that takes in .txt file input as a parameter.
	public CaveExplorer(String fn) throws Exception {
		fileName = fn;
	}

	//Unused but required hardcoded Constructor for the cave I used to test the algorithm as it was being made.
	public char[][] CaveExplorer() {
		char mysteryCave[][] = new char[][]{ {'S', 'S', 'S', 'S', 'S', 'S'},
							                 {'S', 'S', '.', 'M', 'S', 'S'},
							                 {'S', 'S', '.', 'S', 'S', 'S'},
							                 {'S', 'S', '.', '.', 'P', 'S'},
							                 {'S', 'S', 'S', 'S', 'S', 'S'}};
		return mysteryCave;
	}

	//This is the method that creates the cave and deals with reading the information in the provided .txt file.
	//It utilizes a try and catch to handle the exception that comes with file input. A new file object is created, as well
	//as a scanner object. NextInt and nextLine are used for reading the file. beenVisited deals with positions in the cave
	//and if they have been checked or not while mysteryCave is the cave itself. The for loop, loops through the string(s) in the
	//file character by character and reads in the cave. The file is then closed. If there is an exception the appropriate message
	//is printed.
	public void createCave() throws Exception {
		String lineFromFile = "";
		char mysteryCave[][] = null;

		try {
			File inFile = new File(fileName);
			Scanner fileReader = new Scanner(inFile);

			numArrayRows = fileReader.nextInt();
			numArrayCols = fileReader.nextInt();
			fileReader.nextLine();

			beenVisited = new boolean[numArrayRows][numArrayCols];
			mysteryCave = new char[numArrayRows][numArrayCols];

			for (int j = 0; j < numArrayRows; j++) {
				lineFromFile = fileReader.nextLine();
				for (int i = 0; i < lineFromFile.length(); i++) {
					mysteryCave[j][i] = lineFromFile.charAt(i);
				}
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while processing file." + fileName);
			e.printStackTrace();
		}

		defaultCave = mysteryCave;
	}

	//This custom toString was created while I was using the hardcoded cave. It originally helped with reading in the hardcoded
	//cave and printing it with the newlines, as the cave should look. I utilized a string builder to append the contents of the 2d
	//array to print. Since the file constructor and createCave eventually handled the printing of the cave based on what was read out of the file
	//the method became a little obsolete. It overrides the toString class in Object.
	public String toString() {
		StringBuilder caveBuilder = new StringBuilder();
		for (int i = 0; i < defaultCave.length; i++) {
			for (int j = 0; j < defaultCave[i].length; j++) {
				caveBuilder.append(defaultCave[i][j]);
			}
			caveBuilder.append("\n");
		}
		return caveBuilder.toString();
	}

	//This solve method is what actually finds out whether there is a valid path through the cave or not.
	//It does this by utilizing the helper methods described below. To start, the starting position is found, a boolean to
	//later on help stop infinite loops is initialized as well. Then the while loop begins which checks north, east, south, west
	//for the appropriate characters. If '.' is found and 'P' has not been found, the loop runs again. If '.' is not found at any point
	//and 'P' has not been found, the next direction does its check, and so on. Once 'P' is found, the boolean for if there is a path or not becomes
	//true and the boolean that keeps the loop running, shuts the loop down. Returned is the value for if there is a path or not.
	public boolean solve() {
		findMe();

		boolean isPathCheckComplete = false;

		while (!isPathCheckComplete) {
			traverseNorth();

			if (!dotFound && !pFound) {
				traverseEast();
			}

			if (!dotFound && !pFound) {
				traverseSouth();
			}

			if (!dotFound && !pFound) {
				traverseWest();
			}

			if (!dotFound && !pFound){
				isPath = false; // There is no path - return false
				isPathCheckComplete = true;
			}

			if (pFound) {
				isPath = true;
				isPathCheckComplete = true;
			}

			dotFound = false;
		}

		return isPath;
	}

	//The following four methods are helper methods that are used within the very important solve method
	//They all do the same thing in a different direction, that being based upon what position is currently being looked
	//at within the cave. The starting position will always be where 'M' is. The methods check if the direction can be looked at
	//to begin with by checking if it will go out of bounds. It searches to see if the position to be checked has already
	//been checked or not and if the item that it is searching for is there. If that is the case, then the direction is added
	//to a string and the position found becomes the position to search from next. It also searches for P. It utilized boolean values
	//to keep track of whether certain things have been found, which is important for solve. Each method is the same besides the direction
	//of the search.
	private void traverseNorth() {
		if (currentRow != 0) {
			if (defaultCave[currentRow - 1][currentCol] == '.' && !beenVisited[currentRow - 1][currentCol]) {
				beenVisited[currentRow - 1][currentCol] = true;
				currentRow = currentRow - 1;
				directionalPath = directionalPath + "N";
				dotFound = true;
			} else if (defaultCave[currentRow - 1][currentCol] == 'P' && !beenVisited[currentRow - 1][currentCol]) {
				directionalPath = directionalPath + "N";
				pFound = true;
			}
		}
	}

	private void traverseEast() {
		if (currentCol != numArrayCols) {
			if (defaultCave[currentRow][currentCol + 1] == '.' && !beenVisited[currentRow][currentCol + 1]) {
				beenVisited[currentRow][currentCol + 1] = true;
				currentCol = currentCol + 1;
				directionalPath = directionalPath + "E";
				dotFound = true;
			} else if (defaultCave[currentRow][currentCol + 1] == 'P' && !beenVisited[currentRow][currentCol + 1]) {
				directionalPath = directionalPath + "E";
				pFound = true;
			}
		}
	}

	private void traverseSouth() {
		if (currentRow != numArrayRows) {
			if (defaultCave[currentRow + 1][currentCol] == '.' && !beenVisited[currentRow + 1][currentCol]) {
				beenVisited[currentRow + 1][currentCol] = true;
				currentRow = currentRow + 1;
				directionalPath = directionalPath + "S";
				dotFound = true;
			} else if (defaultCave[currentRow + 1][currentCol] == 'P' && !beenVisited[currentRow + 1][currentCol]) {
				directionalPath = directionalPath + "S";
				pFound = true;
			}
		}
	}

	private void traverseWest() {
		if (currentCol != 0) {
			if (defaultCave[currentRow][currentCol - 1] == '.' && !beenVisited[currentRow][currentCol - 1]) {
				beenVisited[currentRow][currentCol - 1] = true;
				currentCol = currentCol - 1;
				directionalPath = directionalPath + "W";
				dotFound = true;
			} else if (defaultCave[currentRow][currentCol - 1] == 'P' && !beenVisited[currentRow][currentCol - 1]) {
				directionalPath = directionalPath + "W";
				pFound = true;
			}
		}
	}

	//This method is for printing out the directional path depending on if there was a valid path through the cave or not
	//If there was, the path is printed. If not, then it is handled by the below text.
	public String getPath() {
		if (isPath) {
			return "The path is: " + directionalPath;
		}
		else {
			return "There is no path";
		}
	}

	//Rather than searching through the array to find where M was, I created its own method so that once it is called, the
	//position of M is immediately known and its position can be referenced by currentRow and Col.
	public void findMe() {
		for (int row = 0; row < numArrayRows; row++) {
			for (int col = 0; col < numArrayCols; col++) {
				if (defaultCave[row][col] == 'M') {
					currentRow = row;
					currentCol = col;
					return;
				}
			}
		}
	}
}

