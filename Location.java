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
        if(pLatitude < -90 || pLatitude > 90)
        {
            System.out.println("Error: Latitude can only be between -90 to 90 degrees.");
        }
        else
        {
            latitude = pLatitude;
        }
    }

    // Accessor for longitude
    public double getLongitude()
    {
        return longitude;
    }
    // Mutator for longitude
    public void setLongitude(double pLongitude)
    {
        if(pLongitude < -180 || pLongitude > 180)
        {
            System.out.println("Error: Longitude can only be between -180 to 180 degrees.");
        }
        else
        {
            longitude = pLongitude;
        }
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
        if(pLatitude < -90 || pLatitude > 90)
        {
            System.out.println("Error: Latitude can only be between -90 to 90 degrees.");
        }
        else
        {
            latitude = pLatitude;
        }

        if(pLongitude < -180 || pLongitude > 180)
        {
            System.out.println("Error: Longitude can only be between -180 to 180 degrees.");
        }
        else
        {
            longitude = pLongitude;
        }

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
        latitude = 45;
        longitude = 90;
        coordinateSystem = "GCS_North_American_1983_CSRS";
    }

    // toString method
    public String toString()
    {
        String locationString;
        locationString = "Latitude is " + latitude +
                         "\nLongitude is " + longitude +
                         "\nCoordinate system is " + coordinateSystem;

        return locationString;
    }
    // equals method
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        Location inLocation = null;

        if(inLocation instanceof Location)
        {
            inLocation = (Location)inObject;
            if(latitude == inLocation.getLatitude())
            {
                if(longitude == inLocation.getLongitude())
                {
                    if(coordinateSystem.equals(inLocation.getCoordinateSystem()))
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
}
