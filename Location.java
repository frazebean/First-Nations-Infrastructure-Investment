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
}
