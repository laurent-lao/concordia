/* ----------------------------------------------------------------
 * Assignment 1 Q2
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This program will welcome the user, prompt the user for their
 * weight(kg) and height(m), calculate their body mass index
 * and finally, it will exit, displaying a message.
 -----------------------------------------------------------------*/
import java.util.Scanner;

public class BMI_Calc {
	public static void main(String[] args)
	{
		//declaring and initializing variables
		float mass = 0, height = 0;
		double bmi = 0;
		Scanner keyIn = new Scanner(System.in);
		
		//Welcome message + blank space
		System.out.println("======= Welcome to the BMI calculator =======\n\n");
		
		//Prompt user for weight
		System.out.println("Please enter your weight in kilograms:");
		mass = keyIn.nextFloat();
		
		//Prompt user for height
		System.out.println("Please enter your height in meters:");
		height = keyIn.nextFloat();
		
		//Calculate bmi
		bmi = mass / (height * height);
		
		//Displaying the results
		System.out.print(
				"\n\n\t------- RESULTS -------\n\n\n"
				+ "Your weight:\t\t" + mass + "\n"
				+ "Your height:\t\t" + height + "\n"
				+ "\n\n"
				+ "Your BMI is:\t--> " + bmi + " <--\n\n"
				);
		
		
		//Display exit message
		System.out.println(
				"\n=============================================\n"
				+ "\t\tStay healthy!\n\n"
				+ "--End of Program--"
				);
		
		//Prevent memory leak
		keyIn.close();
	}
}
