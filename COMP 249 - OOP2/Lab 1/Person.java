
public class Person {
	
	// attributes
	private String name;
	private int age;
	private char gender;
	
	// default constructor
	public Person()
	{
		name = null;
		format();
		age = 0;
		gender = '-';
	}
	
	// parameters constructor
	public Person(String name, int age, char gender)
	{
		this.name = name;
		this.format();
		this.age = age;
		this.gender = gender;
	}
	
	// copy constructor
	public Person(Person person)
	{
		name = person.name;
		age = person.age;
		gender = person.gender;
	}

	// mutator and accessor methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		format();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	// equals & toString
	public boolean equals(Person person)
	{
		boolean sameName = name.equals(person.name);
		boolean sameAge = age == person.age;
		boolean sameGender = gender == person.gender;
		
		return (sameName && sameAge && sameGender);
	}
	
	public String toString()
	{
		return ("This person's name: " + name + " age: " + age + " gender: " + gender);
	}
	// format method
	public void format()
	{
		if (name != null)
		{
		// Upper the first letter
		String firstLetter = name.substring(0, 1);
		firstLetter = firstLetter.toUpperCase();
		
		// Lower all other letters
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();
		
		this.name = (firstLetter + otherLetters);
		}
		
	}
	
}
