/* ----------------------------------------------------------------
 * Assignment 2 Q2
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This program will welcome the user, prompt the user for an integer
 * of at most 7 digits, calculate the sum of its digits, provide the
 * sum's factor and prompt the user if they would like to use another
 * number. Finally, it display an exit message.
 * We are assuming a perfect user.
 -----------------------------------------------------------------*/
import java.util.Scanner;

public class A2Q2_Calculations {

	public static void main(String[] args)
	{
		//define variables
		final int TEN = 10; //constant for division and modulo
		String continuePrompt = ""; //stores continue value
		boolean wantsToContinue = true; //1 if yes, -1 if no, 0 if nothing
		Scanner keyInput = new Scanner(System.in); //key inputs
	
		//welcome message
		System.out.println("========= Welcome to the Calculator 2000 =========");
		
		//looping calculator
		do
		{	
			//local variables
			int sum = 0; //set and reset sum of digits
			
			//prompt user for a 7-digit integer
			//assuming a perfect user
			System.out.print("\n\n\n* Enter a number with at most 7-digits: ");
			int number = keyInput.nextInt(); //user input
			
			//Sum the digits together and display the result
			System.out.print("\n\nSum of the digits of " + number + " is: ");
			while(number != 0)
			{
				//loop get digit, add to sum and go to the next one until number = 0
				sum += number % TEN;
				number /= TEN;
			};
			System.out.println(sum + "\n");
			
			//Display and calculate the factors of the sum
			System.out.println("The divisors of " + sum + " are as follows:");
			
			//Find the factors of sum and print them
			for (int factor = 1; factor <= sum; factor++)
			{
				//increments factor until sum, checks if it is a factor every loop
				//System.out.println("\nWe are at factor = " + factor); //#debug
				if (sum % factor == 0)
					System.out.print(factor + " ");
			};

			//Wants to continue? Verify if YES or NO and stays true until NO.
			boolean valid; //stores if input is valid or not
			System.out.print("\n\n\n");
			do
			{	
				//prompts & verifies and loop if need be
				valid = true;
				System.out.print("* Do you want to try another number? (\"yes\" to repeat, or \"no\" to stop): ");
				continuePrompt = keyInput.next();
				if (continuePrompt.equalsIgnoreCase("yes"));
					//wantsToContinue stays true
				else if (continuePrompt.equalsIgnoreCase("no"))
					//end continue loop	
					wantsToContinue = false;
				else
					//loop prompt for continue
					valid = false;
			} 
			while (!valid);
			
		}
		while(wantsToContinue);
		
		//end program
		System.out.println("\n\nThe program has ended.");
		keyInput.close();
	}

}
