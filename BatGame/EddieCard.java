    

/**
 * EddieCard class.
 * 
 * Class provides functionality of a standard card with a rank and a suit.
 * 
 * Methods:
 * Default and overloaded constructors
 * toString - returns a String representation of the card
 * getRank() - returns the numerical value of the card's rank
 * getSuit() - returns a numerical value representing the card's suit.
 *
 * @author (Eddie Gao)
 * @version (12/02/2022)
 */

public class EddieCard
{
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private byte bytRank, bytSuit; // Final because the value of a card should never be changed. 
    
    // String representations of encoded card suits.
    // 0 -> Clubs
    // 1 -> Diamonds
    // 2 -> Hearts
    // 3 -> Spades
    private final static String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    
    // String represnetations of encoded card ranks.
    // 11, 12, 13, and 1 are represented as Jack, Queen, King, and Ace respectively.
    // Index at 0 will be null because no card can be generated with a value of 0 in a traditional deck.
    private final static String[] RANKS = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    
    /**
     * EddieCard Constructor
     * 
     * Default constructor that takes no parameters and sets instance variables to a logical default
     * value.
     *
     */
    public EddieCard() // <- No param. default constructor
    {
        // the rank of the card is -- at default -- set to 2 as it is the lowest rank among
        // all card-based games. bytRank is not set to Ace/1 as the value will vary -- especially
        // in the BAT game.
        this.bytRank = 2;
        // Set the suit to a base value of 0; in this case being clubs.
        this.bytSuit = 0;
    }
    
    /**
     * EddieCard Constructor
     * 
     * Overloaded constructor takes two parameters, int cardno and int suit and sets them to
     * the instance variables of the EddieCard class. 
     *
     * @param cardno - int - represents the card number/rank of the EddieCard object. Expressed as an int and then casted to byte
     * // for ease of use (i.e no need to cast as byte in the call) whilst preserving the efficiency of this method.
     * @param suit - int - represents the suit value (Clubs, Diamonds, Hearts, Spades) of the EddieCard object.
     */
    public EddieCard(int cardno, int suit)
    // Though bytRank and bytSuit are expressed in bytes for the efficiency of their data type, 
    // the parameters are set as ints and then casted into bytes for ease of use for the user.
    {
        // If the number is not negative and greater than 13, cap it to 13.
        // Since, a card number can not surpass 13 (king)
        if (!(cardno < 0) && cardno > 13) cardno = 13;
        // Same applies with suit number.
        if (!(suit < 0) && suit > 3) suit = 3;
        
        // Cant have a negative card and suit number so absolute value it.
        this.bytRank = (byte) Math.abs(cardno);
        this.bytSuit = (byte) Math.abs(suit);
    }
    
    /**
     * EddieCard Constructor
     * 
     * An overloaded constructor that takes two String parameters and sets their String value to the correct rank and suit.
     *
     * @param n - String - a representation of the EddieCard's rank in the form of a String instead of a digit (i.e byte)
     * @param s - String - a representation of the EddieCard's suit rather than a numerical value that points to a specific suit element.
     */
    public EddieCard(String n, String s) 
    {      
        for (byte i = 0; i < 13; ++i) {
            if (RANKS[i].toLowerCase().equals(n.toLowerCase())) {
                this.bytRank = (byte) (i + 1);
                i = 13;
            }
        }
        
        for (byte i = 0; i < 4; ++i) {
            if (SUITS[i].toLowerCase().equals(s.toLowerCase())) {
                this.bytSuit = i;
                i = 4;
            }
        }
    }
    
    /**
     * Method toString
     * 
     * String representation of the EddieCard object; displays the rank and the suit of the card.
     *
     * @return - String
     */
    public String toString()
    {
        // Based off a String representation of each card, return that representation in this method.
        return RANKS[this.bytRank] + " of " + SUITS[this.bytSuit];
    }
    
    // GETTERS AND SETTERS
    // ====================================================================================================================
    /**
     * Method getRank
     * 
     * Returns a byte value representing the rank of the EddieCard object.
     *
     * @return byte
     */
    public byte getRank()
    {
        return this.bytRank;
    }
    
    /**
     * Method getSuit
     * 
     * Returns a byte value representing the suit of the EddieCard Object that is correlated with the String representation (i.e 0 = Clubs)
     *
     * @return byte
     */
    public byte getSuit()
    {
        return this.bytSuit;
    }
    
    // =====================================================================================================================
    // END OF PROGRAM
}