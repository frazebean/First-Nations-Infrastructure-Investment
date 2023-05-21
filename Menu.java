import java.util.*;
import java.io.*;

public class Menu 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        FileInputStream fs = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        /* Array that stores provinces/territories. To be used if user selects a specific
           province or territory in Canada. */
        String[] provAndTerr = {"Alberta", "British Columbia", "Manitoba",
                               "New Brunswick", "Newfoundland and Labrador", "Nova Scotia",
                               "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan",
                               "Northwest Territories", "Nunavut", "Yukon"};

        int menuSelection, statisticsChoice;
        boolean mainMenuLoop = true;
        String path = "First_Nation_Infrastructure_Investment.csv";

        int arraySize = numberOfLines(path, fs, isr, br);
        Project[] projectArray = new Project[arraySize-1];

        try
        {
            fs = new FileInputStream(path);
            isr = new InputStreamReader(fs);
            br = new BufferedReader(isr);
            int lineNum = 0;
            String line = br.readLine();

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

                projectArray[lineNum] = new Project(province, beneficiary, beneficiaryNum,
                assetClass, name, stage, location);

                lineNum++;
                line = br.readLine();
            }
        }
        catch(IOException error){}

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
                                    percentAllProvTerr(projectArray, provOrTerrChosen) + "%\n");
                                    break;
        
                                case 3:
                                    System.out.print("Total number of Ongoing projects in this province/territory: " +
                                    totalOngoingProvTerr(projectArray, provOrTerrChosen) + "\n");

                                    System.out.print("Percentage of Ongoing projects in this province/territory: " +
                                    percentOngoingProvTerr(projectArray, provOrTerrChosen) + "%\n");
                                    break;
        
                                case 4:
                                    System.out.print("Total number of Completed projects in this province/territory: " +
                                    totalCompletedProvTerr(projectArray, provOrTerrChosen) + "\n");

                                    System.out.print("Percentage of Completed projects in this province/territory: " +
                                    percentCompletedProvTerr(projectArray, provOrTerrChosen) + "%\n");
                                    break;
        
                                case 5:
                                    System.out.print("Number of projects in this province/territory: " +
                                    numProjectsProvTerr(projectArray, provOrTerrChosen) + "\n");

                                    System.out.print("Percentage of all projects location in this province/territory: " +
                                    percentAllProvTerr(projectArray, provOrTerrChosen) + "%\n");

                                    System.out.print("Total number of Ongoing projects in this province/territory: " +
                                    totalOngoingProvTerr(projectArray, provOrTerrChosen) + "\n");
                                    System.out.print("Percentage of Ongoing projects in this province/territory: " +
                                    percentOngoingProvTerr(projectArray, provOrTerrChosen) + "%\n");

                                    System.out.print("Total number of Completed projects in this province/territory: " +
                                    totalCompletedProvTerr(projectArray, provOrTerrChosen) + "\n");
                                    System.out.print("Percentage of Completed projects in this province/territory: " +
                                    percentCompletedProvTerr(projectArray, provOrTerrChosen) + "%\n");
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
        "\n> 6.  Newfoundland and Labrador" +
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
        System.out.println("The total number of Ongoing projects: " + totalNumOngoingProjects(pProjectArray));
        System.out.println("The total number of projects Completed: " + totalNumCompletedProjects(pProjectArray));
        System.out.println("The percentage of Completed Projects: " + percentCompletedProjects(pProjectArray) + "%\n");
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

    public static int totalNumProjects(Project[] pProjectArray)
    {
        int totalNum = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            totalNum++;
        }
        return totalNum;
    }

    public static int totalNumOngoingProjects(Project[] pProjectArray)
    {
        int totalNumOngoing = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            String stage = pProjectArray[i].getStage();

            if(stage.equals("Ongoing"))
            {
                totalNumOngoing++;
            }
        }
        return totalNumOngoing;
    }

    public static int totalNumCompletedProjects(Project[] pProjectArray)
    {
        int totalNumCompleted = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            String stage = pProjectArray[i].getStage();

            if(stage.equals("Completed"))
            {
                totalNumCompleted++;
            }
        }
        return totalNumCompleted;
    }

    public static double percentCompletedProjects(Project[] pProjectArray)
    {
        double divisionResult = 0.0;

        int totalNum = totalNumProjects(pProjectArray);
        int totalNumCompleted = totalNumCompletedProjects(pProjectArray);

        divisionResult = (double)totalNumCompleted / (double)totalNum;
        double percentage = divisionResult * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;  // Got from StackOverflow

        return roundedPercentage;
    }

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

    public static double percentAllProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        double divisionResult = 0.0;

        int totalNum = totalNumProjects(pProjectArray);
        int numProvTerr = numProjectsProvTerr(pProjectArray, pProvOrTerrChosen);

        divisionResult = (double)numProvTerr / (double)totalNum;
        double percentage = divisionResult * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return roundedPercentage;
    }

    public static int totalOngoingProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        int totalNumOngoing = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            if(pProjectArray[i].getProvince().equals(pProvOrTerrChosen))
            {
                if(pProjectArray[i].getStage().equals("Ongoing"))
                {
                    totalNumOngoing++;
                }
            }
        }
        return totalNumOngoing;
    }

    public static double percentOngoingProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        double divisionResult = 0.0;

        int totalNum = numProjectsProvTerr(pProjectArray, pProvOrTerrChosen);
        int totalOngoing = totalOngoingProvTerr(pProjectArray, pProvOrTerrChosen);

        divisionResult = (double)totalOngoing / (double)totalNum;
        double percentage = divisionResult * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return roundedPercentage;
    }

    public static int totalCompletedProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        int totalNumOngoing = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            if(pProjectArray[i].getProvince().equals(pProvOrTerrChosen))
            {
                if(pProjectArray[i].getStage().equals("Completed"))
                {
                    totalNumOngoing++;
                }
            }
        }
        return totalNumOngoing;
    }

    public static double percentCompletedProvTerr(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        double divisionResult = 0.0;

        int totalNum = numProjectsProvTerr(pProjectArray, pProvOrTerrChosen);
        int totalCompleted = totalCompletedProvTerr(pProjectArray, pProvOrTerrChosen);

        divisionResult = (double)totalCompleted / (double)totalNum;
        double percentage = divisionResult * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return roundedPercentage;
    }
}
