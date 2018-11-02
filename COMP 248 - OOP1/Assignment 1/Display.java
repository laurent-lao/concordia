/* ----------------------------------------------------------------
 * Assignment 1 Q1
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This program will welcome the user, display "Lolo" in ASCII art
 * and include an exit message.
 -----------------------------------------------------------------*/
public class Display {
	public static void main(String[] args)
	{
		//Welcome Message + blank spaces
		System.out.println("Welcome! This program will display LOLO in ASCII art. \n\n");
		
		//System.out.print outputs anything inside quotation marks. Blank spaces can be used to format ASCII art
		System.out.println("LLL          OOO     LLL           OOO");
		System.out.println("LLL        OOO OOO   LLL         OOO OOO");
		System.out.println("LLL       OOO   OOO  LLL        OOO   OOO");
		System.out.println("LLL       OOO   OOO  LLL        OOO   OOO");
		System.out.println("LLLLLLLLL  OOO OOO   LLLLLLLLL   OOO OOO");
		System.out.println("LLLLLLLLL    OOO     LLLLLLLLL     OOO");
		
		//Blank spaces + Exit message
		System.out.print("\n\nCool, huh?\n--End of program--");
	}
}