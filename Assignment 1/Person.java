/* Person.java:  
 * This class provides a basic object to extend in future labs. 
 * Comments are omitted for accessor and mutator methods    
 * Author: John Cigas
 * Last updated: July 2021
 */

public class Person {

	private String name;
	private int age;
	private int moveCount;

	// moveCount and its getters and setters were added here so they could be easily accessed from within a contestant.
	public Person(String name, int age, int moveCount) {
		this.name = name;
		this.age = age;
		this.moveCount = moveCount;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoveCount() { return moveCount; }

	public void setMoveCount(int moveCount) { this.moveCount = moveCount; }

	@Override
	public String toString() {
		return "Person [name=" + name + ", ID=" + age + "]";
	}
}
