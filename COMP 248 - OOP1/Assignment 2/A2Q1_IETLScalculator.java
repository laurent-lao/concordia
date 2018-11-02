/* ----------------------------------------------------------------
 * Assignment 2 Q1
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This program will welcome the user, (shows info about IETLS) or (prompt
 * the user for their scores in Listening, Reading, Writing and Speaking
 * and get the average score and round it. Then, a message will show the
 * program's assessment of their scores) and display an exit message.
 * We are assuming a perfect user.
 -----------------------------------------------------------------*/
import java.util.Scanner;

public class A2Q1_IETLScalculator {
	
	public static void main(String[] args) {
		
		//Start Scanner
		Scanner keyInput = new Scanner(System.in);
		
		//Lower and Upper bound constants
		//For rounding
		final float ROUND_LOWER = 0.25f; //lower bound for rounding
		final float ROUND_UPPER = 0.75f; //upper bound for rounding
		//For score checking
		final float BAD_OVERALL = 6f; //lower bound for overall score
		final float GOOD_OVERALL = 6.5f; //upper bound for overall score
		final float ACCEPTABLE_RW = 6f; //upper bound for reading and writing score
		
		//Welcome message TODO
		System.out.println(""
							+ "    -------****-------****-------****-------****-----****-----" 	+ "\n"
							+ "       Welcome to Concordia Language Proficiency Evaluator!" 	+ "\n"
							+ "                      based on IELTS exam" 						+ "\n"
							+ "    -------****-------****-------****-------****-----****-----"
							+ "\n");
		
		/* Menu options and prompt users
		 * 1 - Display information
		 * 2 - Run calculator */
		System.out.print("\n"
				+ "Here are the available options:" + "\n"
				+ "\t" + "1 - Language Proficiency Requirements for Applicant" + "\n"
				+ "\t" + "2 - Evaluation of your language proficienciy" + "\n"
				+ "\n\n");
		System.out.print("* Enter the digit corresponding to your case: ");
		int menuOption = keyInput.nextInt(); //menu option input
		
		//Assuming a perfect user
		switch (menuOption)
		{
			case 1: //Display information
					System.out.println("\n\n"
							+ "- \tThe overall score of IELTS exam of applicants needs to be equal or above 6.5" + "\n"
							+ "\tand the scores for writing and reading skills should not be below 6.0. " + "\n\n"
							+ "- \tIf your overall score is 6, and your reading and writing scores are at least 6, " + "\n"
							+ "\tyou will be eligible for conditional admission. " + "\n\n"
							+ "\tIn that case, you will need to take an English course in the first semester." + "\n"
							+ "- \tOtherwise you have to retake the IELTS exam." + "\n\n"
							+ "Thanks for choosing Concordia" + "\n\n");
					break;
			case 2:	//Run calculator
					
					//Prompt user for Listening, Speaking, Reading and Writing scores
					//assuming a perfect user
					System.out.print("\n* Please enter your Listening score: ");
					float listening = keyInput.nextFloat(); //listening score
					System.out.print("\n* Please enter your Speaking score: ");
					float speaking = keyInput.nextFloat(); //speaking score
					System.out.print("\n* Please enter your Reading score: ");
					float reading = keyInput.nextFloat(); //reading score
					System.out.print("\n* Please enter your Writing score: ");
					float writing = keyInput.nextFloat(); //writing score
					
					//calculate the average and round
					float average = (listening + speaking + reading + writing)/4; //average
					float floatingValue = average - (int) average; //the numbers after the period
					
					//if floatingValue is 0 or 1 then average is already rounded
					if (floatingValue != 0 || floatingValue == 1)
					{
						if (floatingValue < ROUND_LOWER)
							
							//round to nearest integer
							average = (int) average;
						else if (floatingValue >= ROUND_LOWER && floatingValue < ROUND_UPPER)
							
							//round to 0.5 if between 0.25 inclusive and 0.75
							average = 0.5f + (int) average;
						else
							
							//add 1 (between 0.75 inclusive and 1)
							average = 1 + (int) average;
					}
					
					//Evaluating scores
					String evaluation = "";
					if (average >= GOOD_OVERALL && (reading >= ACCEPTABLE_RW && writing >= ACCEPTABLE_RW))

						//average score is greater or equal to 6.5 and both reading and writing are greater or equal to 6
						evaluation = "Congratulations! You are eligible for Admission.";
					else if ( (average >= GOOD_OVERALL && (reading < ACCEPTABLE_RW || writing < ACCEPTABLE_RW))
								|| (average == BAD_OVERALL && (reading >= ACCEPTABLE_RW && writing >= ACCEPTABLE_RW)))

						//average score is greater or equal to 6.5 and either reading and writing are under 6
						//or average score is equal 6 and both reading and writing are over 6
						evaluation = "You are eligible for Conditional Admission.\n\tYou will have to take an English class during your first semester.";
					else

						//any other case
						evaluation = "You must retake the IETLS exam.";
					
					//Printing results
					System.out.println("\n\n\t\t---Your results---\n");
					System.out.println("\tYour overall score is : " + average);
					System.out.println("\tYour reading score is : " + reading);
					System.out.println("\tYour writing score is : " + writing);
					System.out.println("\n\n\t" + evaluation + "\n\n");
					break;
					
			//default: System.out.println("Error! Choose either 1 or 2"); --> assuming perfect user
		};
		
		//ending program
		System.out.println("The program has ended.");
		keyInput.close();

	}
}
