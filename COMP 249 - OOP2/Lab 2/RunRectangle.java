import java.util.Scanner;

public class RunRectangle {

	public static void main(String[] args)
	{
		// Creating scanner object
		Scanner keyIn = new Scanner(System.in);
		
		// Prompt user for number of rectangles they want to input
		System.out.print("How many rectangles do you want to input?: ");
		int numberOfRectangles = keyIn.nextInt();
		
		// Make rectangles
		Rectangle [] rectangles = new Rectangle[numberOfRectangles];
		for (int i = 0; i < numberOfRectangles; i++)
		{
			System.out.println("Creating Rectangle #" + (i + 1) + ":");
			System.out.print("\tInput the width: ");
			double width = keyIn.nextDouble();
			System.out.print("\tInput the height: ");
			double height = keyIn.nextDouble();
			
			// construct the rectangle
			rectangles[i] = new Rectangle(width, height);
		}
		
		boolean isDone = false;
		
		do {
			// Testing toString, constructors and getArea
			// Print rectangles
			System.out.println("\n\n\nListing all your rectangles:");
			for (int i = 0; i < numberOfRectangles; i++)
			{
				System.out.println("Rectangle #" + (i+1) + ": " + rectangles[i]);
			}
			
			// Testing accessors and mutators
			// Prompt if want to change information on some rectangles
			
			System.out.println("\nWant to change the information of one of the rectangles? (1 for yes, 0 for no)");
			int change = keyIn.nextInt();
			
			switch(change)
			{
				case 0:
					isDone = true;
					break;
				case 1:
					// Prompt which to change
					System.out.print("Change which rectangle? (Number from 1 to " + (numberOfRectangles + 1) + ") [no input checking]: ");
					int whichChange = keyIn.nextInt() - 1;
					System.out.println("Showing rectangle information:\n" + rectangles[whichChange]);
					System.out.print("Width is currently: " + rectangles[whichChange].getWidth() + "\nInput the new width: ");
					rectangles[whichChange].setWidth(keyIn.nextDouble());
					System.out.print("Height is currently: " + rectangles[whichChange].getHeight() + "\nInput the new height: ");
					rectangles[whichChange].setHeight(keyIn.nextDouble());
					System.out.println("The new values for this rectangle #" + whichChange + " are: " + rectangles[whichChange]);
					break;
				default:
					System.out.println("Wrong input. Showing all rectangles again.");
					continue;
					
			}
		} while(!isDone);
		
		// Print the sum of all areas
		System.out.println("The sum of all the rectangles is: " + Rectangle.getTotalArea(rectangles));
		
		// End of program
		System.out.println("The program has ended.");
		keyIn.close();
		
	}
}
