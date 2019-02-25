// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant to evaluate inheritance.
// It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
// inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
// ticket prices.
// -----------------------------------------------------

package city;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * Tram class derived from CityBus.
 */
public class Tram extends CityBus {

	// Old Visibility: package access -> allows for access of attributes of object from any class/object in the package
	// This class could be easily derived and derived classes can use these attributes without mutators and accessors
	// as long as the derived classes are within the same package.
	// New Visibility: private -> All derived classes must use mutators and accessors to access these attributes
	// Doesn't affect anything as Tram does not have any derived classes atm
	private int maximumSpeed;

	/**
	 * Default Constructor for Tram
	 */
	public Tram() {

		// *** Uses parent class's constructor automatically ***

		this.maximumSpeed = 0;
	}

	/**
	 * Parameterized Constructor for Tram
	 *
	 * @param ticketPrice        a double containing the ticket price
	 * @param numberOfStops      an integer containing the number of stops
	 * @param routeNumber        a long containing the route number
	 * @param beginOperationYear an integer containing the starting operation year
	 * @param lineName           a String containing the name of the line
	 * @param driverName         a String containing the name of the driver
	 * @param maximumSpeed       an integer containing the maximum speed
	 */
	public Tram(double ticketPrice, int numberOfStops, long routeNumber, int beginOperationYear, String lineName, String driverName, int maximumSpeed) {
		// Uses parent class's constructor
		super(ticketPrice, numberOfStops, routeNumber, beginOperationYear, lineName, driverName);

		this.maximumSpeed = maximumSpeed;
	}

	/**
	 * Copy Constructor for Tram
	 *
	 * @param tram object containing the Tram to be copied
	 */
	public Tram(Tram tram) {
		// Uses parent class's constructor
		super(tram);

		this.maximumSpeed = tram.maximumSpeed;
	}

	/**
	 * Gets the maximum speed
	 *
	 * @return an integer representing the maximum speed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	/**
	 * Sets the maximum speed
	 *
	 * @param maximumSpeed an intenger containing the new maximum speed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
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
		Tram tram = (Tram) o;
		return maximumSpeed == tram.maximumSpeed;
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This Tram's" +
				" ticket price is $" + getTicketPrice() +
				", has " + getNumberOfStops() + " stops" +
				" and has a maximum speed of " + maximumSpeed + " km/h." +
				" The Route Number " + getRouteNumber() +
				" (" + getLineName() + ")" +
				" started in " + getBeginOperationYear() +
				" and is driven by " + getDriverName() + ".";
	}
}
