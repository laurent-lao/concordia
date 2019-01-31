/**
 * An inventory system using the book class
 * @author Laurent Lao
 * @version 1.0
 * @since 1.0
 * Name and ID: Laurent Lao 40020483
 * COMP249
 * Assignment # 1
 * Due Date: February 1st 2019
 * This program is a book inventory system that includes a password authentication process before adding or modifying
 * new books, as well as a search function for books of the same author and for books lower than a given price. There is
 * the added function of allowing only 3 failed password tries before returning to the menu and if the authentication
 * process is failed 4 times while in Option 1, the program quits.
 */

// -----------------------------------------------------
// Assignment 1
// Question: Part 2
// Written by: Laurent Lao
// Student ID: 40020483
// -----------------------------------------------------

import java.util.Scanner;

/**
 * Runs the Book inventory system
 */
public class Main {

	// Static variables
	public static Scanner keyIn = new Scanner(System.in);		// Scanner for everything but strings
	public static Scanner keyInStrg = new Scanner(System.in);	// Since nextInt and such does not delete the carat, using two Scanners
	public static final String CORRECT_PWD = "password";			// The correct password

    public static void main(String[] args) {

    	// Variables
		int counterFailedPwdProcess = 0;				// Stores # of failed Option 1 password
		final int MAX_NUM_OF_FAILED_PWD_TRIES = 4;

    	// Display welcome message
		displayWelcomeMsg();

		// Prompting max books and creating an array containing the books
		Book [] inventory = new Book [promptMaxBooks()];
		// System.out.println("Debug: Printing 'maxBooks' value :: " + maxBooks);
		// System.out.println("Debug: Printing 'inventory' size :: " + inventory.length);

		// Loop until program has to quit either by wrong password or option 5
		boolean quitIsTrue = false;
		while (!quitIsTrue) {

			// Display Menu
			displayMainMenu();

			// Prompt to choose an option and run that option
			// Switch Case depending on the return value of runMainMenuOption
			switch (runMainMenuOption(promptMenuSelection(true, 5), inventory))
			{
				case 0:
					// Return back to Menu
					break;
				case 1:
					// Option 5 was selected: Display exit message and quit
					displayExitMsg();
					quitIsTrue = true;
					break;
				case 2:
					// An invalid password was entered 3 times in Option 1
					// Increment failed counter and check if exceeds allotted number of tries
					counterFailedPwdProcess++;
					if (counterFailedPwdProcess == MAX_NUM_OF_FAILED_PWD_TRIES)
					{
						// If exceeded, quit program
						displayPwdTriesExceeded();
						quitIsTrue = true;
					}
					break;
				default:
					System.out.println("Unexpected switch case from runReturnVal");
					System.exit(0);
					break;

			}

		}

		// Closing scanners
		keyIn.close();
		keyInStrg.close();
    }

	/**
	 * Display a welcome message
	 */
    public static void displayWelcomeMsg()
	{
		System.out.println("Welcome to Laurent Lao's bookstore software.\n");
	}

	/**
	 * Prompts max books
	 * @return An int representing the maximum number of books
	 */
	public static int promptMaxBooks()
	{
		System.out.print("Input the maximum number of books: ");
		return keyIn.nextInt();
	}

	/**
	 * Displays the main menu
	 */
	public static void displayMainMenu()
	{
		final String MENU_MSG = "" +
				"What do you want to do?\n" +
				"\t 1.\t Enter new books (password required)\n" +
				"\t 2.\t Change information of a book (password required)\n" +
				"\t 3.\t Display all books by a specific author\n" +
				"\t 4.\t Display all books under a certain price\n" +
				"\t 5.\t Quit\n";

		System.out.print("\n" + MENU_MSG);
	}

	/**
	 * Displays a menu for modifying the book's information
	 */
	public static void displayModifyBookMenu()
	{
		final String MENU_MSG = "" +
				"What information would you like to" + "\n" +
				"change?\n" +
				"\t 1.\t author\n" +
				"\t 2.\t title\n" +
				"\t 3.\t ISBN\n" +
				"\t 4.\t price\n" +
				"\t 5.\t Quit\n";

		System.out.println("\n" + MENU_MSG);
	}

	/**
	 * Prompts user for inputs until user inputs integer between 1 and maxNumber
	 * @param isMainMenu A boolean value containing whether this prompt is for the main menu or not
	 * @param maxNumber	An int containing the maximum option choice
	 * @return An int representing the menu choice the user has inputted
	 */
	public static int promptMenuSelection(boolean isMainMenu, int maxNumber)
	{
		boolean wrongInput = true;
		int menuChoice = 0;

		// Bad choice message
		String bad = "Please input a number between 1 and " + maxNumber;

		// Different prompt msg according to isMainMenu
		String promptMsg;
		if (isMainMenu)
			promptMsg = "Please enter your choice > ";
		else
			promptMsg = "Enter your choice > ";

		while(wrongInput)
		{
			System.out.print(promptMsg);
			menuChoice = keyIn.nextInt();

			// Verify input is between 1 and maxNumber
			if (menuChoice >= 1 && menuChoice <= maxNumber)
			{
				wrongInput = false;
			}
			else
				System.out.println(bad);
		}

		return menuChoice;
	}

	/**
	 * Runs the algorithms according to the user choice in the Main menu
	 * @param choice 	An int containing the Main Menu choice of the user
	 * @param inventory	A Book array containing the inventory of books
	 * @return Either int 0, 1, 2 representing whether the code should proceed, quit or if there was a password problem
	 * in option 1, respectively.
	 */
	public static int runMainMenuOption(int choice, Book [] inventory)
	{
		// runs one of the option methods depending on the input
		// returns 0 to keep going, 1 to quit, 2 for wrong pw in Option 1
		final int 	PROCEED = 0,
					QUIT = 1,
					WRONG_PW = 2;

		switch (choice)
		{
			case 1:
				// Run Option 1 (Add books) and return value depending if PW process failed
				return (runOptionOne(inventory) ? PROCEED : WRONG_PW);
			case 2:
				// Run Option 2 (Modify books)
				runOptionTwo(inventory);
				return PROCEED;
			case 3:
				// Run Option 3 (Search author)
				runOptionThree(inventory);
				return PROCEED;
			case 4:
				// Run Option 4 (Search under price)
				runOptionFour(inventory);
				return PROCEED;
			case 5:
				// Run Option 5 (Quit)
				return QUIT;
			default:
				System.out.println("Unexpected switch case in runMainMenuOption");
				System.exit(0);
				return -1;		// to satisfy the compiler
		}
	}

	/**
	 * After password authentication, prompts for the number of books to be added and then prompt for their information
	 * @param inventory	A Book array containing the inventory of books
	 * @return A boolean value representing whether the password checking process has failed or not.
	 */
	public static boolean runOptionOne(Book [] inventory)
	{
		boolean retVal = passwordChecking();
		String newBookMsg = "\n=== Store a new book ===\n";
		String displayBookMsg = "Successfully added a book.\nBook information: ";

		// Only if password is valid, Option 1 is ran
		if (retVal)
		{
			// Prompting for number of books to be entered and checking if there is enough space
			int numOfBooksCreated = Book.findNumberOfCreatedBooks();
			int numOfBooksToAdd = promptNumberOfBooksToAdd(inventory.length, numOfBooksCreated);

			for (int counter = 0; counter < numOfBooksToAdd; counter++)
			{
				// Prompt for book information and store it into inventory
				System.out.println(newBookMsg);
				inventory[counter + numOfBooksCreated] = new Book(promptBookTitleOrAuthor(true), promptBookTitleOrAuthor(false), promptBookIsbn(), promptBookPrice());

				// Display information of the new book
				System.out.println(displayBookMsg);
				displayBook(inventory, (counter + numOfBooksCreated));
			}

			System.out.println("No more books to add. Returning to menu.");

		}

		return retVal;
	}

	/**
	 * After password authentication, prompts user for the book and the information they wish to change
	 * @param inventory A Book array containing the inventory of books
	 */
	public static void runOptionTwo(Book [] inventory)
	{
		if(passwordChecking())
		{
			// Prompt book # to be updated: if no Book objects at index, ask if wants to re-enter another book or quit this operation
			int whichBook = promptWhichBookToUpdate(inventory);
			if (whichBook == -1)
			{
				return;
			}
			else
			{
				// Display the book's information, display menu to change information, change the information, display again until quit
				displayBook(inventory, whichBook);

				boolean quitIsTrue = false;
				while (!quitIsTrue)
				{
					displayModifyBookMenu();
					quitIsTrue = modifyABook(promptMenuSelection(false, 5), inventory[whichBook]);
					displayBook(inventory, whichBook);
				}

				return;
			}
		}
		else
			// Wrong password, go back to menu
			return;
	}

	/**
	 * Searches for books of a given author
	 * @param inventory A Book array containing the inventory of books
	 */
	public static void runOptionThree(Book[] inventory)
	{
		System.out.print("\nPlease enter the name of the author you would like to search for: ");
		String author = keyInStrg.nextLine();
		boolean foundSomething = false;

		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] != null)
			{
				// if it's the same author, print the book
				if (inventory[i].getAuthor().equals(author))
				{
					// if it's the first time it finds something, display message
					if (!foundSomething)
					{
						foundSomething = true;
						System.out.println("\nDisplaying search results for author " + "\"" + author + "\": \n");
					}

					displayBook(inventory, i);
				}
			}
		}

		if (!foundSomething)
		{
			// if nothing was found
			System.out.println("\nNo book from " + "\"" + author + "\" was found in the inventory\n");
		}
	}

	/**
	 * Searches for books below a given price
	 * @param inventory A Book array containing the inventory of books
	 */
	public static void runOptionFour(Book [] inventory)
	{
		System.out.print("\nPlease enter a price: ");
		double priceUnder = keyIn.nextDouble();
		boolean foundSomething = false;

		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] != null)
			{
				if (inventory[i].getPrice() < priceUnder)
				{
					// if it's the first time it finds something, display message
					if (!foundSomething)
					{
						foundSomething = true;
						System.out.println("\nDisplaying search results for books under $" + priceUnder + ": \n");
					}

					displayBook(inventory, i);
				}
			}
		}

		if (!foundSomething)
		{
			// if nothing was found
			System.out.println("\nNo book under $" + priceUnder + " was found in the inventory.\n");
		}
	}

	/**
	 * Checks whether the password is valid for a maximum of 3 tries
	 * @return A boolean value representing whether user passed the authentication process
	 */
	public static boolean passwordChecking()
	{
		final String PWD_PROMPT = "Please enter your password: ";

		for (int counter = 1; counter <= 3; counter++)
		{
			System.out.print(PWD_PROMPT);
			if (keyInStrg.nextLine().equals(CORRECT_PWD))
			{
				// if the input password is correct, return true
				return true;
			}
		}

		// if 3 tries exceeded, return false
		return false;
	}

	/**
	 * Changes the information of a book according to user choice
	 * @param choice	An int containing the option (Information or quit) user selected
	 * @param book		A Book array containing the inventory of books
	 * @return A boolean value representing whether user has chosen to quit the menu or not
	 */
	public static boolean modifyABook(int choice, Book book)
	{
		boolean quitWasChosen = false;

		switch (choice){
			case 1:
				// Change author
				book.setAuthor(promptBookTitleOrAuthor(false));
				break;
			case 2:
				// Change title
				book.setTitle(promptBookTitleOrAuthor(true));
				break;
			case 3:
				// Change ISBN
				book.setIsbn(promptBookIsbn());
				break;
			case 4:
				// Change price
				book.setPrice(promptBookPrice());
				break;
			case 5:
				// Quit was chosen
				quitWasChosen = true;
				break;
			default:
				System.out.println("Unexpected Switch case in modifyABook");
				System.exit(0);
				break;
		}

		return quitWasChosen;
	}

	/**
	 * Prompts for the number of books to be added
	 * @param maxBooks
	 * @param numOfBooksCreated
	 * @return
	 */
	public static int promptNumberOfBooksToAdd(int maxBooks, int numOfBooksCreated)
	{
		int numOfBooksToAdd = 0;
		boolean isValid = false;
		int remainingSpace = maxBooks - numOfBooksCreated;

		String prompt = "Enter the number of books you want to create: ";
		String bad = "There is not enough spaces to add this many books (" + remainingSpace + " remaining spaces left)";

		// Keeps asking for a number of books to add that doesn't exceed the maxBooks limit
		while (!isValid)
		{
			// Prompt for a number
			System.out.print(prompt);
			numOfBooksToAdd = keyIn.nextInt();

			// Verify number is correct
			if (numOfBooksToAdd <= remainingSpace)
			{
				isValid = true;
			}
			else
			{
				System.out.println(bad);
			}
		}

		return numOfBooksToAdd;
	}

	/**
	 * Prompts for the Book's Title or Author
	 * @param isTitle A Boolean value containing whether to prompt a title or the author
	 * @return A string representing the user's title or author
	 */
	public static String promptBookTitleOrAuthor(boolean isTitle)
	{
		String message;
		message = isTitle ? "Enter the book's TITLE: " : "Enter the book's AUTHOR: ";
		System.out.print(message);

		return keyInStrg.nextLine();
	}

	/**
	 * Prompts for the Book's ISBN
	 * @return A long integer representing the book's isbn
	 */
	public static long promptBookIsbn()
	{
		String message = "Enter the book's ISBN#: ";
		System.out.print(message);

		return keyIn.nextLong();
	}

	/**
	 * Prompts for the Book's price
	 * @return A double representing the book's price
	 */
	public static double promptBookPrice()
	{
		String message = "Enter the book's PRICE: $ ";
		System.out.print(message);

		return keyIn.nextDouble();
	}

	/**
	 * Asks user which book they wish to modify
	 * @param inventory A Book array containing the inventory of books
	 * @return An int representing the book the user wishes to modify
	 */
	public static int promptWhichBookToUpdate(Book [] inventory)
	{
		// Loop until there is a book there, if not display a menu to ask if wants to quit or try another book
		boolean isValid = false;
		int bookNumber = 0;

		while(!isValid)
		{
			System.out.print("\n" + "Which book would you like to modify? (Enter a book number) > ");
			bookNumber = keyIn.nextInt();

			if (((bookNumber >= 0) && (bookNumber < inventory.length)) && (inventory[bookNumber] != null))
				isValid = true;
			else
			{
				// Inform user that there is no book there. Ask if wants to quit or wants to try another book
				System.out.println("There is no book at that location.\nWould you like to try another book? (1 for yes, 2 for no)> ");
				if (promptMenuSelection(false, 2) == 2)
					return -1;
			}
		}

		return bookNumber;
	}

	/**
	 * Prints the book's information as well as its index
	 * @param inventory A Book array containing the inventory of books
	 * @param index An int containing the index of the book
	 */
	public static void displayBook(Book [] inventory, int index)
	{
		System.out.println("Book # " + index);
		System.out.println(inventory[index]);
	}

	/**
	 * Prints the message for 4 failed authentication process in Option 1
	 */
	public static void displayPwdTriesExceeded()
	{
		final String MSG = "Program detected suspicious activities and will terminate immediately!";
		System.out.println("\n" + MSG);
	}

	/**
	 * Prints the program's exit message when it has properly quit
	 */
	public static void displayExitMsg()
	{
		final String MSG = "Thank you for using Laurent's bookstore software.\nThe program has ended.";
		System.out.println("\n" + MSG);
	}
}
