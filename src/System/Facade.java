package System; /**
 * System.Facade.java
 *
 * Version	
 *   $Id: System.Facade.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: System.Facade.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */

import Model.Board;
import Model.Player;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 * An interface between the GUI and the kernel classes in a checkers game.
 * 
 * @author
 */

public class Facade extends Component {

    public static int LOCALGAME  = 10000;
    public static int HOSTGAME   = 20000;
    public static int CLIENTGAME = 30000;

    public static String update       = "update";
    public static String playerSwitch = "switch";
    public static String ID           = "facade";
    
    public Driver      theDriver;
    public LocalPlayer theLocalPlayer;
    public Board       theBoard;
    public Player      passivePlayer;
    public Player      activePlayer;		
    
    private int startSpace = 99; // Starting space for current move
    private int endSpace   = 99; // Ending space for current move
    
    // The numbers associated with the timer
    private int timer       = 999;
    private int warningTime = 999;
    
    private ActionListener actionListener;
      
    /**
     * Constructor for the facade.  Initializes the data members.
     * 
     * @param newBoard  Model.Board  object System.Facade will manipulate.
     * @param newDriver System.Driver object that will communicate
     *                  with the System.Facade.
     */
    public Facade( Board newBoard, Driver newDriver ){
	
	theBoard = newBoard;
	theDriver = newDriver;
	
    }
    
    /**
     * Return an int indicating which player's turn it is.
     * ( e.g. 1 for player 1 )
     *
     * @return int   The number of the player whose turn it is.
     * 
     * @pre game is in progress
     */
    public int whosTurn(){
	
	// Return the integer value of the activePlayer object
	int turn;
	turn = activePlayer.getNumber();
	
	return turn;
    }
    
    /**
     * Set which players turn it is.
     * 
     * @param active  The active player
     * @param passive The passive player
     */
    public void setPlayerModes( Player active, Player passive ){
	
	activePlayer = active;
	passivePlayer = passive;
	
	// Tell GUI to update
	generateActionPerformed( update );
    }
    
    /**
     *
     * This method should be called to select a space on the board, 
     * either as the starting point or the ending point for a move.  
     * The System.Facade will interpret this selection and send a move on to
     * the kernel when two spaces have been selected.
     *
     * @param space an int indicating which space to move to, 
     *              according to the standard checkers numbering 
     *              scheme, left to right and top to bottom.
     */
    public void selectSpace( int space ){  
	
	// When button is click, take info from the GUI
	if( startSpace == 99 ){
	    
	    // Set startSpace to space
	    startSpace = space;
	    
	}else if( startSpace != 99 && endSpace == 99 ){
	    if( space == startSpace ){
		
		// Viewed as un-selecting the space selected
		// Set startSpace to predetermined unselected value
		startSpace = 99;
		
	    }else{
		// The endSpace will be set to space
		endSpace = space;
		makeLocalMove();
	    }
	}
	
	generateActionPerformed( "update" );   
	
    }
    
    /**
     * Send a move on to the kernel, i.e. call makeMove() in 
     * the System.LocalPlayer and inform it whose turn it is.
     *
     * @pre startSpace is defined
     * @pre endSpace is defined
     */
    private void makeLocalMove(){
	
	//make sure startSpace and endSpace are defined
	if( startSpace != 99 && endSpace!= 99 ){
	    // Takes the information of a move and calls makeMove() 
	    // in a localPlayer
	    boolean result = activePlayer.makeMove( startSpace, endSpace );
	}
	
	// Reset startSpace and endSpace to 99
	startSpace = 99;
	endSpace   = 99;
	
    }
    
    /**
     * Tell the kernel that the user has quit/resigned the game 
     * or quit the program
     */
    public void pressQuit(){

	// Alert players and the kernel that one person 
	// has quit calls quitGame() for both players
	theDriver.endInQuit( activePlayer );
	
    }
    
    /**
     * Tell the kernel that the user has requested a draw.
     */
    public void pressDraw(){

	// Alerts both players and the kernel that one person 
	// has offered a draw calls offerDraw() on both players
	activePlayer.offerDraw( activePlayer );

    }
    
    /**
     * Tell the kernel that the user has accepted a draw.
     *
     */
    public void pressAcceptDraw(){
	
	//calls acceptDraw() in teh driver
	theDriver.endInDraw( activePlayer );
    }
    
    /**
     * Given a player number, returns the name associated 
     * with that number.
     * 
     * @param  playerNum the number of a player
     * @return string    the name associated with playerNum
     *
     * @pre playerNum is a valid player number
     */
    public String getPlayerName( int playerNum ){
	String retString = null;
	
	try{
	    // Checks to see that playerNum is valid
	    if( playerNum == 1 || playerNum == 2 ){
		// checks both Model.Player objects to see which one is
		// associated with the legal number returns the name of 
                // the player asscociated with the number
		if( activePlayer.getNumber() == playerNum ){
		    retString = activePlayer.getName();
		}else{
		    retString = passivePlayer.getName();
		}
	    }		   
	}catch( Exception e ){
	    
	    System.out.println( e.getMessage() );
	  
	    // If playerNum is illegal an exception will be thrown
	}

	return retString;
    }
    
    /**
     * Tell the kernel to associate the given name with the 
     * given player number.
     *
     * @param playerNum the number of a player
     * @param name      the name that player should be given
     *
     * @pre playerNum is a valid player number
     */
    public void setPlayerName( int playerNum, String name ){
	theDriver.setPlayerName( playerNum, name );
    }
    

    
    /**
     * Display to local players that the game has ended with 
     * the message provided.
     * 
     * @param message
     * 
     * @post the game ends
     */
    public void showEndGame( String message ){
	//make sure game is over
	//calls endGame in driver object
	theDriver.endGame( message );
    }
    
    /**
     * Set the game mode: a local game or a network game
     * 
     * @param the mode of the game
     * 
     * @pre we are in the setup for a game
     * 
     */
    public void setGameMode( int mode ) throws Exception{
	// Check to make sure that mode is a legal value
	// Call setGameMode() in driver class passing it 
	// the legal mode.  If mode is not a legal value 
	// an exception will be thrown
       	if( mode == LOCALGAME || mode == HOSTGAME || mode == CLIENTGAME ){
	    theDriver.setGameMode( mode );
	}else {
	    throw new Exception( "Invalid Game Mode" );
	}
    }


   
    /**
     * Adds an action listener to the facade
     */
    public void addActionListener( ActionListener a ){
	actionListener = AWTEventMulticaster.add( actionListener, a );
	//Adds an action listener to the facade
    }
    
    /**
     * Called when both players have clicked OK on the end game dialog box
     * 
     * @post the game has ended 
     */
    public void endGameAccepted(){
	
	//waits until both players have accepted the end of the game 
	//end the game
    }
    
    /**
     * Notifies everything of the sta eof the board
     * 
     * @return a Model.Board object which is the state of the board
     * 
     */
    public Board stateOfBoard(){
	// Return the board so GUI can go through and update itself
	return theBoard;
    }
    
    /**
     * Call the driver and begin the game.
     */
    public void startGame(){
	theDriver.startGame();
    }
    
    /**
     * Generates an action. This is inhereted from Component
     * 
     */    
    public void generateActionPerformed(){

	if ( actionListener != null ) {
	    actionListener.actionPerformed( 
               new ActionEvent( this, ActionEvent.ACTION_PERFORMED, ID ) );
	    // Fires event associated with timer, or a move made on GUI
	}

    }
    
    /**
     * Generates an action. This is inhereted from Componen
     */    
    private void generateActionPerformed( String command ){
	
	if ( actionListener != null ) {
	    actionListener.actionPerformed( 
              new ActionEvent( this, ActionEvent.ACTION_PERFORMED, command ) );
	    // Fires an event associated with timer, or move made on GUI
	}
    }
    
    /**
     * Create a player with the given type and player number.
     *
     * @param num  Int for player number (either 1 or 2)
     * @param type Int for type of player (Local, network, etc.)
     */
    public void createPlayer( int num, int type ) {

	if ( type == HOSTGAME || type == CLIENTGAME ) {
	    theDriver.createPlayer( num, Player.NETWORKPLAYER, "UnNamedPlayer" );
	} else {
	    theDriver.createPlayer( num, Player.LOCALPLAYER, "UnNamedPlayer" );
	}
    }

}// System.Facade.java