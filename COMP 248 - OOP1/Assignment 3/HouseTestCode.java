/* ----------------------------------------------------------------
 * Assignment 3 Q2 - Test Code
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This Test Code tests the House Class and all its constructors,
 * Accessors, Mutators and varying Methods. Prints the values to
 * ensure House Class was well implemented.
 -----------------------------------------------------------------*/

public class HouseTestCode {

	public static void main(String[] args)
	{
		// Welcome Message
		System.out.println("This program is a Test Code for the Class \"House\"\n");
		
		//Constructors Testing
		House house_H1 = new House();
		House house_H2 = new House(100000);
		House house_H3 = new House(4, 120000);
		House house_H4 = new House(2, 220000, "Detached");
		
		//Printing results of Constructor
		System.out.println("House H1: " + house_H1);
		System.out.println("House H2: " + house_H2);
		System.out.println("House H3: " + house_H3);
		System.out.println("House H4: " + house_H4);
		System.out.print("\n");
		
		//Accessors Method Testing Printing
		System.out.println("The housetype for House H4 is " + house_H4.getType() + ", its age is " + house_H4.getAge() + ", and it costs $" + house_H4.getCost());
		System.out.print("\n");
		
		//estimatePrice() Method Testing
		System.out.println("The estimated price of House H3 is $" + String.format("%.2f", house_H3.estimatePrice()));
		System.out.println("The estimated price of House H4 is $" + String.format("%.2f", house_H4.estimatePrice()));
		System.out.print("\n");
		
		// Mutator Method Testing
		for (int i = 1; i <= 5; i++)
		{
			System.out.print("Mutator Method: ");
			
			switch(i)
			{
			case 1:
				house_H3.setAge(5);
				System.out.println("The new age for House H3 is " + house_H3.getAge());
				break;
			case 2:
				house_H3.setType("Semi-Detached");
				System.out.println("The new housetype for House H3 is " + house_H3.getType());
				break;
			case 3:
				house_H3.setCost(240000);
				System.out.println("The new cost for House H3 is $" + house_H3.getCost());
				break;
			case 4:
				house_H3.setHouse(6, 245000);
				System.out.println("The new age for House H3 is " + house_H3.getAge() + " and its new cost is $" + house_H3.getCost());
				break;
			case 5:
				house_H3.setHouse(14, 260000, "Semi-Detached");
				System.out.println("The new housetype for House H3 is " + house_H3.getType() + ", its new age is " + house_H3.getAge() + " and its new cost is $" + house_H3.getCost() + "\n");
				break;
			default:
				// Unexpected Error
				System.out.println("Mutator Method Testing Switch badly implemented");
				System.exit(0);
				break;	
			}
		}
		
		// toString testing
		System.out.println("toString: " + house_H3 + "\n");
		
		// equals testing
		System.out.println("Houses H1 and H2 are equal is " + house_H1.equals(house_H2));
		System.out.println("Houses H1 and H4 are equal is " + house_H1.equals(house_H4) + "\n");
		
		// isLessThan testing
		System.out.println("Houses H4 is less than H3 is " + house_H4.isLessThan(house_H3) + "\n");
		
		// isGreaterThan testing
		System.out.println("Houses H1 is greater than H3 is " + house_H1.isGreaterThan(house_H3) + "\n");
		
		// Ending Program
		System.out.println("End of Program");
		
	}// End of Main Class

}
