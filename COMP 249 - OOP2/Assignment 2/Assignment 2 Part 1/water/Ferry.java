// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant to evaluate inheritance.
// It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
// inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
// ticket prices.
// -----------------------------------------------------

package water;

import transport.PublicTransport;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * Ferry class derived from PublicTransport.
 */
public class Ferry extends PublicTransport {

	// Visibility: package access -> allows for access of attributes of object from any class/object in the package
	// This class could be easily derived and derived classes can use these attributes without mutators and accessors
	// as long as the derived classes are within the same package.
	int    buildYear;
	String skipName = "";

	/**
	 * Default Constructor for Ferry
	 */
	public Ferry() {

		// *** Uses parent class's constructor automatically *** no need of super()

		buildYear = 0;
	}

	/**
	 * Parameterized Constructor for Ferry
	 *
	 * @param ticketPrice   a double containing the ticketPrice
	 * @param numberOfStops an integer containing the numberOfStops
	 * @param buildYear     an integer containing the year it was built
	 * @param skipName      a String containing the name of the Skip
	 */
	public Ferry(double ticketPrice, int numberOfStops, int buildYear, String skipName) {
		// Uses parent class's constructor
		super(ticketPrice, numberOfStops);

		this.buildYear = buildYear;
		this.skipName = skipName;
	}

	/**
	 * Copy Constructor for Ferry
	 *
	 * @param ferry object containing the Ferry to be copied
	 */
	public Ferry(Ferry ferry) {
		// Uses parent class's constructor
		super(ferry);

		this.buildYear = ferry.buildYear;
		this.skipName = ferry.skipName;
	}

	/**
	 * Gets the year it was built
	 *
	 * @return an integer representing the year it was built
	 */
	public int getBuildYear() {
		return buildYear;
	}

	/**
	 * Sets the year it was built
	 *
	 * @param buildYear an integer containing the new year it was built
	 */
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	/**
	 * Gets the name of the Skip
	 *
	 * @return a String representing the name of the Skip
	 */
	public String getSkipName() {
		return skipName;
	}

	/**
	 * Sets the name of the Skip
	 *
	 * @param skipName a String representing the new name of the Skip
	 */
	public void setSkipName(String skipName) {
		this.skipName = skipName;
	}

	/**
	 * Tests for the equality of two objects.
	 * *** Works for any object except if the calling object itself is null ***
	 *
	 * @param o an object that is to be compared to the calling object
	 *
	 * @return a boolean value that says whether the calling object and the parameter is equal or not
	 */
	public boolean equals(Object o) {
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		Ferry ferry = (Ferry) o;
		return buildYear == ferry.buildYear &&
				skipName.equals(ferry.skipName) &&
				super.equals(ferry);
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This Ferry's" +
				" ticket price is $" + ticketPrice +
				" and has " + numberOfStops + " stops." +
				" It was built in " + buildYear +
				" and its Skip is " + skipName + ".";
	}
}
