// Import Libraries
import java.util.ArrayList;

/**
 * Parking lot class utilises EddieCar and EddieTime objects to store an ArrayList of all EddieCar objects, the entering and leaving time, the capacity,
 * the lot number, the total revenue, the hourly rate and maximum charge for a car to park, and the current no. cars in the lot.
 * 
 * Methods pertain to:
 * - A default constructor
 * - Overloaded constructors
 * - calcCost - compares two EddieTime objects, the entering and leaving times of an EddieCar object and calculates a price to be paid based off the hourly
 * rate and maximum charge,
 * - carEnters and carLeaves -- two functional methods that allow a EddieCar object to enter the parking lot and leave the parking lot.
 * - isCarInLot -- returns a statement based off whether or not a EddieCar object is currently in the parking lot.
 * - isLotOpen -- returns a statement based off whether or not the parking lot is currently open using EddieTime objects.
 * - Getter and Setter methods for encapsulation.
 *
 * @author (Eddie Gao)
 * @version (9/29/2022)
 */
public class EddieParkingLot
{
    // Step 1. Declare and initialise instance variables.
    
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANTS AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED WITHOUT CONFIRMATION FROM
    // THE ADMINISTRATOR/DESIRED USER - THUS ARE ENCAPSULATED.
    
    // An ArrayList containing all the cars in the lot -- choosing this object over a primitive array due to seamless expansion if needed
    // by the user. For example, the endless size of an ArrayList over an array allows the user to change the capacity of the lot
    // due to reasons like renovation, construction, or any major changes. See note attached in classroom concerning the use of an array[].
    private ArrayList<EddieCar> carsInLot; 
    
    private float revenue; // The total revenue a sole lot has made.
    
    // These variables are set as floats because realistically, the rate in money will never exceed the float's range and floats are
    // more memory efficient than doubles. 
    private float hourlyRate, maxCharge;
    
    // Akin to previous reasoning, choosing to set capacity of cars, current car count, and the lot number as shorts are to use the most
    // efficient data types. According to GuinessWorldRecords, "the world's largest parking lot can hold 20,000 vehicles" -- thus,
    // the short range of 32,000 is enough to satisfy the largest realistic parking capacity. 
    private short capacity, currentCarCount, lotNumber;
    
    // Opening and Closing times will never change and thus are declared constants.
    public static final EddieTime OPENING_TIME = new EddieTime(6, 0); // represents 6am
    public static final EddieTime CLOSING_TIME = new EddieTime(23, 0); // Represents 11pm
    
    
    // CONSTRUCTORS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    /**
     * EddieParkingLot Constructor
     *
     * The following is a default constructor that sets instance variables to a logical default value. No parameters are present.
     * Capacity is set at 20 to accompany a variety of scenarios, i.e, company parking lots, stores, etc.
     * CurrentCarCount and LotNumber correspond with one another -- thus, they are both set to zero.
     * The hourly rate and max charge, though arbitrary and subjective to the user's preference, are set as a modest logical pricing
     * that suits most day-to-day uses of a parking lot.
     * A carsInLot ArrayList is used to hold all the EddieCar objects in the parking lot -- with a capacity set by the instance var, capacity.
     *
     */
    public EddieParkingLot() // <- no param. default constructor
    {
        // Initialise instance variables
        this.capacity = 20; // A logical default value of 20 lots open.
        this.lotNumber = 0; // The lot number of the obkect.
        this.currentCarCount = 0; // By default, no cars should currently be in a newly *default* constructed parking lot.
        
        // Logical and modest charges that are defaulted if no other value is set.
        this.hourlyRate = 3.00f; // Casting as float as the upmost precision and range is not required in this context.
        this.maxCharge = 12.00f; // The maximum charge that can be expected
        
        this.carsInLot = new ArrayList<EddieCar>(capacity); // Ensure that the instance variable is set after capacity has been declared. Otherwise, error.
    }
    
    /**
     * EddieParkingLot Constructor
     * 
     * Overloaded constructor that sets all instance variables to a default variable HOWEVER holds parameters for the lot number and capacity,
     * allowing the user to enter the essential information required to operate the parking lot.
     *
     * @param LN - int - represents a numerical ID which holds the individual identity of a single lot. Use of short value range in correspondence
     * to the capacity of the parking lot.
     * @param cap - int - capacity which represents the amount of cars able to park in the lot. 
     */
    public EddieParkingLot(int LN, int cap)
    {
        // Purposefully setting an int parameter yet casting as a desired data type (short) that is efficient and logical given the context.
        this.capacity = (short) cap;
        this.lotNumber = (short) LN;
        this.currentCarCount = 0;
        this.hourlyRate = 3.00f;
        this.maxCharge = 12.00f;

        this.carsInLot = new ArrayList(capacity);
        
    }
    
    /**
     * EddieParkingLot Constructor
     * 
     * An overloaded constructor that takes in parameters for all instance variables; this option of constructor is useful for users when they
     * are capable of passing in to the object all info about the EddieParkingLot object. Intentionally setting float values including hourRate and maxChr
     * as doubles for ease of use (i.e no need to cast) and then casting to a float in the constructor for the most efficient memory storage.
     *
     * @param LN - int -The lot number, a representation of every unique lot.
     * @param cap - int - The maximum capacity of the parking lot.
     * @param carCount - int - A parameter that displays the current amount of cars in the parking lot.
     * @param hourRate - double - the hourly charge rate ($) 
     * @param maxChr - double - the maximum charge rate ($)
     */
    public EddieParkingLot(int LN, int cap, int carCount, double hourRate, double maxChr)
    {
        // Purposefully setting the instance variables as integers and doubles so user does not need to cast in the input; then, casting in the constructor
        // to data types that are memory efficient.
        this.capacity = (short) cap;
        this.lotNumber = (short) LN;
        this.currentCarCount = (short) carCount;
        this.hourlyRate = (float) hourRate;
        this.maxCharge = (float) maxChr;

        this.carsInLot = new ArrayList(capacity);
        
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method toString
     *
     * @return String - String representation of the EddieParkingLot object with: Parking lot number, hourly rate, car capacity, current no. cars,
     * and the cars inside the lot currently.
     */
    public String toString()
    {
        return String.format("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n\nParking Lot #%d - rate = $%.2f, capacity %d, current cars %d. Cars in lot: %s.\n\n══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════", this.lotNumber,
        this.hourlyRate, this.capacity, this.currentCarCount, this.carsInLot.size() > 0 ? this.carsInLot.toString().replace("[","").replace("]","") : "0"); 
        // This conditional operator is used for the UI; an empty ArrayList will print [], which is not what is desired.
        // To solve this undesired output, we create an if condition to see if the size of the list is greater than 0 (a.k.a there are cars in the parking lot)
        // if not, simply print "none".
    }
    
    // ADD OR REMOVE CARS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method isCarInLot
     * 
     * Method takes a parameter, EddieCar object, and checks if it is currently in the array or not. If so, it will return true, otherwise false.
     * This helper method is used so that no car can enter twice, leave twice, etc.
     *
     * @param car - EddieCar - is used to determine if this parameter is inside the carsInLot array.
     * @return boolean - true if it is inside, false if it is not inside.
     */
    public boolean isCarInLot(EddieCar car)
    {
        if (this.carsInLot.contains(car))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Method isLotOpen
     * 
     * Helper method is used to ensure a car that enters (via carEnters method) is not entering when the parking lot is closed.
     *
     * @param time - EddieTime - a parameter that is checked to see if it is within the open hours of the parking lot.
     * @return boolean - true if it is open, false if it is trying to park while the lot is closed.
     */
    // Static as all static methods and instances of class should conform to the same closing and opening times.
    public static boolean isLotOpen(EddieTime time)
    {
        if (time.getHours() > OPENING_TIME.getHours() && time.getHours() < CLOSING_TIME.getHours())
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Method calcCost
     * 
     * Instructions for calculating the price to be paid:
     * "The price is paid in half hour increments (i.e., rounded up to the nearest half hour). 
     * For instance, if the hourly rate is $3.00, a car parked in this lot for 1 hour and 35 minutes should pay $6.00, 
     * and a car parked for 2 hours and 20 minutes will pay $7.50."
     * 
     * Very simple helper method that calculates the price by breaking the difference of two times into as many half hour increments as possible, rounded
     * up. Then this no. minutes in the difference is evaluated in an if structure to see if it should be rounded up or not.
     * For e.g, if the minutes are less than 30 (e.g 9:12), round up to the nearest 30 minute mark by incrementing by 1 (i.e 9:30).
     * However, with a minutes value of 48, round up twice: one for the initial 30, and another for the surplus.
     * To illustrate, the value 48 can be broken into two values, a value under 30 and a value over 30. So, the method adds 2
     * increments when it must be rounded twice to a full hour essentially.
     * 
     * Method further checks the maximum charge that can be issued, if the price is larger, it must limit itself to the max charge.
     * 
     *
     * @param t1 - EddieTime - Used to calculate the difference between the entering time and the leaving time in order to calc price.
     * @param t2 - EddieTime - Used to calculate the difference between the entering time and the leaving time in order to calc price.
     * @return float - The cost that has been elapsing over the difference of the two times, t1, and t2.
     */
    public float calcCost(EddieTime t1, EddieTime t2)
    {
        
        // Step 1. find the difference between both times -- which is used to calculate the pricing.
        EddieTime timeToBePaid = t1.difference(t2);
        
        // Step 2. Convert the difference into a half time in order to round up and down to the nearest half hour.
        // Using byte as half of the time to be paid will realistically never exceed 128 hours.
        byte halfOfTime = (byte) (2 * timeToBePaid.getHours());
        
        // Step 3. Determine in which case we should round up.
        // If the minutes is less than 30, we want to round up to the next half hour; so, we increase the halfOfTime by 1.
        if (timeToBePaid.getMinutes() <= 30 && timeToBePaid.getMinutes() != 0)
        {
            halfOfTime++;
        }
        // On the other hand, if it is over 30, that requires two increments -- one for the first 30 minutes, and one for the next round up.
        else if (timeToBePaid.getMinutes() > 30 && timeToBePaid.getMinutes() != 0)
        {
            halfOfTime+=2;
        }
    
        // So long as the final cost does not exceed the maximum charge, go ahead and return the price. 
        if (this.maxCharge > (float) (halfOfTime * (this.hourlyRate / 2)))
        {
            // Dividing hourlyRate by 2 as this is for every 30 minutes.
            return (halfOfTime * (this.hourlyRate / 2f));
        }
        
        // If the previous if condition was not satisfied, that means that the pricing was either the same or bigger than maximum charge, in both cases,
        // simply 
        return this.maxCharge;
    }
    
    /**
     * Method getNoLotsLeft
     * 
     * Helper method that validates the total capacity of the lot minus the amount of cars occupied; thus returning a no. lots left.
     *
     * @return short - no. lots left.
     */
    public short getNoLotsLeft()
    {
        return (short) (this.capacity - this.carsInLot.size()); // return the total capacity - the amount of cars/size of array. 
        // Worrying about negatives is not needed (i.e adding a math.abs() method) as the capacity will always be larger or the same size as the amount
        // of cars in the array.
    }
     
    /**
     * Method carEnters
     * 
     * Non-static void method takes in an input, a EddieCar object to add to the ArrayList. Method determines whether or not the no. cars has not 
     * exceeded the maximum capicity before adding a car as you cannot park a car with no more lots left. 
     * If no lots are available, the message is outputted to the user.
     *
     * @param car - EddieCar Object - Represents the object the user adds to the carsInLot array.
     * @param time - EddieTime Object - Represents the exact time when the car entered the lot.
     */
    
    public void carEnters(EddieCar car, EddieTime time)
    {
        // The reason I am nesting if statements together instead of using an && operator is because I want unique error statements for each potential
        // cause. I.e if the lot is open but the car is already in the lot, it is undesirable to output "lot closed and/or car is already in lot".
        
        // Step 1. Check if the car is in the lot already.
        if (!isCarInLot(car)) // if the car is not in the lot, then it is able to enter. Trying to enter a lot when you are already in it should be avoided.
        {
            // Step 2. Check if the lot is open.
            if (isLotOpen(time))
            {
                // Step 3. Check if there is room for another car in the lot.
                if (this.carsInLot.size() < this.capacity)
                {
                    // Ensure that the car's parking spot is set in this EddieParkingLot object. This allows us to ensure that when a car leaves, it leaves
                    // out of the lot it entered in.
                    car.setParkingSpot(this);
                    
                    // Now that it is confirmed the car is under the right conditions to enter the lot securely, add the car to the array of cars.
                    // This array, carsInLot, represents all the cars in the parking lot.
                    this.carsInLot.add(car); // Add the EddieCar object to the arraylist.
                    this.currentCarCount++; // Increase the total car count by 1.
                    car.setEnteringTime(time); // This is used to calculate the difference when determining how much to pay when the car leaves.
                    
                    // Output
                    System.out.println("🚗 " + car.toString() + " enters Lot " + this.lotNumber + " at " + time.toString() + ". No. Lots Left: " + this.getNoLotsLeft() + ".");
                }
                else
                {
                    // Lot is full.
                    
                    // Output
                    System.out.println("\n🚗 " + car.toString() + " arrives at Lot " + this.lotNumber + " at " + time.toString() + ", but the lot is full.\n"
                    + car.toString() + " is unable to get in.");
                }
            }
            else
            {
                // Lot is closed.
                
                // Output
                System.out.println("🌙 Parking Lot " + this.lotNumber + " is closed.\n═══════════════════\n• OPEN: 6am\n• CLOSED: 11pm\n═══════════════════\n");
            }
        }
        else
        {
            // Car already in parking lot
            System.out.println("\n❌ Error, " + car.toString() + " is already in the parking lot.");
        }
 
    }
    
    /**
     * Method carLeaves
     * 
     * Non-static void method takes in an input, a EddieCar object to remove from the ArrayList. Method determines whether or not the object to be removed
     * is in the ArrayList prior to removing it; if it is not inside, it will not be removed and the method will terminate.
     *
     * @param car - EddieCar - The object to be removed from the ArrayList, carsInLot.
     */
    public void carLeaves(EddieCar car, EddieTime time)
    {       
        // Check that the car is in the lot before removing; we don't want to remove a car thats not in the lot.
        if (isCarInLot(car)) // car has to be in the lot be removed lol
        {           
            // Logic check: It is undesired to remove an object of the arraylist if it is not already in it.
            // To solve this, we call the method inside the if condition itself; if the object, car, is not inside the arraylist, the remove() method
            // will return false. Otherwise, we know the object is inside the arraylist and thus can proceed to remove it.  
            if (this.carsInLot.remove(car))
            {
                // Once the car leaves the parking lot, it is no longer in a parking lot; so, set the parkSpot to be null so the car object can be reused.
                // if this line was omitted, then a car that left Lot 1 and tried to enter Lot 2 would throw an error as it was previously in Lot 1 regardless
                // if it left or not.
                car.setParkingSpot(null);
                // Lower the car count by 1.
                this.currentCarCount--;
                
                if (car.getPermit()) // helper method returns a boolean
                {
                    // Output with no price to be paid.
                    System.out.println("\n🚗 " + car.toString() + " leaves lot at " + time.toString() + ". No. Lots Left: " +
                    this.getNoLotsLeft());
                }
                else
                {
                    // Calculate price as no permit is present.
                    this.revenue += this.calcCost(car.getEnteringTime(), time);
                    
                    // Output
                    System.out.printf("\n🚗 %s leaves Lot %d at %s and paid $%.2f. No. lots left: %d", car.toString(), this.lotNumber, time.toString(), this.calcCost(car.getEnteringTime(), time), this.getNoLotsLeft());
                }
            }
        }
        // If the car is in a lot but just not this one, output that they are in the wrong lot.
        else if (car.getParkingSpot() != null)
        {
            // Output
            System.out.println("❌ Error, " + car.toString() + " is not in this lot.");
        }
        // If the car is simply not in any lot, output that they are not in the lot.
        else
        {
            // Output
            System.out.println("❌ Error, " + car.toString() + " is not in any lot.");
        }
    }
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    // Getters and Setters
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method getRevenue
     * 
     * Method returns the revenue of this object.
     *
     * @return float
     */
    public float getRevenue()
    {
        return this.revenue;
    }
    
    /**
     * Method getHourlyRate
     * 
     * Method returns the hourly rate of this object.
     *
     * @return float
     */
    public float getHourlyRate()
    {
        return this.hourlyRate;
    }
    
    /**
     * Method getMaxCharge
     *
     * @return The return value
     */
    public float getMaxCharge()
    {
        return this.maxCharge;
    }
    
    /**
     * Method setMaxCharge
     * 
     * Method sets this.maxCharge to a new value by user.
     *
     * @param MC - represents the new user-inputted max charge.
     */
    public void setMaxCharge(float MC)
    {
        this.maxCharge = MC;
    }
    
    /**
     * Method setHourlyRate
     * 
     * Method sets a new hourly rate by user and ensures it is less than or equal to the max charge.
     *
     * @param HR - the hourly rate.
     */
    public void setHourlyRate(float HR)
    {
         // It is undesired to let the hourly rate from the user exceed the maximum charge, so, create a if structure that notifies the user if they have
         // inputted a value over the max charge and set the new hourly rate to the max charge.
         if (HR > this.maxCharge)
         {
             System.out.printf("\nHourly rate of $%.2f/hr exceeds maximum charge of $%.2f; hourly rate has been set to max charge instead.", HR, this.maxCharge);
             this.hourlyRate = this.maxCharge;
         }
         else
         {
             // Otherwise, as long as HR <= this.maxCharge, let the hourly rate be the value passed by the user.
             this.hourlyRate = HR;
         }
    }
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    
    // END OF PROGRAM  
}