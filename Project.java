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
        province = pProvince;
    }

    // Accessor for beneficiary
    public String getBeneficiary()
    {
        return beneficiary;
    }
    // Mutator for beneficiary
    public void setBeneficiary(String pBeneficiary)
    {
        beneficiary = pBeneficiary;
    }

    // Accessor for beneficiaryNum
    public String getBeneficiaryNum()
    {
        return beneficiaryNum;
    }
    // Mutator for beneficiaryNum
    public void setBeneficiaryNum(String pBeneficiaryNum)
    {
        beneficiaryNum = pBeneficiaryNum;
    }

    // Accessor for assetClass
    public String getAssetClass()
    {
        return assetClass;
    }
    // Mutator for assetClass
    public void setAssetClass(String pAssetClass)
    {
        assetClass = pAssetClass;
    }

    // Accessor for name
    public String getName()
    {
        return name;
    }
    // Mutator for name
    public void setName(String pName)
    {
        name = pName;
    }

    // Accessor for stage
    public String getStage()
    {
        return stage;
    }
    // Mutator for stage
    public void setStage(String pStage)
    {
        stage = pStage;
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
        province = pProvince;
        beneficiary = pBeneficiary;
        beneficiaryNum = pBeneficiaryNum;
        assetClass = pAssetClass;
        name = pName;
        stage = pStage;
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
        location = new Location();
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
}
