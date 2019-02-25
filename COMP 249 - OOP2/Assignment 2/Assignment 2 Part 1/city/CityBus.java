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

import transport.PublicTransport;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * CityBus class derived from PublicTransport.
 */
public class CityBus extends PublicTransport {

	// Visibility: Protected as defined by the assignment instructions (package access and derived classes)
	// -> This allows for easily extending the class and derived classes can use these attributes without
	// using mutators and accessors.
	protected long   routeNumber;
	protected int    beginOperationYear;
	protected String lineName   = "";
	protected String driverName = "";

	/**
	 * Default Constructor for CityBus
	 */
	public CityBus() {

		// *** Uses parent class's constructor automatically ***

		routeNumber = 0;
		beginOperationYear = 0;
	}


	/**
	 * Parameterized Constructor for CityBus
	 *
	 * @param ticketPrice        a double containing the ticket price
	 * @param numberOfStops      an integer containing the number of stops
	 * @param routeNumber        a long containing the route number
	 * @param beginOperationYear an integer containing the starting operation year
	 * @param lineName           a String containing the name of line
	 * @param driverName         a String containing the name of the driver
	 */
	public CityBus(double ticketPrice, int numberOfStops, long routeNumber, int beginOperationYear, String lineName, String driverName) {
		// Uses parent class's constructor
		super(ticketPrice, numberOfStops);

		this.routeNumber = routeNumber;
		this.beginOperationYear = beginOperationYear;
		this.lineName = lineName;
		this.driverName = driverName;
	}

	/**
	 * Copy Constructor for CityBus
	 *
	 * @param cityBus object containing the CityBus to be copied
	 */
	public CityBus(CityBus cityBus) {
		// Uses parent class's constructor
		super(cityBus);

		this.routeNumber = cityBus.routeNumber;
		this.beginOperationYear = cityBus.beginOperationYear;
		this.lineName = cityBus.lineName;
		this.driverName = cityBus.driverName;
	}

	/**
	 * Gets Route Number
	 *
	 * @return a long representing the route number
	 */
	public long getRouteNumber() {
		return routeNumber;
	}

	/**
	 * Sets Route Number
	 *
	 * @param routeNumber a long containing the new route number
	 */
	public void setRouteNumber(long routeNumber) {
		this.routeNumber = routeNumber;
	}

	/**
	 * Gets the year it started operating
	 *
	 * @return an integer representing the year it started operating
	 */
	public int getBeginOperationYear() {
		return beginOperationYear;
	}

	/**
	 * Sets the year it started operating
	 *
	 * @param beginOperationYear an intenger containing the year it started operating
	 */
	public void setBeginOperationYear(int beginOperationYear) {
		this.beginOperationYear = beginOperationYear;
	}

	/**
	 * Gets the name of the line
	 *
	 * @return a string representing the name of the line
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * Sets the name of the line
	 *
	 * @param lineName a string containing the new name of the line
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * Gets the name of the driver
	 *
	 * @return a string representing the name of the driver
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * Sets the name of the driver
	 *
	 * @param driverName a string containing the new name of the driver
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
		CityBus cityBus = (CityBus) o;
		return routeNumber == cityBus.routeNumber &&
				beginOperationYear == cityBus.beginOperationYear &&
				lineName.equals(cityBus.lineName) &&
				driverName.equals(cityBus.driverName);
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This City Bus's" +
				" ticket price is $" + ticketPrice +
				" and has " + numberOfStops + " stops." +
				" The Route Number " + routeNumber +
				" (" + lineName + ")" +
				" started in " + beginOperationYear +
				" and is driven by " + driverName + ".";
	}
}
