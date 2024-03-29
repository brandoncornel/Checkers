package System; /**
 * System.NetworkMove.java
 *
 * Version:
 *    $Id: System.NetworkMove.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *    $Log: System.NetworkMove.java,v $
 *    Revision 1.1  2002/10/22 21:12:53  se362
 *    Initial creation of case study
 *
 */

import Model.Move;

import java.io.*;

/**
 * An object representation of a move, without a reference to the Model.Player who
 * made the move.
 *
 * @author
 */
public class NetworkMove implements Serializable {

    private int startingLocation;	// the starting location
	private int endingLocation;  	// the ending location

	/**
	 * Create a move with the starting location and
	 * ending location passed in as paremeters.
	 *
	 * @param startLoc The starting point of the move
	 * @param endLoc   The ending point of the move
	 *
	 * @pre startLoc and endLoc are valid locations
	 */
   	public NetworkMove( int startLoc, int endLoc ) {

        startingLocation = startLoc;
        endingLocation = endLoc;

    }

	/**
	 * Gets the start and end locations from an incoming Model.Move.
	 * 
	 * @param aMove - the incoming Model.Move object.
	 */
    public NetworkMove( Move aMove ) {

    	startingLocation = aMove.startLocation();
 	    endingLocation = aMove.endLocation();

    }

	/**
	 * Makes a move from the System.NetworkPlayer.
	 *
	 * @param parent - the System.NetworkPlayer.
	 */
	public void execute( NetworkPlayer parent ) {

		parent.makeMove( startingLocation, endingLocation );

    }

	/**
	 * Return the starting location of this move.
	 *
	 * @return The starting point of the move.
 	 */
	public int startLocation() {

		return -1;

	}


	/**
	 * Return the ending location of this move.
	 *
	 * @return The ending point of this location.
	 */
	public int endLocation(){

		return -1;

	}

	/**
	 * toString method for this object that will give start and end
	 * locations.
	 *
	 * @return a String representation for this object.
	 */
    public String toString() {

        return ("Model.Move.  startLoc = " + startingLocation + ", endLoc = "
                   + endingLocation);

    }


} //System.NetworkMove.java
