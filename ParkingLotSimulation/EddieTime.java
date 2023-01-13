/**
 * EddieTime class represents a "time of day" (e.g., 10:15) with attributes called hours which represents hours, and minutes which represents minutes.
 * Both instance variables/attributes are declared as privatised integers. The class upholds the ability to handle negative values as times.
 * 
 * Methods pertain to:
 * - A default constructor
 * - Overloaded constructors
 * - A constructor that inputs a float value.
 * - A difference method that returns the difference between two times
 * - A calcSum method that returns the sum between two OR an array of times
 * - An equals() overridden method that checks equality between two times.
 * - A toString() overridden method that outputs time in a hours:minutes: format logically.
 * - Getters and Setters for Encapsulation.
 *
 * @author (Eddie Gao)
 * @version (9/29/2022)
 */

public class EddieTime
{
    // Step 1. Initialise and declare instance variables
    // ═══════════════════════════════════════════════════════════════════
    private int hours, minutes;
    
    // CONSTRUCTORS 
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    // Step 2. Create a default constructor    
    /**
     * Default EddieTime Constructor
     * 
     * This constructor accepts no parameters and sets every instance variable to a default value; that value being 0.
     * To create a default constructor, we ensure no parameters are present.
     */
    public EddieTime()
    {
        // In the default value, assume hours and minutes are both 0 -- no time has elapsed.
        this.hours = 0;
        this.minutes = 0;
    }
    
    // Step 3. Create a constructor that now accepts two parameters. This constructor allows the user to alter the instance variables.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    /**
     * EddieTime Constructor
     *
     * An overloaded constructor method that takes in two parameters, hours, and minutes as integers. Method handles negative values for future use.
     *
     * @param hours - int - value is used to assign instance variable, hours.
     * @param minutes - int - value is used to assign instance variable, minutes.
     * 
     */
    public EddieTime(int hours, int minutes)
    {
        // N.B: Because parameter names and instance variable names are of congruency, must add keyword "this."
        
        // To avoid irregular integer division errors when handling a negative, cast the calculation as a float (i.e, 60f) and then re-cast as an int.
        // This provides a wider success range for negative times.
        this.hours = (int) (hours + minutes / 60f);
        
        // As long as the inputted amount of hours is greater than or equal to 0 and the no. minutes is less than 0, i.e a negative, convert the no.
        // of minutes to an absolute value by using the static Math.abs() method.
        if (hours >= 0 && minutes < 0) 
        {
            // Explanation: Following BEDMAS
            /*
             * 1st: Absolute the negative values in minutes (hence the if condition above)
             * 2nd: mod the positive number by 60, subjecting it between 0-59 - a suitable data range for a logical hours:minutes format.
             * 3rd: subtract minutes from 60, therefore, the minutes are reversed to become a logical representation of time (e.g -20 becomes 40).
             */
            this.minutes = 60 - Math.abs(minutes) % 60;
        }
        else
        {
            this.minutes = minutes % 60; // Otherwise, simply find the remaining minutes by calculating the modulus of 60.
        }   
    }
    
    // Step 3.5 Create a constructor that accepts only minutes for the user's customisation and options.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    /**
     * EddieTime Constructor
     *
     * This overloaded Time constructor prompts the user a choice of only minutes; in application, such a feature could yield for more ease of use
     * depending on various scenarios of implementation
     *
     * @param minutes assigns both the total amount of hours and minutes in instance variables: hours, minutes.
     */
    public EddieTime(int minutes)
    {
        // Now, assign our instance variables to whatever values set by the user.
        this.hours = minutes / 60; 
        
        // Refer to previous constructor for reasoning.
        if (hours >= 0 && minutes < 0) 
        {
            this.minutes = 60 % Math.abs(minutes);
        }
        else
        {
            this.minutes = minutes % 60; 
        }
    }
    
    // Step 4. Create a constructor that accepts a float time, e.g 9:35, and converts it to hours and minutes.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    /**
     * EddieTime Constructor
     *
     * This overloaded constructor, uniquely, converts a float time to hours and minutes to be interpreted as such.
     * Any digits past the decimal place in the tenths and hundreths digit will be considered and subsequently converted into minutes
     * whereas the ones digit of the time parameter are converted into an integer hour value.
     *
     * @param time - float - represents a time and is used to initialize the instance variables. 
     * (e.g., a parameter value of 9.5 may either represent the time 9:30am or a 9 hours and 30 minutes time range)
     * 
     */
    public EddieTime(float time)
    {    
        this.hours = (int) time; // Though a byte cast would be more efficient, casting as an int adheres to the instructions of having hours be an int.
        
        // In order to find the minutes, isolate only the tenths and hundreths decimal places by subtracting the time inputted by the user
        // with the same value but rounded as an integer and then * the result by 60. E.g 9:35 - 9 (rounded down) = 35 = amount of minutes.
        this.minutes = (int) (60 * (time - (int) time)); 
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method formatPadding
     * 
     * Non-static non-void helper method fulfills formatting specifications of hour:time display by determining if this.minutes is a single digit; if so,
     * method will return a String with an extra 0 to account for the space occupied of the minutes value.
     *
     * @return String - Return a string with the correct formatting for an hour:time display. E.g minutes = 8 >>> x:08.
     */
    public String formatPadding()
    {
        // An issue occurs when the program outputs a time with only one minutes digit, e.g 9:05 will instead print 9:5.
        // To solve this, we use an if condition when minutes is less than 2 digits by adding an extra 0 before.
        // However, we must find the absolute val. of minutes as a negative minutes value (a.k.a under 10) that contains two digits will still
        // be outputted with one extra 0 (e.g -10:0-47) which is undesired.
        if (Math.abs(this.minutes) < 10)
        {
            return "%d:0%d";
        }
        
        return "%d:%d"; // If minutes is greater or equal to 10, a.k.a, a double digit, there is no need to add an additional zero; instead, simply
        // return the format in hours:minutes.
    }
    
    // Step 5. Create a toString method by declaring it a non-static variable that returns a string with all of the object's data.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    /**
     * Method toString
     *
     * This special method allows us to output info about the object efficiently and logically.
     *
     * @return String representation of hours and minutes from the Time object that the method is called upon.
     * 
     */
    public String toString()
    { 
        return String.format(this.formatPadding(), this.hours, this.minutes); // first, check if minutes requires padding, then output.
    }
    
    // Step 6. Create a non-static method which returns a time object between two other time objects.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    /**
     * Method difference
     * 
     * This method compares two EddieTime objects, t2, and the one called upon and returns the difference in hours and minutes as a newly constructed EddieTime object.
     *
     * @param t2 - EddieTime - A second EddieTime object that is utilised to subtract the current EddieTime object and find the difference
     * @return EddieTime - A EddieTime object with the difference in hours and minutes.
     * 
     */
    public EddieTime difference(EddieTime t2) 
    {
        // Call the constructor and set the instance variables to the difference.
        return new EddieTime(t2.hours - this.hours, t2.minutes - this.minutes);
    }
    
    /**
     * Method calcSum
     * 
     * A non-static method that will leverage two EddieTime objects and return a newly constructed EddieTime object with hours and minutes set as the sum
     * of the two objects.
     * 
     * @param t2 - EddieTime - A Second EddieTime object that has instance variables, hours and minutes which is used to calculate the sum of two EddieTime objects.
     * @return EddieTime - A EddieTime object with the sum of hours and minutes between two objects. Returned by instantiating a new object with constructor.
     */
    public EddieTime calcSum(EddieTime t2)
    {
        // Call the constructor and set the instance variables to the sum.
        return new EddieTime(t2.hours + this.hours, t2.minutes + this.minutes);
    }
    
    /**
     * Method calcSum
     * 
     * An overloaded calcSum() non-static method that will take in an array of EddieTime objects, add the hours and minutes of each object to a total
     * sum variable, and after all all times have been summed, will return a newly constructed EddieTime object with the sum of all hours and minutes.
     *
     * @param arrTotal - EddieTime[] - an array of EddieTime objects to be copied and used in order to calculate the sum of all elements (i.e objects)
     * in the array.
     * @return EddieTime - A EddieTime object with the sum of hours and minutes between all objects in the array, arrTotal.
     */
    public EddieTime calcSum(EddieTime[] arrTotal)
    {
        // Step 1. Declare and initialise local variables.
        
        // Flexibility and easy expansion can often be prioritised over memory efficiency. For the most flexibile yet efficient data types, I am choosing
        // to set the total hours as an integer as it is able to hold values to the hundreds and thousands (which is necessary for larger parking lots)
        // whereas setting the total minutes as a short (range of 32000) is justified because upon construction of an EddieTime object, any value of
        // this.minutes > 59 will be passed as an hour; thus, a short will sufficiently hold the maximum amount of minutes AFTER the object is constructed.
        // However in the constructors, this.minutes must be an integer value as it has not yet been converted to an hour; rather, it is simply a user input. 
        int intTotalHours = 0;
        short shrTotalMinutes = 0;
        
        // Step 2, create a simple for loop and add all the values to the total variables.
        for (int i = 0; i < arrTotal.length; i++)
        {
            intTotalHours += arrTotal[i].hours;
            shrTotalMinutes += arrTotal[i].minutes;
        }
        
        // Return a newly construced EddieTime object with the sum of all times in the array, arrTotal.
        return new EddieTime(intTotalHours, shrTotalMinutes);
    }
    
    // Step 7. Create a equals() method.
    // ══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    /**
     * Method equals
     *
     * Method checks for equality by comparing the hours and minutes to a second EddieTime object's hours and minutes. 
     * If congruency is determined, return true, otherwise, return false.
     * 
     * @param t2 - EddieTime object - Used to compare current EddieTime object with a second one.
     * @return boolean - identifying whether both EddieTime objects are equal (true) or not equal (false).
     * 
     */
    public boolean equals(EddieTime t2)
    {
        // If the hours and minutes are exactly the same, equality has been met.
        return (this.hours == t2.hours && this.minutes == t2.minutes);
    }
    
    
    // Getters and Setters
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method getHours
     * 
     * Returns the hours instance variable of the EddieTime object.
     *
     * @return - this.hours - int
     */
    public int getHours()
    {
        return this.hours;
    }
    
    /**
     * Method getMinutes
     * 
     * Returns the minutes instance variable of EddieTime object.
     *
     * @return - this.minutes - int
     */
    public int getMinutes()
    {
        return this.minutes;
    }
    
    /**
     * Method setHours
     * 
     * Sets this.hours to a new integer value.
     *
     * @param h - int - the desired new hours set by user.
     */
    public void setHours(int h)
    {
        this.hours = h;
    }
    
    /**
     * Method setMinutes
     * 
     * Sets this.minutes to a new integer value.
     *
     * @param m - int - the desired new minutes set by user.
     */
    public void setMinutes(int m)
    {
        this.minutes = m;
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    // END OF PROGRAM
}  