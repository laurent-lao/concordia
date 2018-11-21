/*  Author:     Laurent Lao
 *  Date:       November 21st 2018
 *  Class:      COMP 248-R
 *  Professor:  Nancy Acemian
 *  Purpose:    Creates a Player class with name and garden as attributes.
 *  Classes:    Works with LetsPlay.java. Needs Garden.java
 */

public class Player {

    // Attributes
    private String name;
    private Garden garden;

    // ** Constructor **
    public Player(String s, int size)
    {
        name = s;
        if (size == Garden.getDefaultSize())
        {
            garden = new Garden();
        }
        else
        {
            garden = new Garden(size);
        }
    }

    // ** Accessors **
    public String getName()
    {
        return name;
    }

    // ** Public Methods**

    // Counting Possible Flowers
    public int howManyFlowersPossible()
    {
        return garden.countPossibleFlowers();
    }

    // Counting Possible Trees
    public int howManyTreesPossible()
    {
        return garden.countPossibleTrees();
    }

    // Returns what is planted at that garden
    public char whatIsPlanted(int r, int c)
    {
        return garden.getInLocation(r, c);
    }

    // Plants a tree in the garden
    public boolean plantTreeInGarden(int r, int c)
    {
        if(correctSpot(r, c,"tree"))
        {
            garden.plantTree(r, c);
            return true;
        }
        else
        {
            System.out.println("Incorrect spot for tree");
            return false;
        }
    }

    // Plants a flower in the garden
    public boolean plantFlowerInGarden(int r, int c)
    {
        if(correctSpot(r, c, "flower"))
        {
            garden.plantFlower(r, c);
            return true;
        }
        else
        {
            System.out.println("Incorrect spot for flower");
            return false;
        }
    }

    // Eats the plant at location
    public void eatHere(int r, int c)
    {
        garden.removeFlower(r, c);
    }

    // Checks if Garden is full
    public boolean isGardenFull()
    {
        return garden.gardenFull();
    }

    // Checks if Garden is empty
    public boolean isGardenEmpty()
    {
        return garden.nothingIsPlanted();
    }

    // Prints the Garden
    public void showGarden()
    {
        System.out.println(garden);
    }

    // Checks if Spot is correct
    public boolean correctSpot(int r, int c, String type)
    {
        if (type.equals("tree"))
        {
            return (garden.correctBoundsForTrees(r, c) && garden.correctSpotForTrees(r, c));
        }
        else if (type.equals("flower"))
        {
            return (garden.correctBounds(r, c) && garden.correctSpotForFlower(r, c));
        }
        else
        {
            // Unexpected Error
            System.out.println("Wrong usage of correctSpot(int r, int c, String type");
            System.exit(0);
            return false;
        }
    }
} // End of Player class
