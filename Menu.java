/********************************************************************************************
 * Author: Ajmel Muadz                                                                      *
 * Created: 21/05/2023                                                                      *   
 * Purpose: Menu (Main) class to run the Location.java and Project.java classes as well as  *
 *          main program functionality.                                                     *
 ********************************************************************************************/

import java.util.*;
import java.io.*;

public class Menu 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int menuSelection, statisticsChoice;
        boolean mainMenuLoop = true, fileNameValidation = true;
        // logFile stores any output displayed to the user in a program session.
        // fileName is the CSV file to be used in the program.
        String logFile = "log_file.txt", fileName = "";

        // Array that stores provinces/territories. To be used if user selects a specific
        // province or territory in Canada. provAndTerr is short form for province/territory
        String[] provAndTerr = {"Alberta", "British Columbia", "Manitoba",
                               "New Brunswick", "Newfoundland And Labrador", "Nova Scotia",
                               "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan",
                               "Northwest Territories", "Nunavut", "Yukon"};

        // 'false' boolean in 3rd parameter when program starts: Used to reset the log file
        //  every program start. (append value is set to 'false').
        printAndLog("Please enter the filename to read: ", logFile, false, "print");

        // Loop used to validate if filename entered is correct. User cannot proceed with
        // program until a correct filename is entered.
        while(fileNameValidation)
        {
            fileName = input.nextLine();
            if(fileName.equals("First_Nation_Infrastructure_Investment.csv"))
            {
                fileNameValidation = false;
            }
            else
            {
                printAndLog("Enter the valid filename path in root folder: ", logFile,
                true, "print");
            }
        }      
        System.out.println();

        int arraySize = numberOfLines(fileName);  // Number of rows in CSV files is stored in variable.
        Project[] projectArray = new Project[arraySize-1];  // Array to store projects has its value set.

        // Before program starts, this method call will store each row in the CSV file as
        // objects in the projectArray array.
        createProjectObjects(fileName, projectArray);

        printAndLog("Welcome to the Investments in Indigenous community " +
        "infrastructure\nProgram. There are a total of " + totalNumProjects(projectArray) + 
        " projects throughout Canada.", logFile, true, "println");

        // Main program loop.
        while(mainMenuLoop)
        {
            // Loop for province/territory menu is declared.
            boolean statisticsMenuLoop = true;

            printAndLog(displayMainMenu(), logFile, true, "println");

            try
            {
                // User will choose any options from 1 to 15.
                printAndLog("\nSelection: ", logFile, true, "print");
                menuSelection = input.nextInt();
                input.nextLine();
    
                if(menuSelection == 1)  // If user chooses option 1.
                {
                    // All of Canada's information displayed.
                    printAndLog(displayAllOfCanadaInfo(projectArray), logFile, true, "println");
                }
                else if(menuSelection >= 2 && menuSelection <= 14)  // If user chooses options 2 to 14.
                {
                    while(statisticsMenuLoop)  // User is in province/territory menu loop
                    {
                        // Province/Territory chosen is dynamically assigned based on position
                        // in provAndTerr array. The index is [menuSelection-2] because there
                        // are only 13 province/territory elements in the array, not 15.
                        String provOrTerrChosen = provAndTerr[menuSelection-2];

                        printAndLog("\nPlease make a choice from the statistics below for " +
                        provOrTerrChosen + ":", logFile, true, "print");

                        printAndLog(displayProvTerrMenu(), logFile, true, "println");

                        try
                        {
                            // User chooses statistics to display for province/territory
                            printAndLog("\nChoice: ", logFile, true, "print");
                            statisticsChoice = input.nextInt();
                            input.nextLine();
        
                            switch(statisticsChoice)
                            {
                                case 1:
                                    printAndLog(displayCase1Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    break;
                                
                                case 2:
                                    printAndLog(displayCase2Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    break;
        
                                case 3:
                                    printAndLog(displayCase3Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    break;
        
                                case 4:
                                    printAndLog(displayCase4Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    break;
        
                                case 5:
                                    printAndLog(displayCase1Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    printAndLog(displayCase2Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    printAndLog(displayCase3Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    printAndLog(displayCase4Results(projectArray, provOrTerrChosen),
                                    logFile, true, "println");
                                    break;
        
                                case 6:
                                    statisticsMenuLoop = false;
                                    printAndLog("", logFile, true, "println");
                                    break;
        
                                default:
                                    printAndLog("\nYou must enter a valid choice.", logFile, true, "print");
                            }
                        }
                        // Error handling in case user enters a string or decimal.
                        catch(InputMismatchException error)
                        {
                            printAndLog("\nYou must enter an integer!", logFile, true, "println");
                            input.nextLine();
                        }
                    }
                }
                // User chooses this option if quitting the program is desired.
                else if(menuSelection == 15)
                {
                    printAndLog("\nExiting Program...", logFile, true, "println");
                    mainMenuLoop = false;
                }
                // If user enters a number but it is not between 1 and 15, user is prompted to enter
                // a valid choice.
                else
                {
                    printAndLog("\nYou must enter a valid selection.\n", logFile, true, "println");
                } 
            }
            // Error handling in case user enters a string or decimal.
            catch(InputMismatchException error)
            {
                printAndLog("\nYou must enter an integer!\n", logFile, true, "println");
                input.nextLine();
            }
        }
    }
    /*******************************************************************************************
     * Name: printAndLog                                                                       *
     * Date: 21/05/2023                                                                        *
     * Import: pString (String), pLogFileName (String), pAppend (Boolean), pPrintType (String) *
     * Export: None                                                                            *
     * Purpose: To print strings and write them to a file at the same time.                    *
     *******************************************************************************************/
    public static void printAndLog(String pString, String pLogFileName,
    boolean pAppend, String pPrintType)
    {
        if(pPrintType == "println")
        {
            System.out.println(pString);
        }
        else if(pPrintType == "print")
        {
            System.out.print(pString);
        }

        FileOutputStream fileStream = null;
        PrintWriter pw;

        try
        {
            // Appending documents in file writing done with the help of StackOverflow.
            // APA 7 referencing supplied in another document.
            fileStream = new FileOutputStream(pLogFileName, pAppend);
            pw = new PrintWriter(fileStream);
            pw.println(pString);
            pw.close();
        }
        catch(IOException error)
        {
            System.out.println("Error in writing to file: " + error.getMessage());
        }
    }
    /*******************************************************************************************
     * Name: displayMainMenu                                                                   *
     * Date: 21/05/2023                                                                        *
     * Import: None                                                                            *
     * Export: mainMenu (String)                                                               *
     * Purpose: To display the menu options the user can choose. In a method so the main()     *
     *          method is cleaner/easier to read.                                              *
     *******************************************************************************************/
    public static String displayMainMenu()
    {
        String mainMenu = "Please make a selection from the Menu below.\n" +
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
        "\n> 15. Exit Program";

        return mainMenu;
    }
    /*******************************************************************************************
     * Name: displayAllOfCanadaInfo                                                            *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[])                                                       *
     * Export: allOfCanadaInfo (String)                                                        *
     * Purpose: To display relevant information if user chooses option 1 (All of Canada)       *
     *          in the main menu.                                                              *
     *******************************************************************************************/
    public static String displayAllOfCanadaInfo(Project[] pProjectArray)
    {
        String allOfCanadaInfo = "\nThe total number of projects in Canada: " + totalNumProjects(pProjectArray) +
        "\nThe total number of Ongoing projects: " + totalNumProjectStage("Ongoing", pProjectArray) +
        "\nThe total number of projects Completed: " + totalNumProjectStage("Completed", pProjectArray) +
        "\nThe percentage of Completed Projects: " + calculatePercentage(totalNumProjectStage("Completed", pProjectArray),
        totalNumProjects(pProjectArray)) + "%\n";

        return allOfCanadaInfo;
    }
    /*******************************************************************************************
     * Name: displayProvTerrMenu                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: None                                                                            *
     * Export: provTerrMenu (String)                                                           *
     * Purpose: To display the menu if a user chooses a province/territory. In a method so the *
     *          main() method is cleaner/easier to read.                                       *
     *******************************************************************************************/
    public static String displayProvTerrMenu()  // 'ProvTerr' is short form for 'Province/Territory'
    {
        String provTerrMenu = "\n> 1. Number of projects" +
        "\n> 2. Percentage of all projects located in this province/territory" +
        "\n> 3. Total number and percentage of Ongoing projects" +
        "\n> 4. Total number and percentage of Completed projects" +
        "\n> 5. All of the above statistics" +
        "\n> 6. Return to main menu";

        return provTerrMenu;
    }
    /*******************************************************************************************
     * Name: displayCase1Results                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pProvOrTerrChosen (String)                           *                                    
     * Export: case1Results (String)                                                           *
     * Purpose: To display the relevant information if user chooses option 1 in                *
     *          province/territory menu.                                                       *
     *******************************************************************************************/
    public static String displayCase1Results(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        String case1Results = "\nNumber of projects in this province/territory: " +
        numProjectsProvTerr(pProjectArray, pProvOrTerrChosen);

        return case1Results;
    }
    /*******************************************************************************************
     * Name: displayCase2Results                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pProvOrTerrChosen (String)                           *                                    
     * Export: case2Results (String)                                                           *
     * Purpose: To display the relevant information if user chooses option 2 in                *
     *          province/territory menu.                                                       *
     *******************************************************************************************/
    public static String displayCase2Results(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        String case2Results = "\nPercentage of all projects location in this province/territory: " +
        calculatePercentage(numProjectsProvTerr(pProjectArray, pProvOrTerrChosen),
        totalNumProjects(pProjectArray)) + "%";

        return case2Results;
    }
    /*******************************************************************************************
     * Name: displayCase3Results                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pProvOrTerrChosen (String)                           *                                    
     * Export: case3Results (String)                                                           *
     * Purpose: To display the relevant information if user chooses option 3 in                *
     *          province/territory menu.                                                       *
     *******************************************************************************************/
    public static String displayCase3Results(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        String case3Results = "\nTotal number of Ongoing projects in this province/territory: " +
        totalStageProvTerr("Ongoing", pProvOrTerrChosen, pProjectArray) + "" +

        "\nPercentage of Ongoing projects in this province/territory: " +
        calculatePercentage(totalStageProvTerr("Ongoing", pProvOrTerrChosen, pProjectArray),
        numProjectsProvTerr(pProjectArray, pProvOrTerrChosen)) + "%";

        return case3Results;
    }
    /*******************************************************************************************
     * Name: displayCase4Results                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pProvOrTerrChosen (String)                           *                                    
     * Export: case4Results (String)                                                           *
     * Purpose: To display the relevant information if user chooses option 4 in                *
     *          province/territory menu.                                                       *
     *******************************************************************************************/
    public static String displayCase4Results(Project[] pProjectArray, String pProvOrTerrChosen)
    {
        String case4Results = "\nTotal number of Completed projects in this province/territory: " +
        totalStageProvTerr("Completed", pProvOrTerrChosen, pProjectArray) + "" +

        "\nPercentage of Completed projects in this province/territory: " +
        calculatePercentage(totalStageProvTerr("Completed", pProvOrTerrChosen, pProjectArray),
        numProjectsProvTerr(pProjectArray, pProvOrTerrChosen)) + "%";

        return case4Results;
    }

    /*******************************************************************************************
     * Name: numberOfLines                                                                     *
     * Date: 21/05/2023                                                                        *
     * Import: pFileName (String)                                                              *                                    
     * Export: lineNum (Integer)                                                               *
     * Purpose: To return the number of lines in CSV file. This value is then used to          *
     *          instantiate the 'projectArray' array size.                                     *
     *******************************************************************************************/
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
        {
            if(fs != null)
            {
                try
                {
                    fs.close();
                }
                catch(IOException error2){}
            }
            System.out.println("Error in file processing: " + error.getMessage());
        }

        return lineNum;
    }
    /*******************************************************************************************
     * Name: createProjectObjects                                                              *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pFileName (String)                                   *                                    
     * Export: None                                                                            *
     * Purpose: To create Project objects to be populated in the projectArray array.           *
     *******************************************************************************************/
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
                // Parsing for each row is done through each loop iteration. Every comma separator
                // separates into 'fields' or 'columns'. Each field is then stored in variables.
                // (Lines 423 to 432)
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

                // Each index in the project array is then populated by a project object.
                pProjectArray[lineNum] = new Project(province, beneficiary, beneficiaryNum,
                assetClass, name, stage, location);

                lineNum++;
                line = br.readLine();
            }
        }
        catch(IOException error)
        {
            if(fs != null)
            {
                try
                {
                    fs.close();
                }
                catch(IOException error2){}
            }
            System.out.println("Error in file processing: " + error.getMessage());
        }
    }

    /*******************************************************************************************
     * Name: totalNumProjects                                                                  *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[])                                                       *                                    
     * Export: totalNum (Integer)                                                              *
     * Purpose: To find the total number of projects in the CSV file (All of Canada option)    *
     *******************************************************************************************/
    public static int totalNumProjects(Project[] pProjectArray)
    {
        int totalNum = 0;

        for(int i = 0; i < pProjectArray.length; i++)
        {
            totalNum++;
        }
        return totalNum;
    }
    /*******************************************************************************************
     * Name: totalNumProjectStage                                                              *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pStage (String)                                      *                                    
     * Export: totalNumStage (Integer)                                                         *
     * Purpose: Method that finds either the total number of 'Ongoing' or 'Completed' projects *
     * depending on the parameter supplied for pStage. (All of Canada option)                  *
     *******************************************************************************************/
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
    /*******************************************************************************************
     * Name: numProjectsProvTerr                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pProvOrTerrChosen (String)                           *                                    
     * Export: totalNum (Integer)                                                              *
     * Purpose: Finds total number of projects of a specific province/territory                *
     *          (Province/Territory option)                                                    *
     *******************************************************************************************/
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
    /*******************************************************************************************
     * Name: totalStageProvTerr                                                                *
     * Date: 21/05/2023                                                                        *
     * Import: pProjectArray (Project[]), pStage (String), pProvOrTerrChosen (String)          *                                    
     * Export: totalNumStage (Integer)                                                         *
     * Purpose: To find either the total number of 'Ongoing' or 'Completed' projects in a      *
     *          certain province/territory, depending on the pStage parameter supplied.        *
     *******************************************************************************************/
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

    /*******************************************************************************************
     * Name: calculatePercentage                                                               *
     * Date: 21/05/2023                                                                        *
     * Import: pFractionOfTotal (Integer), pTotalNum (Integer)                                 *                                    
     * Export: roundedPercentage (Double)                                                      *
     * Purpose: Method that performs relevant percentage calculations, giving the result       *
     *          rounded to 2 decimal places.                                                   *
     *******************************************************************************************/
    public static double calculatePercentage(int pFractionOfTotal, int pTotalNum)
    {
        double divisionResult = 0.0;

        divisionResult = (double)pFractionOfTotal / (double)pTotalNum;
        double percentage = divisionResult * 100.0;
        // Rounding to 2 decimal places done with the help of StackOverflow. APA 7 Referencing
        // supplied in another document.
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return roundedPercentage;
    }
}
