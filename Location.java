public class Location 
{
    // Class fields
    private double latitude;
    private double longitude;
    private String coordinateSystem;

    // Accessor for latitude
    public double getLatitude()
    {
        return latitude;
    }
    // Mutator for latitude
    public void setLatitude(double pLatitude)
    {
        latitude = pLatitude;
    }

    // Accessor for longitude
    public double getLongitude()
    {
        return longitude;
    }
    // Mutator for longitude
    public void setLongitude(double pLongitude)
    {
        longitude = pLongitude;
    }

    // Accessor for coordinateSystem
    public String getCoordinateSystem()
    {
        return coordinateSystem;
    }
    // Mutator for coordinateSystem
    public void setCoordinateSystem(String pCoordinateSystem)
    {
        coordinateSystem = pCoordinateSystem;
    }

    // Constructor with parameters
    public Location(double pLatitude, double pLongitude, String pCoordinateSystem)
    {
        latitude = pLatitude;
        longitude = pLongitude;
        coordinateSystem = pCoordinateSystem;
    }
    // Copy constructor
    public Location(Location pLocation)
    {
        latitude = pLocation.getLatitude();
        longitude = pLocation.getLongitude();
        coordinateSystem = pLocation.getCoordinateSystem();
    }
    // Default constructor
    public Location()
    {
        latitude = 50.00;
        longitude = -50.00;
        coordinateSystem = "GCS_North_American_1983_CSRS";
    }
}
