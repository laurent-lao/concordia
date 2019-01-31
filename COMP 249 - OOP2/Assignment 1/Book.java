/**
 * @author Laurent Lao
 * @version 1.0
 * @since 1.0
 * Name and ID: Laurent Lao 40020483
 * COMP249
 * Assignment # 1
 * Due Date: February 1st 2019
 * This Book class creates book objects containing the book's title, author, isbn and price as well as a counter for
 * books created thus far.
 */

// -----------------------------------------------------
// Assignment 1
// Question: Part 1
// Written by: Laurent Lao
// Student ID: 40020483
// -----------------------------------------------------

public class Book {

	// Attributes
	private String title;
	private String author;
	private long isbn;
	private double price;
	private static int bookCounter = 0;		// Number of books created thus far

	// Constructor

	/**
	 * Creates a default Book with default value
	 */
	public Book()
	{
		this.title = "";
		this.author = "";
		this.isbn = 0;
		this.price = 0;

		// Increment the bookCounter var
		bookCounter++;

	}

	/**
	 * Creates a Book with specified information
	 * @param title 	The title of the book
	 * @param author	The author of the book
	 * @param isbn		The isbn of the book
	 * @param price		The price of the book
	 */
	public Book(String title, String author, long isbn, double price)
	{
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;

		// Increment the bookCounter var
		bookCounter++;
	}

	/**
	 * Creates a book as a copy of another book
	 * @param book The book to be copied
	 */
	public Book(Book book)
	{
		this.title = book.title;
		this.author = book.author;
		this.isbn = book.isbn;
		this.price = book.price;

		// Increment the bookCounter var
		bookCounter++;
	}

	// Accessors & Mutators

	/**
	 * Gets the book's title
	 * @return	A string representing the book's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the book's title
	 * @param title	A string containing the book's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the book's author
	 * @return A string representing the book's author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the book's author
	 * @param author A string containing the book's author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the book's isbn
	 * @return A long integer representing the book's isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * Sets the book's isbn
	 * @param isbn A long integer containing the book's isbn
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the book's price
	 * @return A double representing the book's price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the book's price
	 * @param price A double containing the book's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	// toString

	/**
	 * Gets a formatted string representing the book's information for S.o.p()
	 * @return A string representing the book's information
	 */
	public String toString()
	{
		return ("Title: " + getTitle() + "\n" +
				"Author: " + getAuthor() + "\n" +
				"ISBN: " + getIsbn() + "\n" +
				"Price: $" + getPrice() + "\n");
	}

	// equals()

	/**
	 * Computes whether a book has the same information as another book
	 * @param other The other book to be compared against
	 * @return A boolean value representing whether the book has the same information as the other book
	 */
	public boolean equals(Book other)
	{
		boolean bolTitle = (this.title == other.title);
		boolean bolAuthor = (this.author == other.author);
		boolean bolIsbn = (this.isbn == other.isbn);
		boolean bolPrice = (this.price == other.price);

		return (bolTitle && bolAuthor && bolIsbn && bolPrice);
	}

	// Static method

	/**
	 * Finds the number of books created to date
	 * @return An int representing the number of created books to date
	 */
	public static int findNumberOfCreatedBooks()
	{
		return bookCounter;
	}
}