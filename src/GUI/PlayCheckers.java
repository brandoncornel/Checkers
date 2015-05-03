package GUI;

import System.Driver;

/**
 *
 * The main class to run the game. It creates the driver and first screen
 *
 * @author
 *
 */
class PlayCheckers{
	
	/**
	 * The main method to play checkers
	 *
	 * @param args[] the command line arguments
	 * 
	 */
	public static void main(String args[] ){
		
		Firstscreen first = new Firstscreen();
		first.setVisible(true);
	}
}//GUI.PlayCheckers
