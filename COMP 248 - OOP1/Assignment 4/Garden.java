/*  Author:     Laurent Lao
 *  Date:       November 21st 2018
 *  Class:      COMP 248-R
 *  Professor:  Nancy Acemian
 *  Purpose:    Create a Garden class with a garden as an attribute.
 *  Classes:    Works with Player.java
 */

public class Garden {

    // **Attributes**
    private char [][] garden;
    private static final int defaultSize = 3;

    // **Constructors**
    // Default: 3x3
    public Garden()
    {
        garden = new char[defaultSize][defaultSize];
        initializeGarden();

    }
    // With a Specified Size
    public Garden(int size)
    {
        garden = new char[size][size];
        initializeGarden();
    }

    // **Accessors**
    // Get the char located at the (r,c) location
    public char getInLocation(int r, int c)
    {
        return garden[r][c];
    }

    // Get the default size
    public static int getDefaultSize() {

        return defaultSize;
    }

    // **Mutators**
    // Plant a flower
    public void plantFlower(int r, int c)
    {
        garden[r][c] = 'f';
    }

    // Plant a tree
    public void plantTree(int r, int c)
    {
        garden[r][c] = 't';

        garden[r + 1][c] = 't';
        garden[r][c + 1] = 't';
        garden[r + 1][c + 1] = 't';
    }

    // Remove the plant
    public void removeFlower(int r, int c)
    {
        garden[r][c] = '-';
    }

    // ****Public Methods****

    // Checks if the bound is correct
    public boolean correctBounds(int r, int c)
    {
        return !(r < 0 || r >= garden.length || c < 0 || c >= garden.length);
    }

    // Checks if the bounds for trees are correct to prevent an Array Overflow
    public boolean correctBoundsForTrees(int r, int c)
    {
        return (correctBounds(r,c) && correctBounds(r + 1, c) && correctBounds(r, c + 1) && correctBounds(r + 1, c + 1));
    }

    // Checks if the chosen coordinate is empty to add a tree
    public boolean correctSpotForTrees(int r, int c)
    {
        // Checking if each spot is free
        boolean topLeft_available = (getInLocation(r, c) == '-');
        boolean topRight_available = (getInLocation(r + 1, c) == '-');
        boolean bottomLeft_available = (getInLocation(r, c + 1) == '-');
        boolean bottomRight_available = (getInLocation(r + 1, c + 1) == '-');

        return (topLeft_available && topRight_available) && (bottomLeft_available && bottomRight_available);
    }

    // Checks if the chosen coordinate is empty to add a flower
    public boolean correctSpotForFlower(int r, int c)
    {
        return getInLocation(r, c) == '-';
    }

    // Counts all the possible spots where a tree can still be planted
    public int countPossibleTrees()
    {
        int counter = 0;
        for(int i = 0; i < garden.length; i++)
        {
            for(int j = 0; j < garden.length; j++)
            {
                // If the bounds are incorrect, skip that iteration
                if(!correctBoundsForTrees(i,j))
                    continue;
                else
                {
                    // If that spot can be used for a tree, count, otherwise, skip that iteration
                    if(correctSpotForTrees(i,j))
                    {
                        counter++;
                    }
                    else
                        continue;
                }
            }
        }

        return counter;
    }

    // Counts all the possible spots where a flower can still be planted
    public int countPossibleFlowers()
    {
        int counter = 0;
        for(int i = 0; i < garden.length; i++)
        {
            for(int j = 0; j < garden.length; j ++)
            {
                if(correctSpotForFlower(i,j))
                {
                    counter++;
                }
            }
        }

        return counter;
    }

    // Checks if the Garden is Full
    public boolean gardenFull()
    {
        for (int i = 0; i < garden.length; i++)
        {
            for (int j = 0; j < garden.length; j++)
            {
                // If the method finds a '-', returns false and end method
                if(garden[i][j] == '-')
                {
                    return false;
                }
            }
        }

        // If the method doesn't find any '-', then it returns true
        return true;
    }

    // Checks if nothing is planted
    public boolean nothingIsPlanted()
    {
        for (int i = 0; i < garden.length; i++)
        {
            for (int j = 0; j < garden.length; j++)
            {
                if(getInLocation(i, j) != '-')
                {
                    return false;
                }
            }
        }

        return true;
    }

    // Prints the Garden
    public String toString()
    {
        String toBePrinted = "";                                        // Stores string to be printed

        // Generates the garden
        for (int i = 0; i < garden.length; i++)
        {
            for (int j = 0; j < garden.length; j++)
            {
                // Generating first row label
                if (i == 0 && j == 0)
                {
                    toBePrinted += "\t" + "|";
                    for (int n = 0; n < garden.length; n++)
                    {
                        toBePrinted += ("\t" + n);
                    }

                    toBePrinted += "\n";
                }

                // Generating each row
                // First colum label
                if (j == 0)
                {
                    toBePrinted += (i + "\t" + "|");
                }

                // Generating Elements
                toBePrinted += ("\t" + garden[i][j]);

            }

            // Formatting
            toBePrinted += "\n";
        }

        // Returns the Generated String
        return toBePrinted;
    }

    // ****Private Methods****

    // Initializes the Garden with '-'
    private void initializeGarden()
    {
        for (int i = 0; i < garden.length; i++)
        {
            for (int j = 0; j < garden.length; j++)
            {
                removeFlower(i, j);
            }
        }
    }

} // End of Garden Class
