import java.util.Scanner;
import java.util.ArrayList;


/**
 * EddieBat class sets up the BAT game by simulating all features of the game with instance variables, deck, usedCards, players, currentPlayer,
 * totalScore, roundCount, isReversed, playerLost and constants: MAX_TOTAL, MAX_PLAYERS, MIN_PLAYERS, NO_CARDS.
 * Class utilises EddiePlayer, EddieDeck, and EddieLinkedList class objects to simulate a turn-based cyclic game.
 * 
 * Methods:
 * Default constructor
 * clearScreen() -- clears the screen
 * menuSetup() -- sets up the menu selection choice
 * instructions() -- outputs instructions
 * startGame() -- starts the simulation of the game
 * printTotalScore() -- outputs total score
 * addPlayers() -- adds players specified by an input for amount of players and their names.
 * startRound() -- starts a round of BAT
 * replaceDeck() -- replaces deck with usedCards
 * playCard(EddieCard aCard) -- plays a card from currentplayers hand
 * pickUp() -- picks up a new card from deck
 * calculateMove(EddieCard aCard) -- calculates the move for special cards
 * determineScoring(byte value) -- determines how to score using a card
 * drawHand() -- draws a new hand for the current player
 * isWinner() -- returns a boolean as to if there is a winner
 * eliminatePlayer() -- deletes a player from the players to eliminate them from the game permanently.
 * promptCardSelection() -- prompts user for an input as to what card to play
 * reset() -- resets the round
 * nextTurn() -- sets the current player to the next player in rotation
 * endRound() -- returns a boolean as to if the round must end or not.
 * 
 *
 * @author (Eddie Gao)
 * @version (16/10/2022)
 */
public class EddieBat
{  
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    
    // Instance variables
    private EddieDeck deck; // The deck that all players will draw cards from
    private ArrayList<EddieCard> usedCards; // An arraylist of usedCards -- the cards played in every round.
    private EddieLinkedList players; // A linkedlist of all players in the rotation of the game's direction.
    private EddiePlayer currentPlayer; // the current player that is playing a card.
    private byte totalScore; // the total score of the round -- expressed in byte as it will never exceed 99; 99 is less than the byte range (128).
    private byte roundCount; // the amount of rounds that have passed; again, will never exceed the byte range as the max amount of players is capped
    // at 6; 6*3 = 18, 18 < 128.
    private boolean isReversed; // a boolean that holds true if the direction of play is reversed and false if not.
    private boolean playerLost; // if a player has played a card that exceeds the total score beyond 99, this boolean is set to true.
    
    // CONSTANTS
    private final static byte MAX_TOTAL = 99;
    private final static byte MAX_PLAYERS = 6;
    private final static byte MIN_PLAYERS = 2;
    private final static byte NO_CARDS = 3;
    
    public EddieBat()
    {
        this.deck = new EddieDeck(true); // Instantiate a new deck by default and populate it with all the cards in a standard 52-card deck.
        this.usedCards = new ArrayList<EddieCard>(); // Create an empty arraylist holding EddieCard objects as the usedCards pile.
        this.players = new EddieLinkedList(); // Instantiate an empty ll.
        this.currentPlayer = null; // set to default value of null.
        this.totalScore = 0; // set the total score to 0.
        this.isReversed = false; // the rotation is not yet reversed; thus, set it to false by default.
        this.playerLost = false; // no player has lost; thus, set it to false.
        this.roundCount = 1; // Setting the round to 0 would not be as intuitive for the output (i.e, what is round 0?); so, set it to round 1 as the
        // first round.
    }
    
    // Note: In order to run the BAT game, overloaded constructors are not needed; thus, it would be redundant to write them.
    
    /**
     * Method clearScreen
     * 
     * A helper method that merely clears the console (in BlueJ) for the improvement of user interface.
     *
     */
    private void clearScreen() // method is set private because it is only called within this class and should not be used in other classes.
    {
        System.out.print('\u000C'); // command clears the console.
    }
    
    // INITIALISATION METHODS
    // METHODS THAT SIMULATE THE GAME'S PROCESS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method menuSetup
     * 
     * A helper method that sets up the initial menu screen. The method outputs the header page, and prompts the user to select of 3 menu options:
     * to play the game, to read the instructions, or to quit.
     * a return of true means that the game will be played, false means the user desires to quit the game/program.
     *
     * @return boolean
     */
    public boolean menuSetup()
    {
        boolean startPlaying = false; // By default, the game has not started.
        byte userChoice; // the user's choice of menu choice.
        
        // Output the header.
        System.out.println("╔════════════════════════════════════╗\n"
                    + "║░░░░▒▒▒▒▓▓▓  THE BAT GAME  ▓▓▓▒▒▒▒░░░░║\n" + "║ A mix of strategy, luck and competition! ║\n"
                    + "║    Recommended Number of Players: 2-6    ║\n"
                    + "║░░░░░░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░░░░░║\n╚════════════════════════════════════╝\n");
        
        // Step 1. Indefinitely loop 
        do
        {
            try // Create a try catch system where entering a value that is not a byte will catch the error and prompt the user to try again.
            {
                // Output menu options.
                System.out.println("\nWelcome to the BAT game! Select an option to continue:\n"
                    +"\n╔════════════════╗\n║ (1) Begin Game    ║\n╚════════════════╝\n╔════════════════╗\n║ (2) Instructions  ║"
                    +"\n╚════════════════╝\n╔════════════════╗\n║ (3) Quit the Game ║\n╚════════════════╝\n>>> ");
                
                // Take input.
                userChoice = new Scanner(System.in).nextByte();
                
                // Create a switch statement that run through all three options; 1, 2, and 3. However, a default statement is set if the user
                // inputs a byte (so that the try catch will not view it as an error) which plays the role of a logic check.
                // Any value that is not 1, 2, or 3 will be viewed incorrect and prompted to try again.
                switch (userChoice)
                {
                    case 1:
                        System.out.println("Game beginning...\n");
                        startPlaying = true; // break out of the loop by setting the startPlaying boolean to true.
                        break;
                    case 2:
                        instructions(); // call the instructions() method.
                        break;
                    case 3:
                        System.out.println("Quitting game...\n");
                        return startPlaying; // Efficiently state that the user desires to quit the game.
                    default:
                        System.out.println("Must enter a selection choice between 1-3 -- try again.\n");
                }
            }
            catch (Exception e) // Catch block.
            {
                System.out.println("Must enter a numeric whole value for menu selection -- try again.\n");
            }
        }
        while (!startPlaying);
        
        return startPlaying;
    }
    
    /**
     * Method instructions
     * 
     * A simple helper method that displays the instructions of the game.
     *
     */
    public void instructions()
    {
        clearScreen();
        // These are the instructions
        System.out.println("╔════════════════════════════════════╗\n"
            + "║░░░░▒▒▒▒▓▓▓  INSTRUCTIONS  ▓▓▓▒▒▒▒░░░░║\n╚════════════════════════════════════╝\n"
            + "\n\nEvery round, a standard deck of 52 cards is shuffled; then, 3 cards are given to each player."
            + "\nThe remaining cards are stacked down on the table -- this is called the stockpile."
            + "\nEach round starts at zero points, the first player starts and the round rotates clockwise."
            + "\nWhen a player plays a card, the number value associated with the card is added to the score."
            + "\n\nHowever, there are special cards:\n• Ace is either 1 or 11 -- the player decides\n• 9 reverses the order of play w/out changing score"
            + "\n• A 4 skips the player's turn w/out changing score\n• A Jack subtracts 10 from score (Note: score cannot be negative)"
            + "\n• A Queen will set the score to 99 immediately!\n• A King adds 20 points to the score\n\n");
    }
    
    /**
     * Method startGame
     * 
     * A void method that starts the game completely. It calls the menuSetup firstly and will only proceed to start the game once that method
     * returns true; then, the addPlayers() method is called which creates all players to the user's desire and sets the currentPlayer to be the head
     * of the linkedlist.
     * After, the isWinner() method is called continually until that method returns true (meaning there is a winner); if not, a new round will start.
     * At the end, the winner is displayed and the game is finished.
     *
     */
    public void startGame()
    {
        if (menuSetup()) // Run the menuSetup() method to setup the user's choice.
        {
            addPlayers(); // Add players to the game
            // We want to set the currentPlayer/player who goes first to the first player that was added; to do that, we set the currently null
            // EddiePlayer object (currentPlayer) to the cargo (an EddiePlayer object) of the head of the linkedlist.
            this.currentPlayer = this.players.head().getCargo();
            
            while (!isWinner()) // Every time we loop through -- the program will check if there is a winner by calling the method.
            // If there is a winner, no more rounds will start. Otherwise, a round will start. This loop not only allows the amount of rounds to,
            // in theory never end (until a winner is established) -- but it checks to see if there is a winner before every round: another crucial check.
            {
                startRound();
            }
            
            // Display the winner
            System.out.println("╔════════════════════════════════════╗\n░░▒▒▒▒▓▓▓ The winner is " 
                + this.players.head().getCargo().getName() + "!  ▓▓▓▒▒▒▒░░\n╚════════════════════════════════════╝");
        }
        else
        {
            // Successfully quit
            System.out.println("See you next time.");
        }
    }
    
    // METHODS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    /**
     * Method printTotalScore
     * 
     * Helper method that outputs the total score in a organised and aesthetic manner.
     *
     */
    public void printTotalScore()
    {
        // Output total score.
        System.out.println("\n╔══════════════════════════╗\n║     Stockpile Score: " + this.totalScore + 
            "       ║\n╚══════════════════════════╝\n");
    }
    
    /**
     * Method addPlayers
     * 
     * Void method takes input from the user as to how many players the user specifies will play the BAT game along with the names of each player.
     * The method ensures that each player has a name and that the amount of players is between 2-6; the appropriate amount of players for the BAT game.
     *
     */
    public void addPlayers()
    {
        // Step 1. Declare and initialise variables.
        String name;
        byte noPlayers = 0; // Byte as data type because the specified range of players is between 2-6; within the byte range.
        
        // Step 2. Ask the user how many players are playing.
        while (noPlayers < MIN_PLAYERS || noPlayers > MAX_PLAYERS) // Essentially, as long as the user has not entered an appropriate num. players, keep
        // looping.
        {
            try // A try catch is required for the byte value.
            {
                // Output to the user how many players they want to play.
                System.out.println("How many players are playing?\nSelect a number of players between 2-6\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n"
                    + ">>> ");
                
                noPlayers = new Scanner(System.in).nextByte(); // Take input
                
                // If the user has entered an appropriate amount of players, move on to the next step...
                if (noPlayers >= MIN_PLAYERS && noPlayers <= MAX_PLAYERS) 
                {
                    // Step 3. Ask for the names of each player
                    for (byte i = 1; i <= noPlayers; i++) // Again, utilise a byte for i as it is the most efficient data type given the capped range 
                    // of players.
                    {
                        name = ""; // It is desired that the user is stuck in a loop until they enter a name that is not empty; to do this, set the name
                        // currently to an empty string with a length of 0 at the start of every looped iteration.
                        
                        // As mentioned above, keep asking the user for the name of the player until they enter a name that has at least one character.
                        while (name.length() == 0)
                        {
                            // Ask and take input.
                            System.out.println("Enter the name of player " + i + "\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n>>> ");
                            
                            name = new Scanner(System.in).nextLine(); // Take input.
                        }
                        
                        players.add(new EddiePlayer(name)); // Add to the the linked list the new player and their name.
                    }
                }
                else // If they did not enter an appropriate amount of players.
                {
                    // Logic error catch
                    System.out.println("Must enter a valid amount of players between " + MIN_PLAYERS + " and " + MAX_PLAYERS + " -- try again.\n");
                }
            }
            catch (Exception e) // if they did not enter a byte.
            {
                System.out.println("Must enter a numeric whole value for the number of players playing -- try again.\n");
            }
        }
    }
    
    /**
     * Method startRound
     * 
     * Void method starts a round of the BAT game by outputting the round number, resetting all the players hands, the total score, and the deck.
     * The method prompts the player to play a card and go onto the next player until a player has lost; then, it will display who has lost the round
     * when they have indeed lost and increment the roundCount by 1.
     *
     */
    public void startRound()
    {
        // Output the round count.
        System.out.println("\n░░░░▒▒▒▒▓▓▓ ROUND " + roundCount + " ▓▓▓▒▒▒▒░░░░\n");
        
        // Every round, it is desired to reset the player's cards, the deck, and the totalScore; this is accomplished through the reset() method.
        reset();
        
        // It is desired to continue rotating and playing until the endRound() method returns true; which is continually called every iteration 
        // of the while loop.
        while (!endRound())
        {
            // The current player is prompted to play a card.
            playCard(this.currentPlayer.selectCard(promptCardSelection()));
            
            // If no player has lost yet, go to the next player.
            if (!playerLost)
            {
                nextTurn();
            }
            else // If a player has lost, that means that the currentPlayer is the player that has lost; so, output that.
            {
                System.out.println("░░░░▒▒▒▒▓▓▓ ROUND " + roundCount + " :: " + this.currentPlayer.getName() + 
                    " has lost this round. ▓▓▓▒▒▒▒░░░░\n");
            }
        }
        
        roundCount++; // Increment the round count by 1 after a round has finished.
    }
    
    
    /**
     * Method replaceDeck
     * 
     * Helper method replaces the deck with a new deck of usedCards (arraylist); then, the usedCards are cleared.
     * This method is used when the players have run out of cards to pick up from the deck and must reshuffle the deck using the usedCards/cards
     * they have already played.
     *
     */
    public void replaceDeck()
    {
        // Create a new EddieDeck object with a parameter being the usedCards.
        // Alternatively; an equally (or more efficient way of performing this action) is to append the usedCards arraylist to the deck's
        // arraylist of EddieCard objects; however, this way is far more readable from a development perspective.
        this.deck = new EddieDeck(this.usedCards);
        this.usedCards.clear(); // Now that the usedCards are placed back into the main deck; there are no more used cards; so, clear it.
    }
    
 
    /**
     * Method playCard
     * 
     * Helper method takes in an EddieCard object, aCard, to play. The method firstly checks if any player has lost before allowing the current player
     * to play a card; then, it calculates and determines if the card is a special card (e.g 9, Queen, King, Jack, 4, Ace). Before picking up a new card
     * to be added to the player's hand, the method ensures that the deck has cards in it by replacing the deck with the used cards if there are no cards
     * remaining. Ultimately, the currentPlayer's card that they played is replaced with a new card that is picked up and the played card is added
     * to the usedCards arraylist.
     *
     * @param aCard - EddieCard - the card that the current player desires to play.
     */
    public void playCard(EddieCard aCard)
    {
        // Step 1. Ensure that a player has not lost; if a player has lost, the round must end and no player should play anymore.
        if (!playerLost) 
        {
            // Step 2.
            calculateMove(aCard); // Calculate if the card played was a special card or not.
            
            // Logic check: every time a card is played, the player must pick up a new card; however, what if there are no more cards to pick up in the deck?
            // To resolve this and avoid the issue entirely, check if the deck's length is 0 -- meaning there are no cards left; if so, replace the deck.
            if (this.deck.length() == 0)
            {
                replaceDeck();
            }
            
            // Step 3. replace the played card with a new card for the player's hand and then add the played card to the usedCards pile.
            this.currentPlayer.replace(aCard, pickUp());
            this.usedCards.add(aCard);
        }         
    }
    
    /**
     * Method pickUp
     * 
     * Helper method returns the first card out of the current deck so that the player can receive a card after playing one.
     *
     * @return EddieCard
     */
    public EddieCard pickUp()
    {
        // returns the popped card.
        return this.deck.popCard();
    }
    
   
    public void calculateMove(EddieCard aCard)
    {
        // Step 1. Declare and initialise variables.
        boolean bolTrueFalse = true;
        byte oneOrEleven; // For efficiency, the oneOrEleven choice variable is set to a byte as it will only ever hold two values: 1 or 11 -- both 
        // of which are in the range of a byte.
        
        // Given the card, go through each of the possible special card options.
        switch (aCard.getRank())
        {
            case 1: // Ace
                // Prompt the user for 1 or 11
                do
                {
                    try
                    {
                        // Step 1. Prompt the user to see if they want to play a 1 or 11.
                        System.out.printf("%s played an Ace. Enter the desired value:\n• 1\n• 11\n\n>>> ", this.currentPlayer.getName());
                        oneOrEleven = new Scanner(System.in).nextByte();
                        
                        // If they did not enter an 11 nor a 1; let them try again.
                        if (oneOrEleven != 11 && oneOrEleven != 1)
                        {
                            // Output error
                            System.out.println("Must enter 1 or 11 as a value - try again.\n");
                        }
                        else // Otherwise, they have entered a valid option and must proceed.
                        {
                            bolTrueFalse = false; // Set the bolTrueFalse variable to false in order to break out of the do while loop
                            // I do this because the loop serves no more purpose after the user has inputted a valid input of 1 or 11.
                            determineScoring(oneOrEleven); // Finally, add the value to the totalScore.
                        }
                    }
                    catch (Exception e) // The user did not enter a byte; catch this error.
                    {
                        // Output error.
                        System.out.println("Must enter a valid numeric value --  try again.\n");
                    }                   
                }
                while (bolTrueFalse);
                break;
            case 4: // Pass
            // Output the special card scenario.
                System.out.printf("\n%s played a 4 -- turn skipped.\n", this.currentPlayer.getName());
                determineScoring((byte) 0); // Because the player passed; they should not add any score to the totalScore.
                break;
            case 9: 
                // Reverse
                // A crucial step: In the case that two players play a 9 consecutively; i.e they reverse and then reverse again; the second reverse
                // would in actuality accomplish nothing as the isReversed would be set to true and then true again. Instead, if a player reverses
                // and a second reverse is played; the rotation should actually resume in the default direction. This statement accomplishes such 
                // a nuance by reversing the boolean to whatever the opposite of the current value is.
                isReversed = !isReversed;
                // Output the special card scenario.
                System.out.printf("\n%s played a 9 -- turn skipped and direction of play reversed.\n", this.currentPlayer.getName());
                determineScoring((byte) 0); // Because the player reversed; they should not add any score to the totalScore.
                break;
            case 11:
                // Jack
                determineScoring((byte) -10); // Subtract 10 from the total score.
                break;
            case 12: // Queen
                determineScoring((byte) MAX_TOTAL); // Set the total score to the max -- 99.
                break;
            case 13: // King
                determineScoring((byte) 20); // Add 20 to the total score.
                break;
            default: // If the aCard.getRank() did not yield any of the aforementioned and checked special cards, simply add the rank to the total score.
                determineScoring(aCard.getRank());
        }
    }
    
    /**
     * Method determineScoring
     * 
     * A helper method that will take in a byte value -- representing the rank of a played card -- and determine what to do with said score.
     * To explain, the method will determine if a player should lose after playing a card with a given value, if the score should be capped at 
     * the max (in the case that the player plays a Queen), or if the score must be reset back to zero if it is ever negative. Ultimately, if no
     * determination for special circumstances is met, the method will add the value to the total score.
     * 
     *
     * @param value - byte - a number to be added to the total score from the played card's rank.
     */
    private void determineScoring(byte value)
    {
        // KEY NOTE: By separating the function of determining a score in a different method; development has improved readability as special cases
        // are condensed into one method to be evaluated. This method is private as it is only used within the class; determining a score
        // from special circumstances should never be utilised outside of the class as it is an internal operation that occurs after a player 
        // has played a card.
        
        // Queen's set the total score to 99; so, if a value of 99 is passed, simply set the total score to the max value (99).
        if (value == MAX_TOTAL) 
        {
            this.totalScore = MAX_TOTAL;
        }
        else if ((this.totalScore + value) > MAX_TOTAL)
        {
            // If the sum of the value and the totalScore is greater than 99, then that means the current player -- i.e the one that played the card
            // that held the value -- has lost.
            // Set the playerLost boolean to true so that no more turns will continue.
            playerLost = true;
        }
        // It is undesired for the score to ever dip below 0 into a negative digit (which can be accomplished by the jack card)
        // So; simply set the score back to 0 if the sum of the total score and value is below 0.
        else if ((this.totalScore + value) < 0)
        {
            this.totalScore = 0;
        }
        else
        {
            // If the player has not played a queen, nor a jack that turns the score to a negative whilst playing a card that would not make them lose
            // simply add the value of the card to the total score.
            this.totalScore += value;
        }
    }
    
    /**
     * Method drawHand
     * 
     * Helper method simply loops through the amount of cards each player deserves -- and sets one of those slots in their hand to the top card from
     * the deck.
     *
     */
    public void drawHand()
    {   
        // NO_CARDS < Byte max value, so, make i a byte too for efficiency.
        for (byte i = 0; i < NO_CARDS; i++)
        {
            this.currentPlayer.addCard(this.deck.popCard());
        }
    }
    
    /**
     * Method isWinner
     * 
     * Returns true if there is a winner, false if not.
     *
     * @return boolean
     */
    public boolean isWinner()
    {
        // The most efficient way of determining if there is a winner is to check how many active players there still are.
        // If there is only one active player in the ll remaining that means they have won (every other player has reached a status of BAT and has been
        // eliminated); thus, this method returns a true statement if the players (linkedList) length is exactly 1.
        return this.players.length() == 1;
    }
    
    /**
     * Method endRound
     * 
     * Method returns whether the round must be ended or not based off the whether a player has lost. A boolean represents true for if the round must end
     * and false if not.
     *
     * @return boolean
     */
    public boolean endRound()
    {
        // check Ff round is over
        if (playerLost) // Since a player losing a round means the round must end, simply check if a player has lost.
        {
            // Next, we must add a letter to the currentPlayer because they lost; however, if we are unable to add a letter; meaning they have reached
            // the final phrase of BAT, that means the player must be eliminated alongside ending the round.
            if (!this.currentPlayer.addLetter())
            {
                eliminatePlayer();
            }
            
            return true; // A player has lost - the sole condition that the round should end; so, return true.
        }
        
        return false; // the round has not ended if a player has not lost yet.
    }
    
    /**
     * Method nextTurn
     * 
     * Helper methods rotates the currentPlayer to the next player in the players linkedlist.
     *
     */
    public void nextTurn()
    {
        // it is desired to set the currentPlayer (which is always the head of the cyclic linkedlist in this game structure) to the next player;
        // however, we must account for what the next player truly is in the case that someone has played a 9 and the game's order is reversed.
        
        this.currentPlayer = this.players.head().getNextPlayer(isReversed); // Set the currentPlayer to either the next or previous EddieNode's cargo
        // depending on the boolean value of the isReversed var.
        this.players.setHead(this.players.head().getNext(isReversed)); // Then, set the head to the next/prev EddieNode.
    }
    
    /**
     * Method reset
     * 
     * Helper method resets the entire playing field by repopulating and reshuffling a 52 card deck, resetting the total score, ensuring that a player
     * has not lost (since the round has been reset), and iterates through each active player and gives them a new hand after clearing their previous hand.
     *
     */
    public void reset()
    { 
        // Firstly, instantiate and populate a new deck of 52 cards. A question that arises is whether there is an alternative way to repopulate the deck
        // with 52 cards. Another approach is appending the deck with all the players hands and the usedCards arrayList; however, not only is that far
        // harder to read than simply instantiating a new EddieDeck object; but it requires additional helper methods for the sole purpose of re-populating
        // a deck when one line of code can simply be re-used; thus, I believe this is the most intuitive way to repopulate the deck.
        this.deck = new EddieDeck(true);
        this.deck.shuffle(); // Shuffle the deck.
        this.totalScore = 0; // Set the score to 0.
        this.playerLost = false; // Set the playerLost to false.
        
        // Player length will always be less than or equal to 6; so, a byte for i will be the most efficient.
        for (byte i = 0; i < this.players.length(); i++)
        {
            this.currentPlayer.clearHand(); // clear hand, otherwise round 2 players will have 6 cards; round 3: 9 cards...etc....
            drawHand(); // draw a new hand
            nextTurn(); // move on to the next player.
        }
    }
    
    /**
     * Method promptCardSelection
     * 
     * Helper method prompts the user to input a card selection between 1, 2, and 3 (the 3 cards in a player's hand) and then outputs that selection in the
     * form of a byte.
     *
     * @return byte
     */
    public byte promptCardSelection()
    {
        // Step 1. Declare and initialise variables.
        byte userCardChoice = 0;
        
        // While the user has not made a correct card choice..
        while (userCardChoice < 1 || userCardChoice > 3)
        {
            try
            {
                // Print the total score so that the user can strategise which card to play
                printTotalScore();
                // Prompt a card selection menu
                System.out.println(currentPlayer + "\nSelect a card to play (1, 2, 3):\n>>> ");
                userCardChoice = new Scanner(System.in).nextByte();
                
                // If the user has made a valid choice -- display that choice -- then return the users card choice.
                if (userCardChoice >= 1 && userCardChoice <= 3)
                {
                    System.out.println("\nYou have played: " + this.currentPlayer.selectCard((byte) (userCardChoice - 1)).toString() + "\n"); // STAR
                    return (byte) (userCardChoice - 1); // Because humans and arrays "count" differently, the first element of an array/arraylist
                    // will be zero whereas a human's will be 1; so, accomadate for a human's perspective but then return a value that is more 
                    // manageable for the program to read by subtracting 1.
                }
                else // if the player has not entered a valid number selection :: logic check.
                {
                    System.out.println("Must enter a valid number selection between 1 and 3 -- try again.\n");
                }
            }
            catch (Exception e) // Error check.
            {
                System.out.println("Must enter a valid numerical digit -- try again.\n");
            } 
        }
        
        return -1; // footer return statement -- will never reach.
    }
    
    /**
     * Method eliminatePlayer
     * 
     * Helper method will eliminate a player; firstly by displaying that they have been eliminated and then deleting them from the linkedlist, players.
     *
     */
    public void eliminatePlayer()
    {
        System.out.println("\n╔════════════════════════════════════╗\n║       " + this.currentPlayer.getName() + " has been eliminated!         ║"
            + "\n╚════════════════════════════════════╝\n");
        // In my structure of program, the head is always the currentPlayer; and since the currentPlayer must be eliminated; simply delete the head.
        players.deleteHead();
       
    }
    
    
    // END OF PROGRAM
}