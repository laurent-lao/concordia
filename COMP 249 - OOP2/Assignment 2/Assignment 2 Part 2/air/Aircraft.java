// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant to evaluate inheritance.
// It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
// inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
// ticket prices.
// -----------------------------------------------------

package air;

import transport.PublicTransport;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * Aircraft class derived from PublicTransport.
 */
public class Aircraft extends PublicTransport {

	// Visibility: public -> enums are final and can't be modified; public is totally fine.
	// Setting to public allows for ease of use from other classes.
	public enum Class {
		Helicopter,
		Airline,
		Glider,
		Balloon
	}

	public enum Maintenance {
		Weekly,
		Monthly,
		Yearly
	}

	// Old Visibility: package access -> allows for access of attributes of object from any class/object in the package
	// This class could be easily derived and derived classes can use these attributes without mutators and accessors
	// as long as the derived classes are within the same package.
	// New Visibility: private -> All derived classes must use mutators and accessors to access these attributes
	// Doesn't affect anything as Aircraft does not have any derived classes atm
	private Class       aircraftClass;
	private Maintenance aircraftMaintenanceSchedule;

	/**
	 * Default Constructor for Aircraft
	 */
	public Aircraft() {

		// *** Uses parent class's constructor automatically *** no need of super()

		aircraftClass = null;
		aircraftMaintenanceSchedule = null;
	}


	/**
	 * Parameterized Constructor for Aircraft
	 *
	 * @param ticketPrice                 a double containing the ticketPrice
	 * @param numberOfStops               an integer containing the numberOfStops
	 * @param aircraftClass               a Class enum type containing the class
	 * @param aircraftMaintenanceSchedule a Maintenance enum type containing the frequency of the maintenance
	 */
	public Aircraft(double ticketPrice, int numberOfStops, Class aircraftClass, Maintenance aircraftMaintenanceSchedule) {

		// Uses parent class's constructor
		super(ticketPrice, numberOfStops);

		this.aircraftClass = aircraftClass;
		this.aircraftMaintenanceSchedule = aircraftMaintenanceSchedule;

	}

	/**
	 * Copy Constructor for Aircraft
	 *
	 * @param aircraft object containing the Aircraft to be copied
	 */
	public Aircraft(Aircraft aircraft) {
		// Uses parent class's constructor
		super(aircraft);

		this.aircraftClass = aircraft.aircraftClass;
		this.aircraftMaintenanceSchedule = aircraft.aircraftMaintenanceSchedule;
	}

	/**
	 * Gets the name of the Aircraft class
	 *
	 * @return a Class enum type representing the class of the Aircraft
	 */
	public Class getAircraftClass() {
		return aircraftClass;
	}

	/**
	 * Sets the name of the Aircraft class
	 *
	 * @param aircraftClass a Class enum type containing the new class of the Aircraft
	 */
	public void setAircraftClass(Class aircraftClass) {
		this.aircraftClass = aircraftClass;
	}

	/**
	 * Gets the frequency of the Aircraft maintenance
	 *
	 * @return a Maintenance enum type representing the frequency of the Aircraft maintenance
	 */
	public Maintenance getAircraftMaintenanceSchedule() {
		return aircraftMaintenanceSchedule;
	}

	/**
	 * Sets the frequency of the Aircraft maintenance
	 *
	 * @param aircraftMaintenanceSchedule a Maintenance enum type containing the new frequency of the Aircraft maintenance
	 */
	public void setAircraftMaintenanceSchedule(Maintenance aircraftMaintenanceSchedule) {
		this.aircraftMaintenanceSchedule = aircraftMaintenanceSchedule;
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
		Aircraft aircraft = (Aircraft) o;
		return aircraftClass == aircraft.aircraftClass &&
				aircraftMaintenanceSchedule == aircraft.aircraftMaintenanceSchedule;
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This Aircraft 's" +
				" ticket price is $" + getTicketPrice() +
				" and has " + getNumberOfStops() + " stops." +
				" Its class is " + aircraftClass +
				" and it is maintained " + aircraftMaintenanceSchedule + ".";
	}
}
