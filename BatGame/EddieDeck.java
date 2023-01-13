// Import libraries
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that provides the functionality of a standard 52-card deck -- along with the ability to manipulate the cards inside.
 * 
 * Methods
 * - Default and overloaded constructors
 * - randomByte - generates a random byte value between two numbers.
 * - shuffle - shuffles the deck
 * - swapCards - swaps two cards in the deck given their indexes.
 * - popCard - removes and returns the first card in the deck.
 * - cardAt - returns the card at a specified index.
 * - length - returns the length of the deck.
 * 
 * NOTE: This class fulfills a purpose solely for the BAT assignment; thus, certain data types and methods are modified from
 * traditional standards to fit the needs of the BAT game in a manner that is most efficient.
 *
 * @author (Eddie Gao)
 * @version (12/02/2022)
 */

public class EddieDeck
{
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    
    // An ArrayList of EddieCard object
    private ArrayList<EddieCard> cards;
    private final static byte NO_CARDS_IN_DECK = 52; // A constant that represents the number of cards in a deck -- that being 52.
    
    /**
     * Deck Constructor
     * 
     * A default constructor that takes no parameters and sets instance variables to a logical value.
     *
     */
    public EddieDeck()
    {
        // By default, there are 52 cards in a deck (as expressed by the constant); so, that is set as the size of the this.cards arraylist.
        this.cards = new ArrayList<EddieCard>(NO_CARDS_IN_DECK);
    }
    
    /**
     * EddieDeck Constructor
     * 
     * Overloaded constructor sets the this.cards to a deck of ArrayList<EddieCard> inputted by the user.
     *
     * @param deck - an ArrayList of EddieCards specified by the user.
     */
    public EddieDeck(ArrayList<EddieCard> deck)
    {
        this.cards = deck;
    }
    
    /**
     * Deck Constructor
     * 
     * Overloaded constructor takes a boolean parameter specified by the user as to if the ArrayList of EddieCards should be populated with
     * all the cards of a regular 52-card deck. If the populate boolean is true, this.cards will be filled with all the cards of a deck.
     *
     * @param populate - boolean - a specified boolean value by the user that indicates whether or not the this.cards should be populated with
     * EddieCard elements (populate = true)
     */
    public EddieDeck(boolean populate)
    {
        // By default, there are 52 cards in a deck (as expressed by the constant); so, that is set as the size of the this.cards arraylist.
        this.cards = new ArrayList<EddieCard>(NO_CARDS_IN_DECK);
        
        // if the user specified the deck to be populated:
        if (populate)
        {
            // 13 represents the 13 unique cards in a standard deck. Starting i at 1 because there is no such thing as a card with a rank of 0.
            for (byte i = 1; i <= 13; i++) 
            {
                // Byte value for both for loops (x, and i) because the condition is lesser than 128.
                for (byte x = 0; x < 4; x++) // 4 represents the 4 unique suits in a standard deck.
                {
                    cards.add(new EddieCard(i, x)); // Add a card to the this.cards arraylist.
                }
            }
        }
    }
    
    /**
     * Method randomByte
     * 
     * Helper method that, given two digits -- a higher and lower one -- will return a random byte value between them.
     *
     * @param low - int - between two numbers, the low parameter represents the smaller value.
     * @param high - int - between two numbers, the low parameter represents the greater value.
     * @return byte
     */
    private static byte randomByte(int low, int high) // Parameters are set as ints for the convenience of the user not having to cast; however,
    // for the most efficient data type; they are casted in the method body as bytes.
    {
        // If the low value is -- for some reason -- higher than the high value, account for the wrong difference.
        if (low > high)
        {
            low = (byte) high + low; // the low value is set as the sum of the high and low value to account for the difference.
            high = (byte) low - high; 
            low -= low;
        }
        
        // use the random class to generate and return a random value.
        return (byte) new Random().nextInt((high - low) + low);
    }
    
    /**
     * Method shuffle
     * 
     * Helper method utilises the randomByte() method to randomly shuffle the cards in a deck by swapping random indexes of the this.cards (arraylist).
     *
     */
    public void shuffle()
    {
        // Iterate through the entire arraylist (this.cards)
        for (byte i = 0; i < this.cards.size(); i++)
        {
            // Swap the ith element with a random index.
            this.swapCards(i, randomByte(this.cards.size(), i));
        }
    }
    
    /**
     * Method swapCards
     * 
     * Helper method swaps two elements of the this.cards arraylist by retrieving two indexes from the user and swapping the elements of them.
     *
     * @param i - byte - an index to be swapped with another
     * @param j - byte - an index to be swapped with another
     */
    private void swapCards(byte i, byte j) // byte as it is the most efficient data type given the fact that a deck of cards is limited to 52 cards
    // which is less than the byte range (128).
    {
        // Keep the EddieCard at index i when we set that index to the EddieCard at index j.
        EddieCard temp = cards.get(i);
        
        // Swap cards.
        this.cards.set(i, this.cards.get(j));
        this.cards.set(j, temp);
    }
    
    public EddieCard popCard()
    {
        // Pop the first element of the this.cards arraylist to allow this method to be as widely useable as possible.
        // For e.g, this method will work regardless if there is only one element in the arraylist or if there are n elements; the same fact cannot
        // be translated if the index that is popped is not 0.
        
        if (length() == 0) // Check if the this.cards arraylist is empty; if so, just return null and print that the deck is empty and unpoppable.
        {
            System.out.println("Error -- cannot pop a card if the deck is empty.");
            return null;
        }
        
        // Before removing the EddieCard, store it in a temporary popped variable in order to return it.
        EddieCard popped = this.cards.get(0);
        // After it is stored, remove the element.
        this.cards.remove(0);
        
        // return the popped card.
        return popped;
    }
    
    /**
     * Method cardAt
     * 
     * Helper method returns an EddieCard object from the cards (arraylist) at a specified index.
     *
     * @param i - byte - the index of the card that the user desires to be returned.
     * @return EddieCard
     */
    public EddieCard cardAt(byte i) // Most efficient data type; will never be more than 52 indexes in the this.cards.
    {
        return cards.get(i);
    }
    
    /**
     * Method length
     * 
     * Return the length of the cards (arraylist)
     *
     * @return byte
     */
    public byte length()
    {
        return (byte) cards.size(); // cast as byte as the size of the arraylist will never be greater than 52.
    }
    
    // END OF PROGRAM
}