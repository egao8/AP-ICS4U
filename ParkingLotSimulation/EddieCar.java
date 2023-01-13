/**
 * EddieCar class interacts with EddieTime and and EddieParkingLot class to construct an object with instance variables: 
 * - plateNumber, a representation of each unique object's identifying code.
 * - permit, whether or not the EddieCar object has a permit or not.
 * - enteringTime, the time in which the object enters the parking lot using the EddieTime class.
 * - colour, model - identifiers and specifications of the car.
 * - parkSpot, a EddieParkingLot object that indicates the lot the car is inside.
 * 
 * Further, the class has overridden toString() to output information logically, equals() to identify equality, default and overloaded constructors, 
 * and getters and setters.
 * 
 * @author (Eddie Gao)
 * @version (9/28/2022)
 */
public class EddieCar
{
    // Step 1. Initialise and declare instance variables
    // ═══════════════════════════════════════════════════════════════════
    
    // Privatising all these variables with "private" keyword ensures the security of a user's plate number, permit, and entering time -- data that
    // logically should not be exposed or changed without confirmation to do so.
    private String plateNumber; // Plate Number of the Car expressed as a String because plate numbers contain a mix of numerical and alphebetical values.
    private boolean permit; // true if contains permit, false if not.
    private EddieTime enteringTime; // The entering time of the car into the parking lot.
    
    private String colour, model; // Specifications
    
    private EddieParkingLot parkSpot; // The lot the EddieCar object is parked into.
    
    // CONSTRUCTORS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    // Step 2. Create a default constructor
    // ═══════════════════════════════════════════════════════════════════
    /**
     * Car Constructor
     * 
     * This constructor accepts no parameters and sets every instance variable to a default value.
     *
     */
    public EddieCar()
    {
        this.plateNumber = "ABC 123"; // Default value for a plate number as instructed.
        this.permit = false; // Logically, if a Car's permit is not confirmed, it should be assumed as not having one; thus, false.
        this.enteringTime = new EddieTime();
        this.colour = "Unknown"; // Unknown provides more clarity than Java's default value: null.
        this.model = "Unknown";
    }

    // Step 3. Create a constructor that now accepts a plate number
    // ═══════════════════════════════════════════════════════════════════
    /**
     * Car Constructor
     * 
     * An overloaded Car constructor that takes in a the plate number of the car.
     *
     * @param PN - String - A parameter that assigns a plate number code to the instance variable, plateNumber.
     */
    public EddieCar(String PN)
    {
        this.plateNumber = PN;
        this.permit = false;
        this.enteringTime = new EddieTime();
        this.colour = "Unknown";
        this.model = "Unknown";
    }
    
    // Step 4. Create a constructor that now accepts a plate number AND permit
    // ═══════════════════════════════════════════════════════════════════
    /**
     * Car Constructor
     *
     * @param PN - String - A parameter that assigns a plate number code to the instance variable, plateNumber.
     * @param permit - boolean - A parameter that yields a true or false value on whether or not the Car object has a permit. If true, a permit is present,
     * otherwise, false indicates no permit.
     */
    public EddieCar(String PN, boolean permit)
    {
        this.plateNumber = PN;
        this.permit = permit;
        this.enteringTime = new EddieTime();
        this.colour = "Unknown";
        this.model = "Unknown";
    }
    
    /**
     * Car Constructor
     *
     * @param PN - String - A parameter that assigns a plate number code to the instance variable, plateNumber.
     * @param permit - boolean - A parameter that yields a true or false value on whether or not the Car object has a permit. If true, a permit is present,
     * otherwise, false indicates no permit.
     * @param col - String - Represents the colour of the car.
     * @param mdl - String - Represents the model of the car, e.g (2001 Hyundai) -- this allows the user more customisability and distinguish between cars.
     */
    public EddieCar(String PN, boolean permit, String col, String mdl)
    {
        this.plateNumber = PN;
        this.permit = permit;
        this.enteringTime = new EddieTime();
        this.colour = col;
        this.model = mdl;
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method hasPermit
     * 
     * A helper method for the toString() override by determining if this.permit is true (a.k.a has a permit), if so, will add to the toString()
     * return value with an indication that the object has a permit. Otherwise, an empty string will be returned.
     *
     * @return String - An addition of a String that outputs if a car object has a permit or not.
     */
    public String hasPermit()
    {
        /* This is an alternative to the ternary operation in the return statement!
        if (this.permit)
        {
            return " with permit";
        }
        else
        {
            return "";
        }
        */
        
        // I.e, check if this.permit is true or false, if true: return a String representation with the ternary operator; if not, return an empty String.
        return (this.permit ? " with permit" : "");
    }
    
    // Step 5. Create a ToString
    // ═══════════════════════════════════════════════════════════════════
    /**
     * Method toString
     * 
     * This special method allows us to output info about the object efficiently and logically.
     *
     * @return String representation of Car object with plateNumber and an indication of a permit if this.permit is true.
     */
    public String toString()
    {
        return ("Car " + this.plateNumber + this.hasPermit()); // Return the plate number and an indication of permit.
    }
    
    
    /**
     * Method getSpecs
     * 
     * A non-static method that returns a logical and efficient string representation of the EddieCar object's instance variables pertaining to
     * specifications that are, in a practical sense, public to all. This is done efficiently as non-static methods can reference the instance variables
     * directly via this.x where x is a variable.
     *
     * @return String - A String representation of all the specifications and details of a EddieCar object, e.g model and colour.
     */
    public String getSpecs()
    {
        // Return the specifcations: model and colour.
        return ("Car Specifications:\n══════════════════════\n• Model: " + this.model + "\n• Colour: " + this.colour + "\n══════════════════════");
    }
    
    /**
     * Method equals
     * 
     * Method checks for equality by comparing the permit, model, and colour to a second EddieCar object's colour, model, and permit.
     * If congruency is determined, return true, otherwise, return false.
     *
     * @param c2 - EddieCar - An EddieCar object that is used to compare equality with another object.
     * @return - boolean - will return true if equality is satisfied, otherwise false.
     */
    public boolean equals(EddieCar c2)
    {
        // It is illogical to assume a car's license plate number will be the same as another as it is unique to each individual car; hence, it should
        // be omitted from determining equality. However, there are other variables to consider when concluding equality between two objects.
        
        // && operator ensures all conditions must be met until equality is true.
        // equalsIgnoreCase() non-static method used for strings - ignoring cases as "2011 Hyundai" and "2011 hyundai" are still the same model for e.g.
        return (this.permit == c2.permit && this.model.equalsIgnoreCase("c2.model") && this.colour.equalsIgnoreCase(c2.colour));
    }
    
    // Getters and Setters
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method getPermit
     * 
     * returns permit
     *
     * @return this.permit - boolean - returns a boolean indicating whether EddieCar object has permit or not (true, false).
     */
    public boolean getPermit()
    {
        return this.permit;
    }
    
    /**
     * Method setPermit
     * 
     * Sets permit.
     *
     * @param p - boolean - a new value for permit.
     */
    public void setPermit(boolean p)
    {
        this.permit = p;
    }
    
    /**
     * Method getEnteringTime
     * 
     * returns the entering time.
     *
     * @return - EddieTime - this.enteringTime
     */
    public EddieTime getEnteringTime()
    {
        return this.enteringTime;
    }
    
    /**
     * Method setEnteringTime
     * 
     * sets enteringTime to new value.
     *
     * @param ET - sets enteringTime to a new EddieTime object.
     */
    public void setEnteringTime(EddieTime ET)
    {
        this.enteringTime = ET;
    }
    
    /**
     * Method getParkingSpot
     * 
     * returns parkSpot.
     *
     * @return - parkSpot - EddieParkingLot
     */
    public EddieParkingLot getParkingSpot()
    {
        return this.parkSpot;
    }
    
    /**
     * Method setParkingSpot
     *
     * @param PS - a new EddieParkingLot value
     */
    public void setParkingSpot(EddieParkingLot PS)
    {
        this.parkSpot = PS;
    }
    
    /**
     * Method getPlateNumber
     *
     * @return plateNumber - String
     */
    public String getPlateNumber()
    {
        return this.plateNumber;
    }
    
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    // END OF PROGRAM
}