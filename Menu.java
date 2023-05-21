import java.util.*;
import java.io.*;

public class Menu 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        /* Array that stores provinces/territories. To be used if user selects a specific
           province or territory in Canada. */
        String[] provAndTerr = {"Alberta", "British Columbia", "Manitoba",
                               "New Brunswick", "Newfoundland And Labrador", "Nova Scotia",
                               "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan",
                               "Northwest Territories", "Nunavut", "Yukon"};

        int menuSelection, statisticsChoice;
        boolean mainMenuLoop = true;
        String path = "First_Nation_Infrastructure_Investment.csv";

        int arraySize = numberOfLines(path);
        Project[] projectArray = new Project[arraySize-1];

        createProjectObjects(path, projectArray);

        System.out.println("Welcome to the Investments in Indigenous community " +
        "infrastructure\nProgram. There are a total of " + totalNumProjects(projectArray) + 
        " projects throughout Canada.");

        while(mainMenuLoop)
        {
            boolean statisticsMenuLoop = true;

            displayMainMenu();

            try
            {
                System.out.print("\nSelection: ");
                menuSelection = input.nextInt();
                input.nextLine();
    
                if(menuSelection == 1)
                {
                    displayAllOfCanadaInfo(projectArray);
                }
                else if(menuSelection >= 2 && menuSelection <= 14)
                {
                    while(statisticsMenuLoop)
                    {
                        String provOrTerrChosen = provAndTerr[menuSelection-2];

                        System.out.println("\nPlease make a choice from the statistics below for " +
                        provOrTerrChosen + ":");

                        displayProvTerrMenu();

                        try
                        {
                            System.out.print("\nChoice: ");
                            statisticsChoice = input.nextInt();
                            input.nextLine();
        
                            switch(statisticsChoice)
                            {
                                case 1:
                                    System.out.print("Number of projects in this province/territory: " +
                                    numProjectsProvTerr(projectArray, provOrTerrChosen) + "\n");
                                    break;
                                
                                case 2:
                                    System.out.print("Percentage of all projects location in this province/territory: " +
                                    calculatePercentage(numProjectsProvTerr(projectArray, provOrTerrChosen), totalNumProjects(projectArray)) + "%\n");
                                    break;
        
                                case 3:
                                    System.out.print("Total number of Ongoing projects in this province/territory: " +
                                    totalStageProvTerr("Ongoing", provOrTerrChosen, projectArray) + "\n");

                                    System.out.print("Percentage of Ongoing projects in this province/territory: " +
                                    calculatePercentage(totalStageProvTerr("Ongoing", provOrTerrChosen, projectArray), numProjectsProvTerr(projectArray, provOrTerrChosen)) + "%\n");
                                    break;
        
                                case 4:
                                    System.out.print("Total number of Completed projects in this province/territory: " +
                                    totalStageProvTerr("Completed", provOrTerrChosen, projectArray) + "\n");

                                    System.out.print("Percentage of Completed projects in this province/territory: " +
                                    calculatePercentage(totalStageProvTerr("Completed", provOrTerrChosen, projectArray), numProjectsProvTerr(projectArray, provOrTerrChosen)) + "%\n");
                                    break;
        
                                case 5:
                                    System.out.print("Number of projects in this province/territory: " +
                                    numProjectsProvTerr(projectArray, provOrTerrChosen) + "\n");

                                    System.out.print("Percentage of all projects location in this province/territory: " +
                                    calculatePercentage(numProjectsProvTerr(projectArray, provOrTerrChosen), totalNumProjects(projectArray)) + "%\n");

                                    System.out.print("Total number of Ongoing projects in this province/territory: " +
                                    totalStageProvTerr("Ongoing", provOrTerrChosen, projectArray) + "\n");
                                    System.out.print("Percentage of Ongoing projects in this province/territory: " +
                                    calculatePercentage(totalStageProvTerr("Ongoing", provOrTerrChosen, projectArray), numProjectsProvTerr(projectArray, provOrTerrChosen)) + "%\n");

                                    System.out.print("Total number of Completed projects in this province/territory: " +
                                    totalStageProvTerr("Completed", provOrTerrChosen, projectArray) + "\n");
                                    System.out.print("Percentage of Completed projects in this province/territory: " +
                                    calculatePercentage(totalStageProvTerr("Completed", provOrTerrChosen, projectArray), numProjectsProvTerr(projectArray, provOrTerrChosen)) + "%\n");
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

    public static void displayMainMenu()
    {
        System.out.println("Please make a selection from the Menu below.\n" +
        "\n> 1.  All of Canada" +
        "\n> 2.  Alberta" +
        "\n> 3.  British Columbia" +
        "\n> 4.  Manitoba" +
        "\n> 5.  New Brunswick" +
        "\n> 6.  Newfoundland And Labrador" +
        "\n> 7.  Nova Scotia" +
        "\n> 8.  Ontario" +
        "\n> 9.  Prince Edward Island" +
        "\n> 10. Quebec" +
        "\n> 11. Saskatchewan" +
        "\n> 12. Northwest Territories" +
        "\n> 13. Nunavut" +
        "\n> 14. Yukon" +
        "\n> 0.  Exit Program");
    }
    public static void displayAllOfCanadaInfo(Project[] pProjectArray)
    {
        System.out.println("\nThe total number of projects in Canada: " + totalNumProjects(pProjectArray));
        System.out.println("The total number of Ongoing projects: " + totalNumProjectStage("Ongoing", pProjectArray));
        System.out.println("The total number of projects Completed: " + totalNumProjectStage("Completed", pProjectArray));
        System.out.println("The percentage of Completed Projects: " + calculatePercentage(totalNumProjectStage("Completed", pProjectArray), totalNumProjects(pProjectArray)) + "%\n");
    }
    public static void displayProvTerrMenu()  // 'ProvTerr' is short form for 'Province/Territory'
    {
        System.out.println("> 1. Number of projects" +
        "\n> 2. Percentage of all projects located in this province/territory" +
        "\n> 3. Total number and percentage of Ongoing projects" +
        "\n> 4. Total number and percentage of Completed projects" +
        "\n> 5. All of the above statistics" +
        "\n> 6. Return to main menu");
    }

    /* This method finds the number of lines in the CSV file. Used to initialise the array size
       that stores project objects.*/
    public static int numberOfLines(String pFileName)
    {
        FileInputStream fs = null;
        InputStreamReader isr;
        BufferedReader br;
        String line;
        int lineNum = 0;

        try
        {
            fs = new FileInputStream(pFileName);
            isr = new InputStreamReader(fs);
            br = new BufferedReader(isr);
            line = br.readLine();

            while(line != null)
            {
                lineNum++;
                line = br.readLine();
            }
            fs.close();
        }
        catch(IOException error)
        {}

        return lineNum;
    }
    /* This method creates project objects which will then be stored in an array. */
    public static void createProjectObjects(String pFileName, Project[] pProjectArray)
    {
        FileInputStream fs = null;
        InputStreamReader isr;
        BufferedReader br;
        String line;
        int lineNum = 0;
        
        try
        {
            fs = new FileInputStream(pFileName);
            isr = new InputStreamReader(fs);
            br = new BufferedReader(isr);
            line = br.readLine();
            line = br.readLine();

            while(line != null)
            {
                String fields[];
                fields = line.split(",");

                String province = fields[0];
                String beneficiary = fields[1];
                String beneficiaryNum = fields[2];
                String assetClass = fields[3];
                String name = fields[4];
                String stage = fields[6];

                double latitude = Double.parseDouble(fields[7]);
                double longitude = Double.parseDouble(fields[8]);
                String coordinateSystem = fields[9];
                Location location = new Location(latitude, longitude, coordinateSystem);

                pProjectArray[lineNum] = new Project(province, beneficiary, beneficiaryNum,
                assetClass, name, stage, location);

                lineNum++;
                line = br.readLine();
            }
        }
        catch(IOException error){}
    }

    // Method finds the total number of projects in Canada. (All of Canada option)
    public static int totalNumProjects(Project[] pProjectArray)
    {
        int totalNum = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            totalNum++;
        }
        return totalNum;
    }
    /* Method that finds either the total number of 'Ongoing' or 'Completed' projects, depending
       on the parameter supplied for pStage. (All of Canada option) */
    public static int totalNumProjectStage(String pStage, Project[] pProjectArray)
    {
        int totalNumStage = 0;

        for (int i = 0; i < pProjectArray.length; i++) 
        {
            String stage = pProjectArray[i].getStage();

            if(stage.equals(pStage))
            {
                totalNumStage++;
            }
        }
        return totalNumStage;
    }
    // Finds total number of projects of a specific province/territory (Province/Territory option)
    public static int numProjectsProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        int totalNum = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            if(pProjectArray[i].getProvince().equals(pProvOrTerrChosen))
            {
                totalNum++;
            }
        }
        return totalNum;
    }

    // Method that finds either the total number of 'Ongoing' or 'Completed' projects in a 
    // certain province/territory, depending on the pStage parameter suppli
    public static int totalStageProvTerr(String pStage, String pProvOrTerrChosen, Project[] pProjectArray)
    {
        int totalNum = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            if(pProjectArray[i].getProvince().equals(pProvOrTerrChosen))
            {
                if(pProjectArray[i].getStage().equals(pStage))
                {
                    totalNum++;
                }
            }
        }
        return totalNum++;
    }

    // Method that performs all relevant percentage calculations
    public static double calculatePercentage(int pFractionOfTotal, int pTotalNum)
    {
        double divisionResult = 0.0;

        // pTotalNum = method
        // pFractionOfTotal = method

        divisionResult = (double)pFractionOfTotal / (double)pTotalNum;
        double percentage = divisionResult * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return roundedPercentage;
    }
}
