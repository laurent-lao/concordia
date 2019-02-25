package Driver;// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant to evaluate inheritance.
// It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
// inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
// ticket prices.
//
// EXTRA: Made random object generators because I was too lazy to make them by hand.
// -----------------------------------------------------

import java.util.Random;

import transport.*;
import air.*;
import water.*;
import city.*;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #2 Part 1
 * Due Date: February 24 2019
 * Driver.Driver file containing main and some static helper methods.
 */
public class Driver {

	// Arrays to be used for generating objects
	// Usage: listOfCities[getRandomInt(listOfCities.length)]
	private static String[] listOfCities = new String[] {
			"Montreal", "Toronto",
			"Ottawa", "Calgary",
			"Vancouver", "Halifax",
			"Fredericton", "Quebec",
			"Regina", "Victoria",
			"Saskatoon", "Edmonton",
			"Charlottetown", "St. John's",
			"Winnipeg"};
	// Usage: listOfNames[getRandomInt(listOfNames.length)]
	private static String[] listOfNames  = new String[] {
			"Sophia", "Aria",
			"Amelia", "Jackson",
			"Noah", "Grayson",
			"Riley", "Charlotte",
			"Olivier", "Elijah",
			"Lily", "Emily",
			"Logan", "Liam",
			"Mason", "Mia",
			"Abigail", "Lucas",
			"Jacob", "Matthew",
			"Hanna", "Brianna"};

	// Usage: listOfColours[getRandomInt(listOfColours.length)]
	private static String[] listOfColours = new String[] {
			"Red", "Pink",
			"Orange", "Yellow",
			"Purple", "Green",
			"Blue", "Brown",
			"White", "Gray"};

	/**
	 * Copying City Bus into another array of PublicTransport
	 *
	 * @param publicTransports an array containing the PublicTransports to be copied
	 *
	 * @return an array representing the new copied array
	 */
	public static PublicTransport[] copyCityBus(PublicTransport[] publicTransports) {
		PublicTransport[] publicTransports_copy = new PublicTransport[publicTransports.length];

		for (int i = 0; i < publicTransports.length; i++)
		{
			// Using the COPY CONSTRUCTORS of the DIFFERENT LISTED CLASSES as specified in the assignment
			// Assignment says the only copy CityBusses; and since Metro and Tram are also CityBusses -> it should be copied
			if (publicTransports[i].getClass() == PublicTransport.class || publicTransports[i].getClass() == Aircraft.class || publicTransports[i].getClass() == Ferry.class)
			{
				// Assigning null to non-CityBusses
				publicTransports_copy[i] = null;
			}
			else if (publicTransports[i].getClass() == CityBus.class)
			{
				publicTransports_copy[i] = new CityBus((CityBus) publicTransports[i]);
			}
			else if (publicTransports[i].getClass() == Metro.class)
			{
				publicTransports_copy[i] = new Metro((Metro) publicTransports[i]);
			}
			else if (publicTransports[i].getClass() == Tram.class)
			{
				publicTransports_copy[i] = new Tram((Tram) publicTransports[i]);
			}
			else
			{
				// Bad code -> exit
				System.out.println("Debug: Unrecognized class");
				System.exit(0);
			}
		}

		return publicTransports_copy;
	}

	/**
	 * This assignment is meant to evaluate inheritance.
	 * It creates multiple objects from classes derived from the PublicTransit class as well as PublicTransit objects
	 * inside an array, prints them, verifies for equality as well as trace searches for the least and most expensive
	 * ticket prices.
	 */
	public static void main(String[] args) {

		//debug_main(); // Debug launcher

		// Welcome message
		System.out.println("Welcome to Public Transports 2019\n\n");

		// Initializing variousObjectsArray
		int               numberOfVariousObjects = 8;
		PublicTransport[] variousObjectsArray    = new PublicTransport[numberOfVariousObjects];

		// Filling the first 6 slots and their copy  // Ferry, City Bus, Tram, Metro
		System.out.println("Filling the array with " + numberOfVariousObjects + " objects");

		System.out.println("At Index 0\nCreating a parameterized PublicTransport and its copy...");
		variousObjectsArray[0] = new PublicTransport(10, 5);
		PublicTransport publicTransportObject_same = new PublicTransport(10, 5);

		System.out.println("At Index 1\nCreating a parameterized Aircraft and its copy...");
		variousObjectsArray[1] = new Aircraft(525, 2, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly);
		Aircraft aircraft_same = new Aircraft(525, 2, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly);

		System.out.println("At Index 2\nCreating a parameterized Ferry and its copy...");
		variousObjectsArray[2] = new Ferry(25, 5, 1991, "Laurent");
		Ferry ferry_same = new Ferry(25, 5, 1991, "Laurent");

		System.out.println("At Index 3\nCreating a parameterized CityBus and its copy...");
		variousObjectsArray[3] = new CityBus(2, 25, 47, 1985, "Masson Avenue", "Laurent");
		CityBus cityBus_same = new CityBus(2, 25, 47, 1985, "Masson Avenue", "Laurent");

		System.out.println("At Index 4\nCreating a parameterized Tram and its copy...");
		variousObjectsArray[4] = new Tram(7, 15, 45, 1925, "Papineau Avenue", "My-Linh", 50);
		Tram tram_same = new Tram(7, 15, 45, 1925, "Papineau Avenue", "My-Linh", 50);

		System.out.println("At Index 5\nCreating a parameterized Metro and its copy...");
		variousObjectsArray[5] = new Metro(3.25, 20, 2, 1967, "Ligne Orange", "Bao", 7, "Chicago");
		Metro metro_same = new Metro(3.25, 20, 2, 1967, "Ligne Orange", "Bao", 7, "Chicago");

		// Fill the rest of the array and print the array
		fillArrayPublicTransport(variousObjectsArray, 6);
		printingArrayObjects(variousObjectsArray);

		// Comparing objects for equality
		for (int i = 0; i < variousObjectsArray.length; i++)
		{
			PublicTransport objectToCompare = variousObjectsArray[i];
			PublicTransport otherObjectToCompare;

			// The first 6 objects should be equal, the rest of the objects will be compared randomly
			switch (i)
			{
				case 0:
					otherObjectToCompare = publicTransportObject_same;
					break;
				case 1:
					otherObjectToCompare = aircraft_same;
					break;
				case 2:
					otherObjectToCompare = ferry_same;
					break;
				case 3:
					otherObjectToCompare = cityBus_same;
					break;
				case 4:
					otherObjectToCompare = tram_same;
					break;
				case 5:
					otherObjectToCompare = metro_same;
					break;
				default:
					otherObjectToCompare = variousObjectsArray[getRandomInt(variousObjectsArray.length)];
					break;
			}

			System.out.println("*** Comparing for equality #" + i + " ***");
			System.out.println("\t" + objectToCompare);
			System.out.println("\t" + otherObjectToCompare);
			System.out.println("\tEquality: " + objectToCompare.equals(otherObjectToCompare) + "\t");
		}

		// Comparing against null
		PublicTransport nullObject = null;
		System.out.println("*** Comparing against null ***");
		for (int i = 0; i < variousObjectsArray.length; i++)
		{
			System.out.println("\t Index " + i + (i < 10 ? " " : "") + " .equals(nullObject): " + variousObjectsArray.equals(nullObject));
		}

		// Generating 15 objects
		System.out.println("\n*** Generating the 15 objects ***");
		PublicTransport[] publicTransports = new PublicTransport[15];
		fillArrayPublicTransport(publicTransports);
		printingArrayObjects(publicTransports);

		// Searching for least and mox expensive: arrayOfIndex[0] is least; arrayOfIndex[1] is most
		int[] arrayOfIndex = traceSearchLeastAndMostExpensive(publicTransports);
		System.out.println("The least expensive item is at index " + arrayOfIndex[0] + ": " + publicTransports[arrayOfIndex[0]]);
		System.out.println("The most  expensive item is at index " + arrayOfIndex[1] + ": " + publicTransports[arrayOfIndex[1]]);

		// Using copyCityBus()
		System.out.println("*** Using copyCityBus() ***");
		PublicTransport[] publicTransport_copy = copyCityBus(publicTransports);
		printingArrayObjects(publicTransport_copy);

		// Specifying whether the copyCityBus is correct
		System.out.println("The copy contains copies of all CityBusses (including derived classes Metro and Tram)" +
				"\nBy inspecting the copies, I came to the conclusion that the array has been correctly copied even without the help of Polymorphism via .clone()" +
				"\nThis is due to a check for the class of the objects, which is the only way I could have implemented the call to the copy constructor of \n" +
				"all the listed classes as well as the correctly implemented copy constructors that called the super's copy constructor.\n\n");

		// Signal end of program
		System.out.println("End of Program");
	}

	/**
	 * Used for testing code inside main
	 */
	public static void debug_main() {

		// Testing constructors
		debug_constructorAndToString();

		// Testing equals
		debug_equals();

		// Testing random object generator
		debug_randomObjects();

		// Testing copy Constructor
		debug_copyConstructor();
	}

	/**
	 * Used in debugging constructors and toString
	 */
	private static void debug_constructorAndToString() {
		System.out.println("** Testing constructors and toString **");

		// Aircraft @DONE
		System.out.println("Testing Aicraft class");
		System.out.println("\tDefault: " + new Aircraft());
		System.out.println("\tParametized: " + new Aircraft(12.34, 3, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly));

		// Ferry @DONE
		System.out.println("Testing Ferry class");
		System.out.println("\tDefault: " + new Ferry());
		System.out.println("\tParametized" + new Ferry(12.00, 2, 1991, "Laurent"));

		// City Bus @DONE
		System.out.println("Testing CityBus class");
		System.out.println("\tDefault: " + new CityBus());
		System.out.println("\tParametized" + new CityBus(11.00, 1, 13, 1991, "Grand", "Steven"));

		// Tram @DONE
		System.out.println("Testing Tram class");
		System.out.println("\tDefault: " + new Tram());
		System.out.println("\tParametized" + new Tram(10.00, 2, 14, 1990, "Foo", "Mick", 100));

		// Metro @DONE
		System.out.println("Testing Metro class");
		System.out.println("\tDefault: " + new Metro());
		System.out.println("\tParametized" + new Metro(9.00, 3, 15, 1989, "Bar", "Arthur", 8, "Montreal"));
	}

	/**
	 * Used in debugging equality
	 */
	private static void debug_equals() {
		System.out.println("** Testing equals() **");

		// Aircraft @DONE
		System.out.println("Testing Aircraft class");
		Aircraft aircraft1 = new Aircraft(12.34, 3, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly);
		Aircraft aircraft2 = new Aircraft(12.34, 3, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly);
		Aircraft aircraft3 = new Aircraft(12.30, 3, Aircraft.Class.Helicopter, Aircraft.Maintenance.Monthly);
		Aircraft aircraft4 = null;
		System.out.println("These should be equal: " + aircraft1.equals(aircraft2));
		System.out.println("These should be not equal: " + aircraft1.equals(aircraft3));
		System.out.println("Handles null (should be false): " + aircraft1.equals(aircraft4));

		// Ferry @DONE
		System.out.println("Testing Ferry class");
		Ferry ferry1 = new Ferry(12.00, 2, 1991, "Laurent");
		Ferry ferry2 = new Ferry(12.00, 2, 1991, "Laurent");
		Ferry ferry3 = new Ferry(12.01, 2, 1991, "Laurent");
		Ferry ferry4 = null;
		System.out.println("These should be equal: " + ferry1.equals(ferry2));
		System.out.println("These should be not equal: " + ferry1.equals(ferry3));
		System.out.println("Handles null (should be false): " + ferry1.equals(ferry4));

		// City bus @DONE
		System.out.println("Testing CityBus class");
		CityBus bus1 = new CityBus(11.00, 1, 13, 1991, "Grand", "Steven");
		CityBus bus2 = new CityBus(11.00, 1, 13, 1991, "Grand", "Steven");
		CityBus bus3 = new CityBus(11.00, 1, 13, 1991, "Hehe", "Steven");
		CityBus bus4 = null;
		System.out.println("These should be equal: " + bus1.equals(bus2));
		System.out.println("These should be not equal: " + bus1.equals(bus3));
		System.out.println("Handles null (should be false): " + bus1.equals(bus4));

		// Tram @DONE
		System.out.println("Testing Tram class");
		Tram tram1 = new Tram(10.00, 2, 14, 1990, "Foo", "Mick", 100);
		Tram tram2 = new Tram(10.00, 2, 14, 1990, "Foo", "Mick", 100);
		Tram tram3 = new Tram(10.00, 3, 14, 1990, "Foo", "Mick", 100);
		Tram tram4 = null;
		System.out.println("These should be equal: " + tram1.equals(tram2));
		System.out.println("These should be not equal: " + tram1.equals(tram3));
		System.out.println("Handles null (should be false): " + tram1.equals(tram4));

		// Metro @DONE
		System.out.println("Testing Metro class");
		Metro metro1 = new Metro(9.00, 3, 15, 1989, "Bar", "Arthur", 8, "Montreal");
		Metro metro2 = new Metro(9.00, 3, 15, 1989, "Bar", "Arthur", 8, "Montreal");
		Metro metro3 = new Metro(9.00, 3, 15, 1979, "Bar", "Arthur", 8, "Montreal");
		Metro metro4 = null;
		System.out.println("These should be equal: " + metro1.equals(metro2));
		System.out.println("These should be not equal: " + metro1.equals(metro3));
		System.out.println("Handles null (should be false): " + metro1.equals(metro4));
	}

	/**
	 * Used in debugging random object generators
	 */
	private static void debug_randomObjects() {

		// Prints randomly generated objects
		for (int i = 0; i < 10; i++)
		{
			System.out.println("**** Generating all objects #" + (i + 1) + " ****");

			System.out.println("Generating a random Aircraft: " + getRandomAircraft());
			System.out.println("Generating a random Ferry: " + getRandomFerry());
			System.out.println("Generating a random City Bus: " + getRandomCityBus());
			System.out.println("Generating a random Tram: " + getRandomTram());
			System.out.println("Generating a random Metro: " + getRandomMetro());
		}
	}

	/**
	 * Used in debugging copy constructor
	 */
	private static void debug_copyConstructor() {
		System.out.println("*** Copy Constructor Debugging ***");

		PublicTransport publicTransport = getRandomPublicTransport();
		Aircraft        aircraft        = getRandomAircraft();
		Ferry           ferry           = getRandomFerry();
		CityBus         cityBus         = getRandomCityBus();
		Tram            tram            = getRandomTram();
		Metro           metro           = getRandomMetro();

		System.out.println(publicTransport + "\n" + new PublicTransport(publicTransport));
		System.out.println(aircraft + "\n" + new Aircraft(aircraft));
		System.out.println(ferry + "\n" + new Ferry(ferry));
		System.out.println(cityBus + "\n" + new CityBus(cityBus));
		System.out.println(tram + "\n" + new Tram(tram));
		System.out.println(metro + "\n" + new Metro(metro));
	}

	// End of code testers

	/**
	 * Fills an array with random Objects from the 6 classes, ensures that the first 6 objects are at least one of each
	 *
	 * @param array array of PublicTransport to be filled
	 */
	public static void fillArrayPublicTransport(PublicTransport[] array) {
		fillArrayPublicTransport(array, 0);
	}

	/**
	 * Fills an array with random Objects from the 6 classes, ensures that the first 6 objects are at least one of each
	 *
	 * @param array         array of PublicTransport to be filled
	 * @param startingIndex the index from which to start (in case the array was manually filled before)
	 */
	public static void fillArrayPublicTransport(PublicTransport[] array, int startingIndex) {
		for (int i = startingIndex; i < array.length; i++)
		{
			int choice;

			if (i < 6)
			{
				choice = i;
			}
			else
			{
				choice = getRandomInt(6);
			}

			System.out.println("At Index " + i);

			switch (choice)
			{
				case 0:
					System.out.println("Generating a random Public Transport...");
					array[i] = getRandomPublicTransport();
					break;
				case 1:
					System.out.println("Generating a random Aircraft...");
					array[i] = getRandomAircraft();
					break;
				case 2:
					System.out.println("Generating a random Ferry...");
					array[i] = getRandomFerry();
					break;
				case 3:
					System.out.println("Generating a random City Bus...");
					array[i] = getRandomCityBus();
					break;
				case 4:
					System.out.println("Generating a random Tram...");
					array[i] = getRandomTram();
					break;
				case 5:
					System.out.println("Generating a random Metro...");
					array[i] = getRandomMetro();
					break;
			}
		}
	}

	/**
	 * Prints the array of PublicTransport
	 *
	 * @param array the array of PublicTransport to be printed
	 */
	public static void printingArrayObjects(PublicTransport[] array) {
		System.out.println("\n*** Printing the array of " + array.length + " objects ***");
		for (int i = 0; i < array.length; i++)
		{
			// Prettify
			String formattedIndex = "\tIndex " + i;
			if (i < 10)
			{
				formattedIndex += " ";
			}
			formattedIndex += ": ";

			// Printing
			System.out.println(formattedIndex + array[i]);
		}

		System.out.println("*****************\n");
	}

	/**
	 * Finds the index of the most and least expensive tickets
	 *
	 * @param array Array of PublicTranport containing to be searched
	 *
	 * @return int array of length 2, array[0] = least expensive, array[1] = most expensive
	 */
	public static int[] traceSearchLeastAndMostExpensive(PublicTransport[] array) {
		int    least_index = 0;
		double least_price = 1000000;
		int    most_index  = 0;
		double most_price  = 0;

		for (int i = 0; i < array.length; i++)
		{
			// Grab the price
			double price = array[i].getTicketPrice();

			// If the price is the lowest so far, store the price and the index
			if (price < least_price)
			{
				least_price = price;
				least_index = i;
			}

			// If the price is the highest so far, store the price and the index
			if (price > most_price)
			{
				most_price = price;
				most_index = i;
			}

		}
		// Return the array containing the information
		int[] indexArray = new int[] {least_index, most_index};
		return indexArray;
	}

	/**
	 * Generates a random number with a maximum
	 *
	 * @param exclusive_maximum an integer containing the maximum of the random number (exclusive)
	 *
	 * @return random integer between 0 and exclusive_maximum (exclusive)
	 */
	public static int getRandomInt(int exclusive_maximum) {

		return new Random().nextInt(exclusive_maximum);
	}

	/**
	 * HACK: Uses the getRandomInt to create a random price
	 *
	 * @return a price of maximum 5 digits (999.99) and minimum of 2 digits (0.99)
	 */
	public static double getRandomPrice() {

		double price = 0.00;

		while (price < 0.99)
		{
			// reset price
			price = 0.00;
			double priceIndex = 0.01;

			// Generate the double using one digit at a time
			for (int maximumDigits = getRandomInt(4) + 2; maximumDigits > 0; maximumDigits--)
			{
				// Gets a digit between 0 and 9 (inclusive)
				price += priceIndex * getRandomInt(10);

				// Go to next digit of the double
				priceIndex *= 10;

			}
		}

		// HACK: Clean up the double to 2 digits after decimal place using casting
		int priceCast = (int) (price * 100);
		price = priceCast / 100.0;

		return price;
	}

	/**
	 * Generate a random year using a max Age
	 *
	 * @param maxAge an integer containing the maximum Age
	 *
	 * @return an integer representing a year
	 */
	public static int getRandomYear(int maxAge) {
		int yearNow = 2019;

		return yearNow - getRandomInt(maxAge);
	}

	/**
	 * Generate a random PublicTransport object
	 *
	 * @return a PublicTransport object
	 */
	public static PublicTransport getRandomPublicTransport() {
		return new PublicTransport(getRandomPrice(), getRandomInt(11) + 1);
	}

	/**
	 * Generate a random Aircraft object
	 *
	 * @return an Aircraft object
	 */
	public static Aircraft getRandomAircraft() {
		// Get a randomClass
		Aircraft.Class randomClass = Aircraft.Class.values()[getRandomInt(Aircraft.Class.values().length)];

		// Get a randomMaintenanceSchedule
		Aircraft.Maintenance randomMaintenance = Aircraft.Maintenance.values()[getRandomInt(Aircraft.Maintenance.values().length)];

		return new Aircraft(getRandomPrice(), getRandomInt(10) + 1, randomClass, randomMaintenance);
	}

	/**
	 * Generate a random Ferry object
	 *
	 * @return a Ferry object
	 */
	public static Ferry getRandomFerry() {

		return new Ferry(getRandomPrice(), getRandomInt(10) + 1, getRandomYear(120), listOfNames[getRandomInt(listOfNames.length)]);
	}

	/**
	 * Generate a CityBus object
	 *
	 * @return a CityBus object
	 */
	public static CityBus getRandomCityBus() {

		long routeNumber = getRandomInt(500) + 1;

		return new CityBus(getRandomPrice(), getRandomInt(100) + 1, routeNumber, getRandomYear(120), getLineName(routeNumber), listOfNames[getRandomInt(listOfNames.length)]);
	}

	/**
	 * Create a line name according to the route number
	 *
	 * @param routeNumber a long between 0 and 500 (exclusive) that corresponds to the route number
	 *
	 * @return a random line name with the suffix corresponding to the route Number
	 */
	private static String getLineName(long routeNumber) {
		String lineName = listOfNames[getRandomInt(listOfNames.length)] + " ";

		if (routeNumber < 100)
		{
			lineName += "Street";
		}
		else if (routeNumber < 200)
		{
			lineName += "Avenue";
		}
		else if (routeNumber < 300)
		{
			lineName += "Boulevard";
		}
		else if (routeNumber < 400)
		{
			lineName += "Night";
		}
		else
		{
			lineName += "Express";
		}
		return lineName;
	}

	/**
	 * Generates a random Tram object
	 *
	 * @return a Tram object
	 */
	public static Tram getRandomTram() {
		long routeNumber = getRandomInt(500) + 1;

		return new Tram(getRandomPrice(), getRandomInt(100) + 1, routeNumber, getRandomYear(120), getLineName(routeNumber), listOfNames[getRandomInt(listOfNames.length)], getRandomInt(100) + 50);
	}

	/**
	 * Generates a random Metro object
	 *
	 * @return a Metro object
	 */
	public static Metro getRandomMetro() {
		long routeNumber = getRandomInt(500);

		return new Metro(getRandomPrice(), getRandomInt(30) + 1, getRandomInt(9) + 1, getRandomYear(70), listOfColours[getRandomInt(listOfColours.length)], listOfNames[getRandomInt(listOfNames.length)], (getRandomInt(6) + 5), listOfCities[getRandomInt(listOfCities.length)]);
	}
}
