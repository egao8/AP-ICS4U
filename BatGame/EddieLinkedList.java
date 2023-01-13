import java.util.ArrayList;


/**
 * EddieLinkedList
 * 
 * A doubly-linkedlist; meaning -- it can travel both to the previous or next node pointed to.
 * NOTE: This doubly-linkedlist fulfills a purpose solely for the BAT assignment; thus, certain data types and methods are modified from
 * traditional standards to fit the needs of the BAT game in a manner that is most efficient.
 * 
 * Methods:
 * - A default and overloaded constructor(s) that set a byte length and EddieNode head to various logical default values and/or inputted values.
 * - addPlayer() -- adds a player in the form of a EddieNode with a EddiePlayer cargo that is specified as an input by user.
 * - deleteHead() -- deletes the head EddieNode in the linkedlist.
 * - toString() -- returns a String representation of the linkedlist.
 * - isEmpty() -- returns whether or not the linkedlist is empty through a boolean value.
 * - Getters and Setters for instance variables (head, length, etc.)
 * 
 *
 * @author (Eddie Gao)
 * @version (16/10/2022)
 */
public class EddieLinkedList
{
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private EddieNode head; // the head of the linked list is an EddieNode at index of 0. 
    private EddieNode tail; // the tail is the last EddieNode in the 
    private byte length; // Though it is plausible that linkedlists hold a length greater than the byte range (i.e 128), this linked list
    // serves a purpose solely for the BAT game; in which each EddieNode holds an EddiePlayer object. The amount of players will never exceed 128; so,
    // the length is determined as a byte. 
    
    /**
     * EddieLinkedList Constructor
     * 
     * A default constructor that takes no parameters and sets instance variables to a logical value.
     *
     */
    public EddieLinkedList() // <- no param. = default constructor
    {
        length = 0; // By default, there have been nodes in the linkedlist; so, the length is set to 0.
        head = null; // Set the head to null to represent that the Linkedlist is empty.
    }
    
    // Note: No overloaded constructors are written as the BAT game requires only a default linkedlist to be instantiated.
    
    /**
     * Method add
     * 
     * Method that adds an EddieNode to the EddieLinkedList by receiving an inputted EddiePlayer object and then creating a node that carries 
     * the EddiePlayer object as it's cargo. If the ll is empty, it sets the head to hold the player.
     * The method will further ensure a closed-cyclic structure by ensuring no node, previous, or next, will point to null.
     *
     * @param player - EddiePlayer - represents a player that the user desires to be added.
     */
    public void add(EddiePlayer player)
    {
        if (isEmpty()) // Firstly, the head is checked to see if it is null.
        {
            // If it is null -- meaning the ll is empty:
            head = new EddieNode(player); // Instantiate a new EddieNode object with the player parameter as the head.
            
            // Create an infinite cycle where the head points to itself in both directions.
            head.setPrevious(head);
            head.setNext(head);
            
            // Essentially; when the first EddieNode is inputted; this if structure determines a robust way to ensure that the EddieNode is the head
            // and that it creates a loop where the node never points to null in any direction; this is desired as it emulates the cyclic structure
            // of the BAT game through a doubly-linked list.
        }
        else // If the linked list has EddieNodes currently in it, add a new Node with the player value inputted by user.
        {
            // star
            // Create a "closed-cycle" dll structure where every node will point (in both directions) to another node without any node pointing to null.
            EddieNode tail = head.getPrevious();
            tail.setNext(new EddieNode(player, head, tail));
            head.setPrevious(tail.getNext());
        }
        
        // Because a new node has been added, increment the length of the dll by 1.
        length++;
    }
    
    /**
     * Method deleteHead
     * 
     * Method that deletes the head Node of the linkedlist without returning that node. Further, method ensures 
     * a closed-cyclic structure by connecting the previous node and next node of the head node (that will be deleted) to each other.
     * Essentially, the head.next will become the new head after the head has been deleted from the ll.
     *
     */
    public void deleteHead()
    {
        // If the ll is empty, it is impossible to delete the head (since it does not have a head).
        if (isEmpty())
        {
            // Therefore, output an error message and then end the method by returning nothing.
            System.out.println("Error -- cannot delete the head of the linkedlist if it is empty.");
            return;
        }
        
        // Create a temp node and set it to the head.
        // This is required to keep the head node AFTER it has been deleted.
        EddieNode temp = head;
            
        // set the head's previous node to the heads next node; essentially, this will ensure a closed-cyclic structure
        // as the two nodes that the head points to are now connected.
        this.head.getPrevious().setNext(head.getNext());
        // Now, set the new head to the "old/to-be-deleted" head's next pointed node.
        this.head = head.getNext();
        
        // Then, set the "new" head's previous node to the "old" head's previous -- fully enclosing the linkedlist after successfully deleting the head.
        this.head.setPrevious(temp.getPrevious());
        
        // A node has been deleted, decrement the length by 1.
        length--;
    }
    
    /**
     * Method toString
     * 
     * Returns a string representation of the linkedlist by printing all nodes (calling their toString())
     *
     * @return String
     */
    public String toString() 
    {
        // If the ll is empty, return a String declaring that to the console.
        if (isEmpty()) return "Empty";
        
        // Otherwise, set a temp EddieNode variable to the head.
        EddieNode n = this.head;
        
        String result = "";
        // Iterate through the ll.
        for (int i = 0; i < this.length; i++) 
        {
            // Add each node's toString() call to the result String.
            result += n.toString() + ", ";
            // go to the next node.
            n = n.getNext();
        }
        
        // return the final result.
        return result + " ";
    }
    
    // GETTERS AND SETTERS
    
    /**
     * Method length
     * 
     * Returns the length of the linkedlist as a byte.
     *
     * @return - byte
     */
    public byte length() 
    {
        return length;
    }
    
    /**
     * Method head
     * 
     * returns the head.
     *
     * @return EddieNode
     */
    public EddieNode head() 
    {
        return head;
    }
    
    /**
     * Method isEmpty
     * 
     * Returns a boolean statement as to if the linkedlist is empty or not (true = empty).
     *
     * @return boolean
     */
    public boolean isEmpty()
    {
        return length == 0;
    }
    
    /**
     * Method setHead
     * 
     * Sets the current this.head to a new EddieNode object.
     *
     * @param h - EddieNode - the new this.head that is specified by the user.
     */
    public void setHead(EddieNode h)
    {
        this.head = h;
    }
    
    
    // END OF PROGRAM
}
