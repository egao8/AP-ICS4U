// import libraries
import java.util.ArrayList;

/**
 * Player class stores information of a player in the BAT game with instance variables hand representing their cards, their status, and their name.
 * 
 * Methods:
 * -Default and overloaded constructors
 * -toString() returns a String representation of the EddiePlayer object with their hand, name, and status.
 * -addLetter() - adds a letter to their status and returns a boolean as to whether or not a letter could be added before reaching the same 
 * status as "BAT".
 * -clearHand -- clears the hand of the player
 * -replace -- replaces a card with a new card.
 * - Getters and setters
 *
 * @author (Eddie Gao)
 * @version (16/10/2022)
 */
public class EddiePlayer
{
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private ArrayList<EddieCard> hand; // hand represents an ArrayList of EddieCard's that the user can play.
    private String status, name;
    
    // Constants
    private final static byte NO_CARDS = 3; // The number of cards a player can have will always be three; thus, it will never change.
    private final static String FINAL_PHRASE = "BAT"; // This is the final phrase/status a player can receive as it will never change.
    
    /**
     * EddiePlayer Constructor
     * 
     * Default constructor takes no parameters and sets the instance variables to appropriate default values.
     *
     */
    public EddiePlayer()
    {
        this.hand = new ArrayList<EddieCard>(NO_CARDS); // hand will always be 3 cards; so, set it to that by default.
        this.status = ""; // a player will always have no letters by default
        this.name = "Guest Player"; // If no name is specified, the player is a guest player.
    }
    
    /**
     * EddiePlayer Constructor
     * 
     * Overloaded constructor however only the name is set to a inputted String value.
     *
     * @param name - String - a desired name for the player.
     */
    public EddiePlayer(String name)
    {
        this.hand = new ArrayList<EddieCard>(NO_CARDS); // hand will always be 3 cards; so, set it to that by default.
        this.status = ""; // a player will always have no letters by default
        this.name = name; 
    }
    
    /**
     * Method toString
     * 
     * returns String representation of the player's information: their name, their hand, and their status.
     *
     * @return String
     */
    public String toString()
    {
        // Step 1. Declare and initialise variables.
       String result = this.name + "'s hand:\n";

       // Step 2. Loop through each card in their hand and add it to the final print result.
       // Byte because the hand size will always be 3; a byte fulfills that with maximum efficiency.
       for (byte i = 0; i < hand.size(); i++)
       {
           result += ("(" + (i+1) + ") " + hand.get(i) + "\n");
       }
    
       // Step 3. return the final result.
       return (result + ("Current Letters: " + this.status) + "\n");
    
    }
    
    /**
     * Method addLetter
     * 
     * Helper method adds a letter to the players status in the order of B-A-T. Further the method, will return true if a letter can be added
     * without reaching BAT (meaning the player hasnt lost) and false if the player will reach BAT if this method is called (meaning they lost).
     *
     * @return boolean
     */
    public boolean addLetter()
    {
        // If the length of the status is less than the length of the final phrase (3) - 1, they are able to keep playing whilst adding a letter.
        // I.e if the length of the status = 2 (which is also the length of the final phrase - 1), that means they will lose upon adding a letter.
        if (this.status.length() < (FINAL_PHRASE.length()-1)) // final phrase is BAT.
        {
            // Add the corresponding character needed to form BAT at that specific length.
            this.status += FINAL_PHRASE.charAt(this.status.length());
            return true;
        }
        else
        {
            // Set the status to the final phrase as they have lost.
            this.status = FINAL_PHRASE;
            return false;
        }
    }
    
    /**
     * Method clearHand
     * 
     * Clears the EddiePlayer's hand.
     *
     */
    public void clearHand()
    {
        this.hand.clear();
    }
    
    /**
     * Method replace
     * 
     * Helper method removes a card from the hand and replaces it with a new card.
     *
     * @param oldCard - EddieCard - the card to be replaced with the newCard parameter
     * @param newCard - EddieCard - the card to replace the oldCard
     */
    public void replace(EddieCard oldCard, EddieCard newCard)
    {
        this.hand.remove(oldCard);
        this.hand.add(newCard);
    }
    
    /**
     * Method selectCard
     * 
     * Method returns a card from the hand at a specified index.
     *
     * @param index - byte - the index in which the card at that index is to be returned.
     * @return EddieCard
     */
    public EddieCard selectCard(byte index)
    {
        return this.hand.get(index);
    }
    
    // GETTERS AND SETTERS
    

    /**
     * Method getStatus
     * 
     * returns this.status
     *
     * @return String
     */
    public String getStatus()
    {
        return this.status;
    }
    
    /**
     * Method getName
     * 
     * returns this.name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Method setHand
     *
     * @param index - the 
     * @param card A parameter
     */
    public void addCard(EddieCard card)
    {
        this.hand.add(card);
    }
    
    // END OF PROGRAM
    
}
