package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import System.Driver;
import System.Facade;


/**
 *
 * This is the second screen of options. It handles the players names and
 * options for the timer.
 *
 * @author
 * 
 */
public class Secondscreen {
    
    private final Facade theFacade;
    
    // Variables declaration
    private final JFrame frame;
    // End of variables declaration
	
	
    /**
     * 
     * Creates new GUI.Secondscreen
     *
     * @param first the GUI.Firstscreen object that created this window
     * @param gameType the type of game 
     * @param ipAddr the address of the other player, if there is one.
     */
    
    public Secondscreen( ) {
        theFacade = new Driver().getFacade();
        int gameType = 10000;
        
        try {
			// this method doesn't throw a type of exception, it throws Exception itself
			theFacade.setGameMode( gameType );
		} catch (Exception e1) {
			AssertionError e2 = new AssertionError("gameType does not throw if parameter is one of CLIENTGAME, HOSTGAME or LOCALGAME. Was: " + gameType);
			e2.initCause(e1);
			throw e2;
		}
        
		
        theFacade.createPlayer( 1, gameType );
        theFacade.createPlayer( 2, gameType );
        theFacade.setPlayerName(1, "Enter name");
        theFacade.setPlayerName(2, "Enter name");

        
        this.frame = new JFrame("Second Screen");
        
        initComponents();
        frame.pack();
        
    }


    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
	
    private void initComponents() {

        final SetNamePanel playerOneField = new SetNamePanel(1);
        final SetNamePanel playerTwoField = new SetNamePanel(2);
        final JButton okButton = new JButton();
        final JButton cancelButton = new JButton();
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.addWindowListener(new ExitProgramListener());
        
        
		playerOneField.addDocumentListener(new SetPlayerNameDocumentListener(1));
		playerTwoField.addDocumentListener(new SetPlayerNameDocumentListener(2));
        
        okButton.setText("OK");
        okButton.setBackground(new Color (212, 208, 200));
        okButton.addActionListener( new ContinueToCheckerGuiActionListener() );
        
        cancelButton.setText("Cancel");
        cancelButton.setBackground(new Color (212, 208, 200));
        cancelButton.addActionListener( new ReturnToFirstScreenActionListener() );
        
		
		{
			final GridBagConstraints nameBoxConstraints = new GridBagConstraints();
			nameBoxConstraints.weightx = 1;
			nameBoxConstraints.fill = GridBagConstraints.BOTH;
			nameBoxConstraints.insets = new Insets(5, 0, 0, 0);
			nameBoxConstraints.anchor = GridBagConstraints.WEST;
			nameBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			final GridBagConstraints okButtonConstraints = new GridBagConstraints();
			okButtonConstraints.insets = new Insets(20, 0, 10, 12);
			okButtonConstraints.anchor = GridBagConstraints.EAST;
			
			final GridBagConstraints cancelButtonConstraints = new GridBagConstraints();
			cancelButtonConstraints.insets = new Insets(20, 0, 10, 0);
			cancelButtonConstraints.anchor = java.awt.GridBagConstraints.WEST;
			cancelButtonConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			final GridBagConstraints weightOne = new GridBagConstraints();
			weightOne.weightx = 1;
			
			frame.getContentPane().add(playerOneField, nameBoxConstraints);
			frame.getContentPane().add(playerTwoField, nameBoxConstraints);
			frame.getContentPane().add(new JLabel(), weightOne);
			frame.getContentPane().add(okButton, okButtonConstraints);
			frame.getContentPane().add(cancelButton, cancelButtonConstraints);
		}
	}
    
    
    public void setVisible(boolean newValue) {frame.setVisible(newValue);}
    public void dispose() {frame.dispose();}
	
	
	/**
	 * This takes care of when an action takes place. It will check the
	 * action command of all components and then deicde what needs to be done.
	 *
	 * @param the event fired
	 */
	
	private final class ContinueToCheckerGuiActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			
			//start the game
			theFacade.startGame();
			//hide this screen, make and show the GUI
			Secondscreen.this.dispose();
			CheckerGUI GUI = new CheckerGUI( theFacade, theFacade.getPlayerName(1), theFacade.getPlayerName(2) );
			GUI.setVisible(true);
			
		}//end of actionPerformed
	}
	
	/**
	 * Upon a call to actionPerformed, disposes SecondScreen and shows FirstScreen
	 */
	private final class ReturnToFirstScreenActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			Secondscreen.this.dispose();
		}
	}
	
	/**
	 * Upon a call to any DocumentListener method, updates facade such that it
	 * sets the name of the specified player to the contents of the parameter document
	 */
	private final class SetPlayerNameDocumentListener implements DocumentListener {
		private final int playerNumber;
		
		public SetPlayerNameDocumentListener(int playerNumber) {
			this.playerNumber = playerNumber;
		}
		
		public void insertUpdate(DocumentEvent e) { this.changedUpdate(e); }
		public void removeUpdate(DocumentEvent e) { this.changedUpdate(e); }
		
		public void changedUpdate(DocumentEvent e) {
			Document src = e.getDocument();
			try {
				String name = src.getText(0, src.getLength());
				theFacade.setPlayerName(playerNumber, name);
			} catch (javax.swing.text.BadLocationException ex) {
				throw new AssertionError("Document.getText is specified to accept both '0' and 'Document#getLength()' as valid positions.", ex);
			}
		}
	}
	
}//GUI.Secondscreen
