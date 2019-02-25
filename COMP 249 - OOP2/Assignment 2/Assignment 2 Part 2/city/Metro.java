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
 * Metro class derived from CityBus.
 */
public class Metro extends CityBus {

	// Old Visibility: package access -> allows for access of attributes of object from any class/object in the package
	// This class could be easily derived and derived classes can use these attributes without mutators and accessors
	// as long as the derived classes are within the same package.
	// New Visibility: private -> All derived classes must use mutators and accessors to access these attributes
	// Doesn't affect anything as Metro does not have any derived classes atm
	private int    numberOfVehicles;
	private String nameOfTheCity = "";

	/**
	 * Default Constructor for Metro
	 */
	public Metro() {

		// *** Uses parent class's constructor automatically ***

		this.numberOfVehicles = 0;
	}

	/**
	 * Parameterized Constructor for Metro
	 *
	 * @param ticketPrice        a double containing the ticket price
	 * @param numberOfStops      an integer containing the number of stops
	 * @param routeNumber        a long containing the route number
	 * @param beginOperationYear an integer containing the starting operation year
	 * @param lineName           a String containing the name of the line
	 * @param driverName         a String containing the name of the driver
	 * @param numberOfVehicles   an integer containing the number of vehicles
	 * @param nameOfTheCity      a String containing the city in which the object operates
	 */
	public Metro(double ticketPrice, int numberOfStops, long routeNumber, int beginOperationYear, String lineName, String driverName, int numberOfVehicles, String nameOfTheCity) {
		// Uses parent class's constructor
		super(ticketPrice, numberOfStops, routeNumber, beginOperationYear, lineName, driverName);

		this.numberOfVehicles = numberOfVehicles;
		this.nameOfTheCity = nameOfTheCity;
	}

	/**
	 * Copy Constructor for Metro
	 *
	 * @param metro object containing the Metro to be copied
	 */
	public Metro(Metro metro) {
		// Uses parent class's constructor
		super(metro);

		this.numberOfVehicles = metro.numberOfVehicles;
		this.nameOfTheCity = metro.nameOfTheCity;
	}

	/**
	 * Gets the number of vehicles
	 *
	 * @return an integer representing the number of vehicles
	 */
	public int getNumberOfVehicles() {
		return numberOfVehicles;
	}

	/**
	 * Sets the number of vehicles
	 *
	 * @param numberOfVehicles an integer containing the new number of vehicles
	 */
	public void setNumberOfVehicles(int numberOfVehicles) {
		this.numberOfVehicles = numberOfVehicles;
	}

	/**
	 * Gets the name of the city
	 *
	 * @return a String representing the name of the city in which the object operates
	 */
	public String getNameOfTheCity() {
		return nameOfTheCity;
	}

	/**
	 * Sets the name of the city
	 *
	 * @param nameOfTheCity a String containing the new name of the city in which the object operates
	 */
	public void setNameOfTheCity(String nameOfTheCity) {
		this.nameOfTheCity = nameOfTheCity;
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
		Metro metro = (Metro) o;
		return numberOfVehicles == metro.numberOfVehicles &&
				nameOfTheCity.equals(metro.nameOfTheCity);
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This Metro's" +
				" ticket price is $" + getTicketPrice() +
				", has " + getNumberOfStops() + " stops" +
				" and " + numberOfVehicles + " vehicles." +
				" The Route Number " + getRouteNumber() +
				" (" + getLineName() + ")" +
				" operating in " + nameOfTheCity +
				" started in " + getBeginOperationYear() +
				" and is driven by " + getDriverName() + ".";
	}
}
