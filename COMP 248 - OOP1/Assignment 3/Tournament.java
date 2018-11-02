/* ----------------------------------------------------------------
 * Assignment 3 Q1
 * Written by: Laurent Lao ID: 40020483
 * For COMP 248 2182-R
 * 
 * This program simulates a football tournament. It asks the user
 * for their favourite team and ensures that the team is one of the
 * 16 teams that made it into the tournament. After that, it simulates
 * tournaments until their favourite team wins or 20 tournaments have
 * passed. Every match has goal values generated from both teams from
 * 0 to 4. In the event of a draw, it goes into sudden death and selects
 * one team randomly who scores the winning goal. After tournaments are
 * generated, print results, if favourite team won as well as general
 * goal statistics with averages.
 -----------------------------------------------------------------*/

// Ask if these are valid
// -- util.Random method OK!
// -- String.format("",args) method -- check Decimal.format
// -- Class called Decimal format
// -- Additional whitespace???

import java.util.Scanner;
import java.util.Random;


public class Tournament {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Output roadmap
		// Either team wins or 20 tries
		// Count goals for each match for each tournament and average goal
		
		// Defining Variables
		String[] teams16 = {"Uruguay", "Portugal", "France", "Argentina", "Brazil", "Mexico", "Belgium", "Japan", "Spain", "Russia", "Croatia", "Denmark", "Sweden", "Switzerland", "Colombia", "England"};	// Initialize the team names
		String userTeam;											// Will contain the team chosen by user
		final int T_LIMIT = 20;										// Total Tournaments to be played
		final int MATCH_LIM = 15;									// Total Matches per Tournament
		final int INFO = 5;											// Information kept per match
		final int MATCH_IN_R16 = 8;									// # of matches in the Round16
		int userTeamIndex = -1;										// Will contain the index of the team chosen by user
		int [] winningTeam = new int[T_LIMIT];						// Array that will contain the index of the winning team of each tournament
		boolean chosenTeamWon = false;								// Tracks if the userTeam won
		int tournamentCount = 0;									// Keeps count of the number of tournaments
		int [][][] tournament = new int[T_LIMIT][MATCH_LIM][INFO];	// Array that will contain for each tournament, their individual matches and the match's information
																	// tournament [i][j][k] 
																	// [i] is tournament number
																	// [j] is the match number from 0-15
																	// [k] = 0: Total goal, 1,2: Index of team playing, 3,4: respective goal count
		Scanner keyInput = new Scanner(System.in);					// Create Scanner object
		Random rnd = new Random();									// Create Random object
		
		// Welcome Message
		System.out.println("==== Welcome to Football simulator 2018 ====");
		
		// Prompting for team and checking if it's one of the 16 teams
		System.out.print("\n\nEnter your favourite team: ");
		userTeam = keyInput.nextLine();
		String withoutWhiteSpace = "";	// Will contain userTeam without whitespace
		for (int i = 0; i < userTeam.length(); i++)
		{
			// write to withoutWhiteSpace and ignore white space
			if (userTeam.charAt(i) == ' ')
				continue;
			else
				withoutWhiteSpace += userTeam.charAt(i);
		}
		// Assign the corrected team name
		userTeam = withoutWhiteSpace;
		for (int i = 0; i <= teams16.length; i++)
		{
			// Check if one of the teams and keep its index
			if (i == teams16.length)
			{
				// If it reaches the bounds, exit immediately
				System.out.println("\n\nYour team is not in the Round of 16");
				System.out.println("\nEnd of Program");
				System.exit(0);
			}
			else if (userTeam.equalsIgnoreCase(teams16[i]))
			{
				// If it finds a team, end the loop and save index value
				userTeamIndex = i;
				break;
			}
			else
				// Otherwise, just go to next iteration
				continue;
		}
		
		// Tournament algorithm
		for (; tournamentCount < T_LIMIT; tournamentCount++)
		{	
			// Randomly determining Round of 16
			String [] teamsAlreadyAssigned = new String[teams16.length]; 		// Stores already assigned teams
			int writeAt = 0;													// Stores where to write the newly assigned team in the teamsAlreadyAssigned array
			for (int j = 0; j < MATCH_IN_R16; j++)								// j is the match number
			{
				// Keeping Index to write team selection into
				for (int k = 0; k <= 1; k++)									// k helps for index, if k is even : writes to tournament[tournamentCount][j][1] and if odd, writes to tournament[tournamentCount][j][2]
				{
					boolean unique = false;										// Keeps track of uniqueness of team
					
					// Assigns a random team and check if that team is already in play
					while(!unique)
					{
						int teamSelection = rnd.nextInt(teams16.length);	// Randomly selects one of the team
						
						boolean isAlreadySelected = false;
						// Iterating on the teamsAlreadyAssigned array
						for (int n = 0; n < writeAt; n++)
						{
								isAlreadySelected = teams16[teamSelection].equals(teamsAlreadyAssigned[n]);
								if (isAlreadySelected)
									// immediately break out of the loop if a team is already assigned
									break;
						}
						if (isAlreadySelected)
						{
							// if it's already selected, then it's not unique
							unique = false;
						}
						else if (!isAlreadySelected)
						{
							// Switch Unique to true, write the selected team in Tournament array and in teamsAlreadyAssigned and also increment writeAt
							unique = true;
							tournament[tournamentCount][j][1 + k] = teamSelection;
							teamsAlreadyAssigned[writeAt] = teams16[teamSelection];
							writeAt++;
						}
						else
						{
							// Unexpected error
							System.out.println("Error: Couldn't determine if Team is already selected");
							System.exit(-1);
						}
					}	
				}
			}
			
			// Match Algorithm
			for (int i = 0; i < (MATCH_LIM); i++)
			{
					// Assigning scores for opposing teams and sudden death if draw
					if ((tournament[tournamentCount][i][3] = rnd.nextInt(5)) == (tournament[tournamentCount][i][4] = rnd.nextInt(5)))
					{
						// Randomly choose which team wins the draw
						int winnerOfDraw = rnd.nextInt(2);	// returns either 0 or 1 (team 1 or team 2)
						if (winnerOfDraw == 0)
						{
							// If team 1 won, increment their score
							tournament[tournamentCount][i][3]++;
						}
						else if (winnerOfDraw == 1)
						{
							tournament[tournamentCount][i][4]++;
						}
						else
						{
							// Unexpected error
							System.out.println("Error: The Winner of the Draw couldn't be determined");
							System.exit(0);
						}
					}
					
					// Evaluate the winner
					int matchWinner = -1;					// Keeps track of the index of winner of match
					if (tournament[tournamentCount][i][3] > tournament[tournamentCount][i][4])
						matchWinner = tournament[tournamentCount][i][1];
					else if (tournament[tournamentCount][i][3] < tournament[tournamentCount][i][4])
						matchWinner = tournament[tournamentCount][i][2];
					else
					{
						// Unexpected error
						System.out.println("Error: A Winner couldn't be determined.");
						System.exit(0);
					}
					
					// Store total goal for the match
					tournament[tournamentCount][i][0] = tournament[tournamentCount][i][3] + tournament[tournamentCount][i][4];
				
					// Advance winning team to next bracket
					switch(i)
					{
					// Round of 16 Behaviour [0 to 7]
					case 0: 
						tournament[tournamentCount][8][1] = matchWinner;
						break;
					case 1: 
						tournament[tournamentCount][8][2] = matchWinner;
						break;
					case 2: 
						tournament[tournamentCount][9][1] = matchWinner;
						break;
					case 3: 
						tournament[tournamentCount][9][2] = matchWinner;
						break;
					case 4: 
						tournament[tournamentCount][10][1] = matchWinner;
						break;
					case 5:
						tournament[tournamentCount][10][2] = matchWinner;
						break;
					case 6:
						tournament[tournamentCount][11][1] = matchWinner;
						break;
					case 7:
						tournament[tournamentCount][11][2] = matchWinner;
						break;
					
					// Quarter-Final Behaviour [8 to 11]
					case 8:
						tournament[tournamentCount][12][1] = matchWinner;
						break;
					case 9:
						tournament[tournamentCount][12][2] = matchWinner;
						break;
					case 10:
						tournament[tournamentCount][13][1] = matchWinner;
						break;
					case 11:
						tournament[tournamentCount][13][2] = matchWinner;
						break;
					
					// Semi-Final Behaviour [12, 13]
					case 12:
						tournament[tournamentCount][14][1] = matchWinner;
						break;
					case 13:
						tournament[tournamentCount][14][2] = matchWinner;
						break;
						
					// Final Behaviour
					case 14:
						winningTeam[tournamentCount] = matchWinner;
						break;
						
					//Unexpected Error
					default:
						System.out.println("Error: Switch is badly implemented");
						System.exit(0);
					}
			}
			
			// Print the results
			for (int i = 0; i < MATCH_LIM; i++)
			{	
				// Header
				switch(i)
				{
				case 0:
					System.out.print("\nROUND OF 16");
					break;
				case 8:
					System.out.print("\nQUARTER-FINALS");
					break;
				case 12:
					System.out.print("\nSEMI-FINALS");
					break;
				case 14:
					System.out.print("\nFINAL");
				}
				
				// Printing Individual Match results
				System.out.print(" [" + teams16[tournament[tournamentCount][i][1]]
								+ " " + tournament[tournamentCount][i][3]
								+ ":" + tournament[tournamentCount][i][4]
								+ " " + teams16[tournament[tournamentCount][i][2]]
								+ "]");		
			}
			
			// Printing Winner message
			System.out.println("\nTournament " + (tournamentCount + 1) + ": The Winner is \t~ " + teams16[winningTeam[tournamentCount]].toUpperCase() + "! ~");
			
			// Check if the team chosen by user has won and break if that's the case
			if (chosenTeamWon = teams16[winningTeam[tournamentCount]].equals(teams16[userTeamIndex]))
				{
				tournamentCount++;
				break;
				}
		}	// <-- End of Tournament Algorithm bracket
		
		// Printing Tournament cycles outcome
		if (chosenTeamWon)
		{	
			System.out.println("\nIt took " + (tournamentCount) + " tournaments(s) of the game for " + teams16[userTeamIndex] + " to win!!!");
			System.out.println("\n");
		}
		else if (tournamentCount == T_LIMIT)
		{
			System.out.println("\nSorry, " + teams16[userTeamIndex] + " didn't win in " + T_LIMIT + " tournaments! :(");
		}
		
		// Goal Statistics
		double [][][] goalStats = new double[tournamentCount][MATCH_LIM + 1][1];	// Array where the goal stats will be calculated from. Extra size to contain total average per tournament
		// Iterate over tournament data to populate goalStats
		for (int i = 0; i < tournamentCount; i++)
		{
			for (int j = 0; j < MATCH_LIM; j++)
			{
				goalStats[i][15][0] += (goalStats[i][j][0] = tournament[i][j][0]);
			}
				goalStats[i][15][0] /= MATCH_LIM;
		}
		// Printing Goal Statistics from goalStats
		System.out.println("\nGOAL STATS");
		for (int i = 0; i < tournamentCount; i++)
		{
			System.out.print("\n[Tournament " + (i + 1) + "] " + (i < 9 ? " ": "") + "Total goals: [");
			
			for (int j = 0; j < MATCH_LIM; j++)
			{
					System.out.print((int) goalStats[i][j][0]);
					
					if (j < (MATCH_LIM - 1))
						System.out.print(", ");
			}
			// Cast average goal into a string, format to 1 decimal and print
			String averageGoalForTournament = String.format("%.1f", goalStats[i][15][0]);	// Format the String
			System.out.print("] [Average: " + averageGoalForTournament + "]");
		}
		// Printing Average
		double overallAverage = 0;						// Stores the Average
		for (int i = 0; i < tournamentCount; i++)
		{
			overallAverage += goalStats[i][15][0];
		}
		overallAverage /= (tournamentCount + 1);
		System.out.println("\nAverage goals for " + tournamentCount + " tournament(s): " + String.format("%.1f", overallAverage));
		// Count Over Average
		int countOverAverage = 0;
		for (int i = 0; i < tournamentCount; i++)
		{
			for (int j = 0; j < MATCH_LIM; j++)
			{
				if (tournament[i][j][0] > overallAverage)
				{
					countOverAverage++;
				}
			}
		}
		System.out.println("Total matches in all tournamentes over the average goal value: " + countOverAverage);
		
		// End of Program
		keyInput.close();
		System.out.println("\nThe program has ended.");
	} //<-- End of Main
		
} //<-- End of Class
