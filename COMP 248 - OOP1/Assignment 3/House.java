/* ----------------------------------------------------------------
 * Assignment 3 Q2 - Class
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This Class contains 3 Accessors, 4 constructors and 5 Mutators of
 * varying parameters, a method named estimatePrice() that estimates
 * a house price from its age and housetype, a toString and equals
 * methods as well as additional isLessThan, isGreaterThan methods.
 * 
 *  This Class is tested with HouseTestCode
 -----------------------------------------------------------------*/

public class House {
	
	private int age;
	private String type;
	private double cost;
	
	// == Constructor lists ==
	
	public House()
	{
		age = 50;
		type = "Attached";
		cost = 100000;
	}
	
	public House(double newCost)
	{
		setHouse(50, newCost, "Attached");
	}
	
	public House(int newAge, double newCost)
	{
		setHouse(newAge, newCost, "Attached");
	}
	
	public House(int newAge, int newCost, String newType)
	{
		setHouse(newAge, newCost, newType);
	}

	// == Mutators list ==
	
	public void setAge(int newAge)
	{
		age = newAge;
	}
	
	public void setCost(double newCost)
	{
		cost = newCost;
	}
	
	public void setType(String newType)
	{
		// Validates input
		if (!newType.equalsIgnoreCase("Detached") && !newType.equalsIgnoreCase("Semi-Detached") && !newType.equalsIgnoreCase("Attached"))
		{
			// Error
			System.out.print("Type is invalid in setType");
			System.out.print("Type entered is " + newType);
			System.exit(0);
		}
		// Assign the proper case for each type
		else if (newType.equalsIgnoreCase("Detached"))
		{
			type = "Detached";
		}
		else if (newType.equalsIgnoreCase("Semi-Detached"))
		{
			type = "Semi-Detached";
		}
		else if (newType.equalsIgnoreCase("Attached"))
		{
			type = "Attached";
		}
		else
		{
			// Debug helper error
			System.out.print("Unexpected error in setType");
			System.exit(0);
		}
	}
	
	public void setHouse(int newAge, double newCost)
	{
		setAge(newAge);
		setCost(newCost);
	}
	
	public void setHouse(int newAge, double newCost, String newType)
	{
		setAge(newAge);
		setCost(newCost);
		setType(newType);
	}
	
	// == List of Accessors Methods ==
	
	public int getAge()
	{
		return age;
	}
	
	public double getCost()
	{
		return cost;
	}
	
	public String getType()
	{
		return type;
	}
	
	// == Miscellaneous Methods ==
	
	public double estimatePrice()
	{
		// Defining variables
		double newCost;						// Is Assigned a starting value and incremented according to housetype and age
		boolean isAttached = false;			// Stores if house is attached
		boolean isSemiDetached = false;		// Stores if house is semi-detached
		boolean isDetached = false;			// Stores if house is detached
		
		// Starting costs according to type
		if (isAttached = (type == "Attached"))
		{
			newCost = 100000;
		}
		else if (isSemiDetached = (type == "Semi-Detached"))
		{
			newCost = 150000;
		}
		else if (isDetached = (type == "Detached"))
		{
			newCost = 200000;
		}
		else
		{
			// Unexpected Error
			System.out.println("There is an error in the type evaluation of method estimatePrice");
			System.exit(0);
			return 0;
		}
		
		// House Appreciation calculator
		for (int i = 1; i <= age; i++)
		{
			if (i <= 5)
			{
				if (isAttached)
					newCost *= 1.01;
				else if (isSemiDetached)
					newCost *= 1.02;
				else if (isDetached)
					newCost *= 1.02;
			}
			else
			{
				if (isAttached)
					newCost *= 1.02;
				else if (isSemiDetached)
					newCost *= 1.03;
				else if (isDetached)
					newCost *= 1.02;
			}
		}
		
		// Returns calculated value
		return newCost;
	}
	
	// == isLessThan() & isGreaterThan() ==
	public boolean isLessThan(House toCheckAgainst)
	{
		return (cost < toCheckAgainst.cost);
	}
	
	public boolean isGreaterThan(House toCheckAgainst)
	{
		return (cost > toCheckAgainst.cost);
	}
	
	// == Java expected methods ==
	
	public boolean equals(House toCheckAgainst)
	{
		return (age == toCheckAgainst.age && cost == toCheckAgainst.cost && type.equals(toCheckAgainst.type));
	}
	
	public String toString()
	{
		return "This House is type " + type + ". Its age is " + age + " and costs $" + cost;
	}
	
}