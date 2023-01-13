
/**
 * EddieNode class holds three instance variables:
 * cargo - a variable holding an EddiePlayer object.
 * next - the next EddieNode
 * prev - the previous EddieNode
 * 
 * This next and prev relationship with the current node establishes a pointer in both directions; thus, it can be used for doubly linked lists.
 * 
 * NOTE: The following EddieNode class is tailored for the ICS4U BAT assignment; it is not a node class that should be utilised and implemented
 * for general use. Hence, all methods and instance variables are modified to suit the functionality of the BAT game rather than all programs.
 *
 * @author (Eddie Gao)
 * @version (12/02/2022)
 */
public class EddieNode 
{
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private EddiePlayer cargo;
    private EddieNode next;
    private EddieNode prev;
    
    
    // CONSTRUCTORS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    /**
     * EddieNode Constructor
     * 
     * A default constructor that takes no parameters and sets instance variables to a logical value.
     * In this case, all instnace variables are set to null as they are not primitives; instead, they are of an Object data type.
     * For instance, cargo is an EddiePlayer object, next and prev are EddieNode objects.
     *
     */
    public EddieNode() // <- no param. therefore default constructor
    { 
        // set all instance variables to nul
        cargo = null;
        next = null;
        prev = null;
    }
    
    /**
     * EddieNode Constructor
     * 
     * An overloaded constructor that takes one parameter, ep, and sets that to the cargo of the node.
     * Constructor should be used when the next EddieNode and previous EddieNode objects are unknown.
     *
     * @param ep - EddiePlayer - represents a player in the BAT game and is set to the cargo of this node.
     */
    public EddieNode(EddiePlayer ep) 
    {
        cargo = ep;
        next = null;
        prev = null;
    }
    
    /**
     * EddieNode Constructor
     * 
     * An overloaded constructor that takes two parameters, ep and n, and sets them to the cargo and the next EddieNode respectively.
     * Constructor should be used when the previous EddieNode is unknown.
     *
     * @param ep A parameter
     * @param n A parameter
     */
    public EddieNode(EddiePlayer ep, EddieNode n) 
    {
        cargo = ep;
        next = n;
        prev = null;
    }
    
    /**
     * EddieNode Constructor
     * 
     * An overloaded constructor that takes in three parameters to set all three instance variables: ep, n, and p. In this order, these parameters
     * set the cargo, next, and prev instance variables.
     *
     * @param ep A parameter
     * @param n A parameter
     * @param p A parameter
     */
    public EddieNode(EddiePlayer ep, EddieNode n, EddieNode p) 
    {
        cargo = ep;
        next = n;
        prev = p;
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method toString
     *
     * @return String - String representation of the node by simply calling the cargo (EddiePlayer)'s toString() method to receive information
     * about what the node carries as a cargo. In terms of the BAT game, it is unnessecary to represent, through a String, the next and prev EddieNodes.
     * 
     */
    public String toString() 
    {
        return "" + cargo.toString();
    }
    
    // GETTERS AND SETTERS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method getCargo
     * 
     * Method returns the cargo of the EddieNode, i.e, returning an EddiePlayer object that the cargo holds.
     *
     * @return EddiePlayer
     */
    public EddiePlayer getCargo()
    {
        return this.cargo;
    }
    
    /**
     * Method getNext
     * 
     * Returns the next EddieNode that this EddieNode points to.
     *
     * @return EddieNode
     */
    public EddieNode getNext()
    {
        return this.next;
    }
    
    /**
     * Method getPrevious
     *
     * Returns the prev EddieNode that this EddieNode points back to.
     * 
     * @return EddieNode
     */
    public EddieNode getPrevious()
    {
        return this.prev;
    }
    
    /**
     * Method getNext
     * 
     * getNext method takes a boolean, isReversed, that depicts whether the EddieNode's pointing should be reversed or not (i.e, swap prev and next).
     *
     * @param isReversed - boolean - represents the rotation of the BAT game's order. If the order is reversed, then the prev EddieNode is returned
     * and expressed as the "next" EddieNode. Otherwise, simply return the next EddieNode that this EddieNode points to.
     * 
     * @return EddieNode
     */
    public EddieNode getNext(boolean isReversed)
    {
        if (isReversed) return this.prev; // If it is true that it should be reversed, return the previous EddieNode. This treats the previous EddieNode
        // as the next node.
        else return this.next; // If isReversed is false, i.e, the pointing is not reversed, simply return next EddieNode.
    }
    
    /**
     * Method getNextPlayer
     * 
     * getNext method takes a boolean, isReversed, that depicts whether the EddieNode's pointing should be reversed or not (i.e, swap prev and next).
     * With this, the cargo of that pointed EddieNode is returned as the cargo holds the EddiePlayer object; i.e the next player.
     *
     * @param isReversed - boolean - represents the rotation of the BAT game's order. If the order is reversed, then the prev EddieNode is returned
     * and expressed as the "next" EddieNode. Otherwise, simply return the next EddieNode that this EddieNode points to.
     * 
     * @return EddiePlayer
     */
    public EddiePlayer getNextPlayer(boolean isReversed)
    {
        if (isReversed) return this.prev.getCargo(); // getCargo() returns the EddiePlayer object.
        else return this.next.getCargo();
    }
    
    /**
     * Method setNext
     * 
     * Method sets this.next to a new value by user.
     *
     * @param n - EddieNode - a new EddieNode object that the user desires to be the next EddieNode 
     */
    public void setNext(EddieNode n) {
        next = n;
    }
    
    /**
     * Method setPrevious
     * 
     * Method sets this.prev to a new value by user.
     *
     * @param n - EddieNode - a new EddieNode object that the user desires to be the previous EddieNode 
     */
    public void setPrevious(EddieNode p)
    {
        prev = p;
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    // END OF PROGRAM
}
