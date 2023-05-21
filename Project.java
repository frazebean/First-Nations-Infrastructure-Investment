public class Project 
{
    // Class fields
    private String province;
    private String beneficiary;
    private String beneficiaryNum;
    private String assetClass;
    private String name;
    private String stage;
    private Location location;

    // Project class will contain Location object as one of the class fields (Aggregation)

    // Accessor for province
    public String getProvince()
    {
        return province;
    }
    // Mutator for province
    public void setProvince(String pProvince)
    {
        if(pProvince == "")
        {
            System.out.println("Error: Province name cannot be blank.");
        }
        else
        {
            province = pProvince;
        }
    }

    // Accessor for beneficiary
    public String getBeneficiary()
    {
        return beneficiary;
    }
    // Mutator for beneficiary
    public void setBeneficiary(String pBeneficiary)
    {
        if(pBeneficiary == "")
        {
            System.out.println("Error: Beneficiary cannot be blank.");
        }
        else
        {
            beneficiary = pBeneficiary;
        }
    }

    // Accessor for beneficiaryNum
    public String getBeneficiaryNum()
    {
        return beneficiaryNum;
    }
    // Mutator for beneficiaryNum
    public void setBeneficiaryNum(String pBeneficiaryNum)
    {
        if(pBeneficiaryNum == "")
        {
            System.out.println("Error: Beneficiary Number cannot be blank.");
        }
        else
        {
            beneficiaryNum = pBeneficiaryNum;
        }
    }

    // Accessor for assetClass
    public String getAssetClass()
    {
        return assetClass;
    }
    // Mutator for assetClass
    public void setAssetClass(String pAssetClass)
    {
        if(pAssetClass == "")
        {
            System.out.println("Error: Asset Class cannot be blank.");
        }
        else
        {
            assetClass = pAssetClass;
        }
    }

    // Accessor for name
    public String getName()
    {
        return name;
    }
    // Mutator for name
    public void setName(String pName)
    {
        if(pName == "")
        {
            System.out.println("Error: Name cannot be blank.");
        }
        else
        {
            name = pName;
        }
    }

    // Accessor for stage
    public String getStage()
    {
        return stage;
    }
    // Mutator for stage
    public void setStage(String pStage)
    {
        if(pStage == "")
        {
            System.out.println("Error: Stage cannot be blank.");
        }
        else
        {
            stage = pStage;
        }
    }

    // Accessor for location
    public Location getLocation()
    {
        return location;
    }
    // Mutator for location
    public void setLocation(Location pLocation)
    {
        location = pLocation;
    }

    // Constructor with parameters
    public Project(String pProvince, String pBeneficiary, String pBeneficiaryNum,
    String pAssetClass, String pName, String pStage, Location pLocation)
    {
        if(pProvince == "")
        {
            System.out.println("Error: Province name cannot be blank.");
        }
        else
        {
            province = pProvince;
        }

        if(pBeneficiary == "")
        {
            System.out.println("Error: Beneficiary cannot be blank.");
        }
        else
        {
            beneficiary = pBeneficiary;
        }

        if(pBeneficiaryNum == "")
        {
            System.out.println("Error: Beneficiary Number cannot be blank.");
        }
        else
        {
            beneficiaryNum = pBeneficiaryNum;
        }

        if(pAssetClass == "")
        {
            System.out.println("Error: Asset Class cannot be blank.");
        }
        else
        {
            assetClass = pAssetClass;
        }
        
        if(pName == "")
        {
            System.out.println("Error: Name cannot be blank.");
        }
        else
        {
            name = pName;
        }

        if(pStage == "")
        {
            System.out.println("Error: Stage cannot be blank.");
        }
        else
        {
            stage = pStage;
        }
        
        location = pLocation;
    }
    // Copy constructor
    public Project(Project pProject)
    {
        province = pProject.getProvince();
        beneficiary = pProject.getBeneficiary();
        beneficiaryNum = pProject.getBeneficiaryNum();
        assetClass = pProject.getAssetClass();
        name = pProject.getName();
        stage = pProject.getStage();
        location = pProject.getLocation();
    }
    // Default constructor
    public Project()
    {
        province = "NULL province";
        beneficiary = "NULL beneficiary";
        beneficiaryNum = "NULL beneficiaryNum";
        assetClass = "NULL assetClass";
        name = "NULL name";
        stage = "NULL stage";
        location = new Location();
    }

    // toString method
    public String toString()
    {
        String projectString;
        projectString = "Province is " + province +
                        "\nBeneficiary is " + beneficiary +
                        "\nBeneficiary Number is " + beneficiaryNum +
                        "\nAsset class is " + assetClass +
                        "\nName is " + name +
                        "\nStage is " + stage +
                        "\nLocation is " + location;

        return projectString;
    }
    // equals method
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        Project inProject = null;

        if(inProject instanceof Project)
        {
            inProject = (Project)inProject;
            if(province.equals(inProject.getProvince()))
            {
                if(beneficiary.equals(inProject.getBeneficiary()))
                {
                    if(beneficiaryNum.equals(inProject.getBeneficiaryNum()))
                    {
                        if(assetClass.equals(inProject.getAssetClass()))
                        {
                            if(name.equals(inProject.getName()))
                            {
                                if(stage.equals(inProject.getStage()))
                                {
                                    if(location.equals(inProject.getLocation()))
                                    {
                                        isEqual = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return isEqual;
    }
}
