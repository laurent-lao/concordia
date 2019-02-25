// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant to evaluate inheritance.
// It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
// inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
// ticket prices.
// -----------------------------------------------------

package transport;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * PublicTransport class being the parent class.
 */
public class PublicTransport {

	// Old Visibility: protected (package access and derived classes) -> This allows for easily extending the class
	// and derived classes can use these attributes without using mutators and accessors.
	// New Visibility: private -> All derived classes must use mutators and accessors to access these attributes
	// Only affects the derived toString (parameterized constructor and equals() uses super)
	private double ticketPrice;
	private int    numberOfStops;

	/**
	 * Default Constructor for PublicTransport
	 */
	public PublicTransport() {
		ticketPrice = 0;
		numberOfStops = 0;
	}


	/**
	 * Parameterized Constructor for PublicTransport
	 *
	 * @param ticketPrice   a double containing the ticketPrice
	 * @param numberOfStops an integer containing the numberOfStops
	 */
	public PublicTransport(double ticketPrice, int numberOfStops) {
		this.ticketPrice = ticketPrice;
		this.numberOfStops = numberOfStops;
	}

	/**
	 * Copy Constructor for PublicTransport
	 *
	 * @param publicTransport object containing the PublicTransport to be copied
	 */
	public PublicTransport(PublicTransport publicTransport) {
		this.ticketPrice = publicTransport.ticketPrice;
		this.numberOfStops = publicTransport.numberOfStops;
	}

	/**
	 * Gets price of the tickets
	 *
	 * @return a double representing the ticket price
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}

	/**
	 * Sets the price of the tickets
	 *
	 * @param ticketPrice a double containing the new ticket price
	 */
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/**
	 * Gets the number of stops
	 *
	 * @return an integer representing the number of stops
	 */
	public int getNumberOfStops() {
		return numberOfStops;
	}

	/**
	 * Sets the number of stops
	 *
	 * @param numberOfStops an intenger containing the new number of stops
	 */
	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
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
		PublicTransport that = (PublicTransport) o;
		return ticketPrice == that.ticketPrice &&
				numberOfStops == that.numberOfStops;
	}

	/**
	 * Prints the information of the object
	 *
	 * @return a string that represents the information of the object
	 */
	public String toString() {
		return "This Public Transport's" +
				" ticket price is $" + ticketPrice +
				" and has " + numberOfStops + " stops.";
	}
}