/*  Author:     Laurent Lao
 *  Date:       November 21st 2018
 *  Class:      COMP 248-R
 *  Professor:  Nancy Acemian
 *  Purpose:    Create a Dice class with each die as an attribute.
 *  Classes:    Works with LetsPlay.java
 */

import java.util.Random;

public class Dice {

    // Attributes
    private int die1;
    private int die2;
    private Random random = new Random();

    // Constructor with both dice at 0
    public Dice()
    {
        die1 = 0;
        die2 = 0;
    }

    // rollDice Method
    // Randomizes dice and returns sum of dice
    public int rollDice()
    {
        die1 = random.nextInt(6) + 1;
        die2 = random.nextInt(6) + 1;

        return (die1 + die2);
    }

    // Accessors to get die 1 and die 2
    public int getDie1()
    {
        return die1;
    }
    public int getDie2()
    {
        return die2;
    }

    // Returns a string containing the result of the two dice
    public String toString() {
        return "(Die 1: [" + getDie1() + "] Die 2: [" + getDie2() + "])";
    }
}
