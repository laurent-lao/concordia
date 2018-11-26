/*  Author:     Laurent Lao
 *  Date:       November 21st 2018
 *  Class:      COMP 248-R
 *  Professor:  Nancy Acemian
 *  Purpose:    Implement the "Garden Game". Shows a welcome message, then a list of rules. Afterwards, prompt players
 *              if they would like the default size or enter their own size. Prompt players for their names. The script
 *              then figures who goes first with a dice roll. Then, the game starts with each dice roll having an out-
 *              come. When a garden is filled, the game breaks and shows a winning screen with a list of all the gardens.
 *  Classes:    Needs: Garden.java, Dice.java, Player.java
 */

import java.util.Scanner;
import java.util.Random;

public class LetsPlay {

    public static void main(String[] args)
    {
        // Declaring variables
        Scanner keyInput = new Scanner(System.in);          // Reading User Input
        Scanner otherInput = new Scanner(System.in);        // Reading Strings and Enter key
        boolean gameIsWon = false;                          // Boolean check to see if someone won the game
        int winningPlayer = -1;                             // Will store the index of the winning player
        int roundCounter = 0;                               // Keeps track of the number of rounds
        boolean firstRun = true;

        // Welcome message and rules
        gameIntro();

        // Starting a match: get the garden size and number of players
        int [] matchVar = gameStart(keyInput);              // Stores garden size and player count from method
        int size = matchVar[0];                             // Assigns relevant name for value
        int numOfPlayers = matchVar[1];                     // Assigns relevant name for value

        // Create Player array and initialize each with name and chosen size
        Player [] player = new Player[numOfPlayers];        // Array of all the players
        for (int i = 0; i < numOfPlayers; i++)
        {
            player[i] = new Player(promptName(otherInput, i), size);
        }

        // Compute players turn order from the highest dice roll and show the order
        System.out.println("\nDetermining Turns...");
        player = gameDetermineTurnOrder(player);                 // Stores sorted player list
        showPlayerTurn(player);

        // Game Starts message
        System.out.println("\n\nTime to play!!!");
        System.out.println("---------------\n");

        // As long as there are no winners
        do {
            // Iterating algorithm for every player
            for (int i = 0; i < numOfPlayers; i++)
            {
                // Incrementing round counter
                roundCounter++;

                // Message variable
                String turnMessage = player[i].getName() + "'s turn:";

                //                  NOTE FOR CORRECTOR
                // **This code adds a prompt to wait for a return before continuing**
                // Show whose turn it is and wait until there is an input from the user before continuing
                System.out.println("**Round " + roundCounter + "**");
                System.out.println("\n" + turnMessage);
                printLine(turnMessage);

                gameWaitForReturn(otherInput, firstRun);
                firstRun = false;

                // Rolling the dice and translate the outcome into a (0 to 5) number
                int outcome = diceToOutcome(rollingDice(player[i].getName()));

                // Do Outcome, store results
                gameIsWon = gameDoOutcome(outcome, size, player[i], keyInput);

                // Checks if the game is won and break, otherwise, go to next iteration
                if(gameIsWon)
                {
                    winningPlayer = i;
                    break;
                }
            }
        }
        while(!gameIsWon);

        // Winning the game
        gameWinningScreen(player, player[winningPlayer].getName(), roundCounter);

        // End the program
        keyInput.close();
        otherInput.close();
        System.out.println("End of Program");

    } // End of Main Method

    /**
     * Display a message prompting user to press Enter in order to continue
     * Helps control the flow of the game
     * @param enterInput      hook for a Scanner object to allow user input
     */
    public static void gameWaitForReturn(Scanner enterInput, boolean firstRun)
    {
        if (firstRun)
        {
            System.out.print("Press Enter to Continue... (Might have to do it twice)");
        }
        else
            System.out.print("Press Enter to Continue...");

        enterInput.nextLine();
    }

    /**
     * Printing Welcome message and rules
     */
    public static void gameIntro()
    {
        // Welcome banner
        System.out.println("" +
                "\t\t********************************************************\n" +
                "\t\t*                                                      *\n" +
                "\t\t*          Welcome to Laurent's Garden Game            *\n" +
                "\t\t*                                                      *\n" +
                "\t\t********************************************************\n");

        // Show Rules

        // Intro rules
        System.out.println("" +
                "To win this game, you need some luck with the dice and a bit of strategy.\n" +
                "Your garden is an NxN lot. You can plant flowers or trees. A flower takes\n" +
                "one spot. A tree takes 4 spots (2x2). You roll the dice and based on the\n" +
                "outcome, you get the plant a pre-set number of trees and flowers.\n");

        // Rolls and their outcome
        System.out.println("" +
                "Rolls and their outcome:\n" +
                "------------------------\n" +
                "\t3: \t\t\t\t\t\t* Plant a tree (2x2) and a flower (1x1)\n" +
                "\t6: \t\t\t\t\t\t* Plant 2 flowers (2 times 1x1)\n" +
                "\t12:\t\t\t\t\t\t* Plant 2 trees (2 times 2x2)\n" +
                "\t5 or 10:\t\t\t\t* The Rabbit will eat something that you have planted:\n" +
                "\t\t\t\t\t\t\t\teither a flower or part of a tree (1x1)\n" +
                "\tAny other Even rolls:\t* Plant a tree (2x2)\n" +
                "\tAny other Odd rolls:\t* Plant a flower (1x1)\n");

        // Game rules
        System.out.println("" +
                "Game rules:\n" +
                "-----------\n" +
                "* \tMinimum number of players: \t2\n" +
                "* \tMaximum number of players: \t10\n" +
                "* \tMinimum size of garden: \t3X3\n" +
                "* You can only plant in empty locations.\n" +
                "* If no space left to plant tree(s), you lose the tree(s) to be planted\n" +
                "* Enter the top left coordinate of the tree to plant a tree\n" +
                "* A dice is rolled to determine the turn order.\n" +
                "* The game is won once someone fills their garden\n");

        // Starting the game
        System.out.println("" +
                "\n\t------------ Game Start ------------\n\n");


    }

    /**
     * Rolling Dice algorithm and printing
     * @param name      name of the player who is rolling the dice
     * @return          the total sum of both dice
     */
    public static int rollingDice(String name)
    {
        // Declaring Variables
        Dice dice = new Dice();                                     // Dice object
        int result = dice.rollDice();                               // Stores the sum of both dice

        // Printing Result and both dice
        System.out.println(name + ", you rolled " + result + " " + dice);

        // Returns Result
        return result;
    }

    /**
     * Prompts for size and numOfPlayers and return an array with the values
     * @param keyInput      hook for a Scanner object to allow user input
     * @return              int array of size 2, storing the size at [0] and number of players at [1]
     */
    public static int [] gameStart(Scanner keyInput)
    {
        int [] matchVar = new int[2];               // Creates array where size and numOfPlayers will be returned
        boolean isInputCorrect;                     // Input check switch
        int size = 0;                               // Initializing size
        int numOfPlayers = 0;                       // Initializing numOfPlayers
        boolean wantsDefault = false;               // Value for wanting default size or not

        System.out.println("Choose the size of your garden");

        // Default board size or change the size! (Enter Y or N to enter your own size)
        do{
            isInputCorrect = false;

            // Prompt the user to select between default size or input value
            System.out.print("Do you want to use the default garden size (" + Garden.getDefaultSize()
                    + "X" + Garden.getDefaultSize() + ")? [Y/N]: ");
            String yesOrNo = keyInput.next();

            if (yesOrNo.equalsIgnoreCase("Y"))
            {
                isInputCorrect = true;
                wantsDefault = true;
            }
            else if (yesOrNo.equalsIgnoreCase("N"))

            {
                isInputCorrect = true;
                wantsDefault = false;
            }
            else
            {
                isInputCorrect = false;
                System.out.println("Invalid choice. Type \"Y\" or \"N\".");
            }
        }
        while (!isInputCorrect);

        // Asks for size (at least 3) if doesn't want defaultSize
        if(!wantsDefault) {
            do {
                isInputCorrect = false;

                // Message and prompt user
                System.out.print("** Enter the size (default is " + Garden.getDefaultSize() + " , minimum is 3): ");
                int input = keyInput.nextInt();

                // Input checking
                if (input >= 3) {
                    isInputCorrect = true;
                    size = input;
                }
                else {
                    System.out.println("Bad Size! Try again!");
                }
            }
            while (!isInputCorrect);
        }
        else
        {
            // GetDefaultSize because can change default size easily
            size = Garden.getDefaultSize();
        }

        // Asks for # of players (2 to 10)
        do{
            isInputCorrect = false;

            // Message and prompt user
            System.out.print("** Enter the number of players (between 2 to 10): ");
            int input = keyInput.nextInt();

            // Input checking
            if (input >= 2 && input <= 10)
            {
                isInputCorrect = true;
                numOfPlayers = input;
            }
            else
            {
                System.out.println("Bad Number of Players! Try again!");
            }
        }
        while(!isInputCorrect);

        // Returns the data
        matchVar[0] = size;
        matchVar[1] = numOfPlayers;
        return matchVar;
    }

    /**
     * Prompts for name, using a message specifying without space
     * @param keyInput      hook for a Scanner object to allow user input
     * @param counter       used to format the prompt message as player 1, 2, 3 ...
     * @return              the name that was inputted
     */
    public static String promptName(Scanner keyInput, int counter)
    {
        String name;
        boolean isValid = false;
        // Display message and prompt for the name

        do {
            System.out.print("--> Name of player " + counter + " (no spaces allowed): ");
            name = keyInput.nextLine();

            boolean foundSpace = false;

            // Searches for spaces
            for (int i = 0; i < name.length(); i++)
            {
                if (name.charAt(i) == ' ') {
                    System.out.println("The name contains a space. Try again.");
                    foundSpace = true;
                    break;
                }
            }

            // If there are spaces, go to next iteration, otherwise, valid input.
            if (foundSpace);
            else
                isValid = true;

        } while (!isValid);


        return name;
    }

    /**
     * Determines turn order by rolling dice for every player. Highest roll starts. The rest are ordered starting from the highest roll.
     * @param unordered     an array of Player object
     * @return              sorted array of Player objects from highest to lowest dice roll
     */
    public static Player [] gameDetermineTurnOrder(Player[] unordered)
    {
        // Variables
        int [] diceRoll = new int[unordered.length];            // Stores all the dice rolls
        Player [] ordered = new Player[unordered.length];                                      // Declaring array in which sorted players will be stored
        boolean isTied = true;                                  // Tie checker switch
        int highestRoll = 0;
        int highestIndex = 0;

        // Rolls dice until no ties
        do {
            // Rolls dice for each player
            for (int i = 0; i < unordered.length; i++) {
                isTied = false;
                diceRoll[i] = rollingDice(unordered[i].getName());

                // Iterate over previous rolls to find ties
                for (int j = 0; j < i; j++)
                {
                    if (diceRoll[j] == diceRoll[i])
                    {
                        isTied = true;
                        System.out.println("\nWe will start over as " + diceRoll[i] + " was rolled by " + unordered[j].getName() + " as well.\n");
                        break;
                    }
                }

                // Tie check
                if (isTied)
                {
                    break;
                }
            }
        } while(isTied);

        // Highest starts, so find the highest roll
        for (int i = 0; i < unordered.length; i++)
        {
            if (highestRoll < diceRoll[i])
            {
                highestRoll = diceRoll[i];
                highestIndex = i;
            }
        }

        // Reorder the players. Highest roll first, and subsequent players are based on player order after highest.
        for (int i = 0; i < unordered.length; i++)
        {
            ordered[i] = unordered[(highestIndex + i) % unordered.length];
        }

        return ordered;
    }

    /**
     * Prints the player turn order
     * @param player        array of sorted Player object, by turn order
     */
    public static void showPlayerTurn(Player[] player)
    {
        String outputMessage;                               // Stores output message to be printed
        System.out.println("\n\n==== Players turn ====");   // Turn order header

        // Formats turn order list to be grammatically correct
        for (int i = 0; i < player.length; i++) {
            if (i == player.length - 1) {
                outputMessage = ("Last:\t" + player[i].getName());
            } else {
                switch (i) {
                    case 0:
                        outputMessage = ("1st:\t" + player[i].getName());
                        break;
                    case 1:
                        outputMessage = ("2nd:\t" + player[i].getName());
                        break;
                    case 2:
                        outputMessage = ("3rd:\t" + player[i].getName());
                        break;
                    default:
                        outputMessage = ((i + 1) + "th:\t" + player[i].getName());
                        break;
                }
            }

            // Prints resulting message and go to next iteration
            System.out.println(outputMessage);
        }
    }


    public static void printLine(String messageToUnderline)
    {
        String lines = "";

        for (int i = 0; i < messageToUnderline.length(); i++)
        {
            lines += "-";
        }

        System.out.println(lines);
    }

    /**
     * Translates dice result into an outcome case
     * Helps in building a streamlined switch case
     * @param dice      value of dice roll
     * @return          outcome number from 0 to 5 (inc)
     */
    private static int diceToOutcome(int dice)
    {
        int outcome;

        // Outcome translator
        if(dice == 3)
        {
            outcome = 0;
        }
        else if(dice == 6)
        {
            outcome = 1;
        }
        else if(dice == 12)
        {
            outcome = 2;
        }
        else if (dice == 5 || dice == 10)
        {
            outcome = 3;
        }
        else if ((dice % 2) == 0)
        {
            outcome = 4;
        }
        else
        {
            outcome = 5;
        }

        return outcome;
    }

    /**
     * Game behaviour dependent on outcome case
     * @param outcome   outcome integer from 0 to 5
     * @param size      current garden size
     * @param player    current player
     * @param keyInput  hook for a Scanner object to allow user input
     * @return          an array containing values if won the game and if needed input
     */
    public static boolean gameDoOutcome(int outcome, int size, Player player, Scanner keyInput)
    {
        boolean gameIsWon = false;

        switch(outcome)
        {
            case 0: // Gets to plant 1 tree and 1 flower !! check if won
                gameIsWon = gameGardenPlanting(1, 1, player, keyInput);
                break;
            case 1: // Gets to plant 2 flowers  !! check if won
                gameIsWon = gameGardenPlanting(0, 2, player, keyInput);
                break;
            case 2: // Gets to plant 2 trees    !! check if won
                gameIsWon = gameGardenPlanting(2, 0, player, keyInput);
                break;
            case 3: // Rabbit eats
                gameRabbit(player, size);
                gameIsWon = false;
                break;
            case 4: // Gets to plant a tree
                gameIsWon = gameGardenPlanting(1, 0, player, keyInput);
                break;
            case 5: // Gets to plant a flower
                gameIsWon = gameGardenPlanting(0, 1, player, keyInput);
                break;
            default: // Unexpected error
                System.out.println("Outcome improperly implemented");
                System.exit(0);
                break;
        }

        return gameIsWon;
    }

    /**
     * Plants an number of trees and of flowers with formatted messages, checks at every step if someone won the game
     * @param numTrees      number of trees to be planted
     * @param numFlowers    number of flowers to be planted
     * @param player        Player object who is planting
     * @param keyInput      hooks a Scanner object to allow user input
     * @return              whether game has been won or not
     */
    private static boolean gameGardenPlanting(int numTrees, int numFlowers, Player player, Scanner keyInput)
    {
        // Starting message builder with correct grammar
        String startPlantingMessage = "You must plant ";
        String textBlock_tree = "tree (2x2)";
        String textBlock_trees = "trees (2x2)";
        String textBlock_flower = "flower (1x1)";
        String textBlock_flowers = "flowers (1x1)";
        if (numTrees == 2)
            startPlantingMessage += "two " + textBlock_trees;
        else if (numTrees == 1)
            startPlantingMessage += "a " + textBlock_tree;
        if (numTrees != 0 && numFlowers != 0)
            startPlantingMessage += " and ";
        if (numFlowers == 2)
            startPlantingMessage += "two " + textBlock_flowers;
        else if (numFlowers == 1)
            startPlantingMessage += "a " + textBlock_flower;

        // Prints the starting message
        System.out.println(startPlantingMessage + ".\n");

        // Define Variables
        boolean isFirstRun = true;
        int counter_tree = 1;
        int counter_flower = 1;
        boolean plantingResult = false;

        // Plant numTrees amount of trees
        while(numTrees != 0)
        {
            // Display Garden
            if (isFirstRun) {
                player.showGarden();
            }

            // Tree planting message + number of Spots
            if (player.howManyTreesPossible() == 0)
            {
                System.out.println("Sorry no room left to plant a tree!\n");
                break;
            }
            else {
                if (isFirstRun && numFlowers != 0) {
                    // Toggle false isFirstRun and print
                    isFirstRun = false;
                    System.out.println("Let's start with the tree");
                }
                else {

                    // Formats with the amount of trees left to be planted
                    isFirstRun = false;
                    System.out.println("Let's plant tree #" + counter_tree + " (2x2) [" + numTrees + " left to plant]");
                    counter_tree++;
                }
            }
            // Number of spots left for trees with proper grammar
            if (player.howManyTreesPossible() == 1)
                System.out.println("You have " + player.howManyTreesPossible() + " place to do this.");
            else
                System.out.println("You have " + player.howManyTreesPossible() + " places to do this.");

            // Planting the tree and check if won
            promptCoordinatesAndPlant(keyInput, player, "tree");
            System.out.println();
            player.showGarden();
            System.out.println("You planted a tree.\n");

            // If the game is won, return true to exit method
            if (player.isGardenFull()) {
                System.out.println("... and won!!\n");
                plantingResult = true;
                return plantingResult;
            }

            // Iterating loop until no trees left to plant
            numTrees--;
            counter_tree++;
        }

        // Plant numFlowers amount of flowers
        while(numFlowers != 0)
        {
            if (isFirstRun) {
                // Display Garden
                player.showGarden();
            }

            // Flower planting message + number of Spots with proper grammar
            System.out.println("Let's plant flower #" + counter_flower + " (1x1) [" + numFlowers + " left to plant]");
            if (player.howManyFlowersPossible() == 1)
                System.out.println("You have " + player.howManyFlowersPossible() + " place to do this.");
            else
                System.out.println("You have " + player.howManyFlowersPossible() + " places to do this.");

            // Planting the flower and check if won
            isFirstRun = false;
            promptCoordinatesAndPlant(keyInput, player, "flower");
            System.out.println();
            player.showGarden();
            System.out.println("You planted a flower.\n");

            // If the game is won, return true to exit method
            if (player.isGardenFull()) {
                System.out.println("... and won!!\n");
                plantingResult = true;
                return plantingResult;
            }

            // Iterating loop until no flowers left to plant
            numFlowers--;
            counter_flower++;
        }

        // If game is not won after all the planting then return false
        plantingResult = false;
        return plantingResult;
    }

    /**
     * Prompts for coordinates as well as check if valid location, valid boundary, etc.
     * @param keyInput      hooks a Scanner object to allow user input
     * @param player        Player object whose turn it currently is
     * @param type          either "tree" or "flower"
     */
    private static void promptCoordinatesAndPlant(Scanner keyInput, Player player, String type) {
        String message = "Enter the coordinate as row column: ";
        String wrongInputs = "Either your row or column is not an integer. Try again.";
        boolean isCorrect;
        int r;
        int c;

        do {
            // Prompt and read user input. Checks if row and columns are ints.
            System.out.print(message);
            if (keyInput.hasNextInt())
                r = keyInput.nextInt();
            else
            {
                String garbage = keyInput.next();
                garbage = keyInput.next();
                garbage = null;
                isCorrect = false;
                System.out.println(wrongInputs);
                continue;
            }
            if (keyInput.hasNextInt())
                c = keyInput.nextInt();
            else
            {
                String garbage = keyInput.next();
                garbage = null;
                isCorrect = false;
                System.out.println(wrongInputs);
                continue;
            }

            // If row and columns are ints, pass those values into a planting method
            if (type.equals("tree"))
                isCorrect = player.plantTreeInGarden(r, c);
            else if (type.equals("flower")) {
                isCorrect = player.plantFlowerInGarden(r, c);
            } else {
                // Unexpected error
                System.out.println("promptCoordinates type is unknown");
                System.exit(0);
                isCorrect = false;      // Keeps IDE happy
            }
        } while (!isCorrect) ;

    }

    /**
     * Checks if the board is empty and if not, then eats part of a plant at a random location
     * It will iterate until it finds a valid location to eat
     * @param player        Player object whose turn it currently is
     * @param size          size of the garden
     */
    private static void gameRabbit(Player player, int size)
    {
        Random random = new Random();       // Random object
        boolean isValid = false;            // Something is planted checker
        int r;                              // Row coordinate
        int c;                              // Col coordinate

        // Display board
        System.out.println();
        player.showGarden();

        // Checks if the Garden is Empty
        if (player.isGardenEmpty())
        {
            System.out.println("A rabbit came to eat, but there was nothing in the garden to eat.\n");
        }
        else {
            // Find a spot to eat
            do {
                r = random.nextInt(size);
                c = random.nextInt(size);

                if (player.whatIsPlanted(r, c) != '-') {
                    isValid = true;
                    player.eatHere(r, c);
                }
            } while (!isValid);

            // Display message and display board again
            System.out.println("A rabbit ate whatever was planted in location (" + r + ", " + c + ")!\n\n");
            player.showGarden();
        }
    }

    /**
     * Show winning screen
     * @param player        array with all the players
     * @param winnersName   name of the winner
     * @param totalRounds   number of rounds until game is won
     */
    public static void gameWinningScreen(Player[] player, String winnersName, int totalRounds)
    {
        // Printing Results
        System.out.println("\n\n" +
                "FINAL RESULTS\n" +
                "-------------\n");

        // Showing everyone's garden
        System.out.println("" +
                "Here are the gardens after " + totalRounds + " rounds:\n");
        for (int i = 0; i < player.length; i++)
        {
            System.out.println(player[i].getName() + "'s garden");
            player[i].showGarden();
            System.out.print("\n");
        }

        // Show winner
        System.out.println("---------------\n\n" +
                "And the winner is... " + winnersName + "!!!\n" +
                "What a bountiful garden you have.\n");

        // Ending message
        System.out.println("Hope you had fun!\n");
    }
    
} // End of Let's Play Class
