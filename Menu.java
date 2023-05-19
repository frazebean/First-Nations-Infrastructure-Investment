import java.util.*;

public class Menu 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        // menuOption array below to store options.
        String[] menuOptions = {"All of Canada", "Alberta", "British Columbia", "Manitoba",
                               "New Brunswick", "Newfoundland and Labrador", "Nova Scotia",
                               "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan",
                               "Northwest Territories", "Nunavut", "Yukon"};

        int menuSelection, statisticsChoice;
        boolean mainMenuLoop = true, statisticsMenuLoop = true;

        System.out.println("Welcome to the Investments in Indigenous community " +
        "infrastructure\nProgram. There are a total of 'XYZ' projects throughout Canada.");

        while(mainMenuLoop)
        {
            // Display the menu options.
            System.out.println("Please make a selection from the Menu below.\n");
            displayMenuOptions(menuOptions);
            System.out.println("> 0.  Exit Program");

            try
            {
                System.out.print("\nSelection: ");
                menuSelection = input.nextInt();
                input.nextLine();
    
                if(menuSelection == 1)
                {
                    System.out.println("\nThe total number of projects in Canada: XYZ");
                    System.out.println("The total number of Ongoing projects: XYZ");
                    System.out.println("The total number of projects Completed: XYZ");
                    System.out.println("The percentage of Completed Projects: XYZ\n");
                }
                else if(menuSelection >= 2 && menuSelection <= 14)
                {
                    while(statisticsMenuLoop)
                    {
                        System.out.println("\nPlease make a choice from the statistics below for " +
                        menuOptions[menuSelection-1] + ":");
                        System.out.println("> 1. Number of projects" +
                                        "\n> 2. Percentage of all projects located in this province/territory" +
                                        "\n> 3. Total number and percentage of Ongoing projects" +
                                        "\n> 4. Total number and percentage of Completed projects" +
                                        "\n> 5. All of the above statistics" +
                                        "\n> 6. Return to main menu");

                        try
                        {
                            System.out.print("\nChoice: ");
                            statisticsChoice = input.nextInt();
                            input.nextLine();
        
                            switch(statisticsChoice)
                            {
                                case 1:
                                    // Display number of projects
                                    break;
                                
                                case 2:
                                    // Display percentage of all projects located in province/territory
                                    break;
        
                                case 3:
                                    // Display total number and percentage of Ongoing projects
                                    break;
        
                                case 4:
                                    // Display total number and percentage of Completed projects
                                    break;
        
                                case 5:
                                    // Display all above statistics
                                    break;
        
                                case 6:
                                    statisticsMenuLoop = false;
                                    System.out.println();
                                    break;
        
                                default:
                                    System.out.println("\nYou must enter a valid choice.");
                            }
                        }
                        catch(InputMismatchException error)
                        {
                            System.out.println("\nYou must enter an integer!");
                            input.nextLine();
                        }
                    }
                }
                else if(menuSelection == 0)
                {
                    System.out.println("\nExiting Program...");
                    mainMenuLoop = false;
                }
                else
                {
                    System.out.println("\nYou must enter a valid selection.\n");
                } 
            }
            catch(InputMismatchException error)
            {
                System.out.println("\nYou must enter an integer!\n");
                input.nextLine();
            }
        }
    }

    public static void displayMenuOptions(String[] pMenuOptions)
    {
        for(int i = 0; i < pMenuOptions.length; i++)
        {
            if(i+1 >= 1 && i+1 <= 9)
            {
                System.out.println("> " + (i+1) + ".  " + pMenuOptions[i]);
            }
            else
            {
                System.out.println("> " + (i+1) + ". " + pMenuOptions[i]);
            }
        }
    }
}
