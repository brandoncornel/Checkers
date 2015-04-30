package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import System.Facade;


/**
 *
 * This is the second screen of options. It handles the players names and
 * options for the timer.
 *
 * @author
 * 
 */
public class Secondscreen extends JFrame {
    
    private final Facade theFacade;
    private final Firstscreen theFirst;
    
    // Variables declaration
    private JCheckBox timedGameBox;
    private JSlider turnLengthField;
    private JSlider warningLengthField;
    // End of variables declaration
	
	
    /**
     * 
     * Creates new GUI.Secondscreen
     *
     *@param f the facade getting passed to to set options
     *@param first the GUI.Firstscreen object that ceated this window
     *@param type the type of game 
     * 
     */
    
    public Secondscreen( Facade f, Firstscreen first, int type ) {

        super( "Second Screen" );
        theFacade = f;
        theFirst = first;
        
        initComponents(type);
        pack();
    }


    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
	
    private void initComponents(int gameType) {

        timedGameBox = new JCheckBox();
        final JLabel playerOneLabel = new JLabel();
        final JLabel playerTwoLabel = new JLabel();
        final JTextField playerOneField = new JTextField();
        final JTextField playerTwoField = new JTextField();
        final JLabel turnLengthLabel = new JLabel();
        final JLabel WarningLengthLabel = new JLabel();
        final JButton okButton = new JButton();
        final JButton cancelButton = new JButton();
        turnLengthField = new JSlider( 10, 300, 120 );
        warningLengthField = new JSlider( 10, 300, 120 );
        getContentPane().setLayout(new GridBagLayout());
        addWindowListener(new ExitProgramListener());
        
        
        timedGameBox.setBackground(new Color (204, 204, 204));
        timedGameBox.setText("Timed game");
        timedGameBox.setSelected( true );
        timedGameBox.addActionListener(new EnableTimerSettingsActionListener());
        
        playerOneLabel.setText("Model.Player 1:");
        playerTwoLabel.setText("Model.Player 2:");
		playerOneField.getDocument().addDocumentListener(new SetPlayerNameDocumentListener(1));
		playerTwoField.getDocument().addDocumentListener(new SetPlayerNameDocumentListener(2));
        playerOneField.setText("Enter name");
        playerTwoField.setText("Enter name");
		
        turnLengthLabel.setText("Turn Length ( " + turnLengthField.getValue() + " seconds )");
        WarningLengthLabel.setText("Warning Length ( " + warningLengthField.getValue() + " seconds )");
        
        okButton.setText("OK");
        okButton.setBackground(new Color (212, 208, 200));
        okButton.addActionListener( new ContinueToCheckerGuiActionListener() );
        
        cancelButton.setText("Cancel");
        cancelButton.setBackground(new Color (212, 208, 200));
        cancelButton.addActionListener( new ReturnToFirstScreenActionListener() );
        
        turnLengthField.addChangeListener( new UpdateLabelWithValueChangeListener(turnLengthLabel, "Turn Length") );
        warningLengthField.addChangeListener( new UpdateLabelWithValueChangeListener(WarningLengthLabel, "Warning Length") );
		
		
		//determine what components should be disabled
		//depending on the game mode
		{
			final boolean firstPlayerIsLocal = (gameType != Facade.HOSTGAME);
			final boolean secondPlayerIsLocal = (gameType != Facade.CLIENTGAME);
			
			playerTwoLabel.setEnabled( firstPlayerIsLocal );
			playerTwoField.setEnabled( firstPlayerIsLocal );
			if (!firstPlayerIsLocal) {
				theFacade.setPlayerName(2, "player2");
			}
			
			playerOneLabel.setEnabled( secondPlayerIsLocal );
			playerOneField.setEnabled( secondPlayerIsLocal );
			if (!secondPlayerIsLocal) {
				theFacade.setPlayerName(1, "player1");
			}
			
			timedGameBox.setEnabled( secondPlayerIsLocal);
			turnLengthLabel.setEnabled( secondPlayerIsLocal );
			WarningLengthLabel.setEnabled( secondPlayerIsLocal );
			turnLengthField.setEnabled( secondPlayerIsLocal );
			warningLengthField.setEnabled( secondPlayerIsLocal );
		}
		
		
		{
			final GridBagConstraints sliderLabelConstraints = new GridBagConstraints();
			sliderLabelConstraints.fill = GridBagConstraints.BOTH;
			
			final GridBagConstraints sliderValueConstraints = new GridBagConstraints();
			sliderValueConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			final GridBagConstraints nameLabelConstraints = new GridBagConstraints();
			nameLabelConstraints.insets = new Insets(5, 0, 0, 0);
			nameLabelConstraints.anchor = GridBagConstraints.WEST;
			
			final GridBagConstraints nameBoxConstraints = new GridBagConstraints();
			nameBoxConstraints.insets = new Insets(5, 0, 0, 0);
			nameBoxConstraints.anchor = GridBagConstraints.WEST;
			nameBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			final GridBagConstraints timedGameBoxConstraints = new GridBagConstraints();
			timedGameBoxConstraints.insets = new Insets(31, 0, 1, 0);
			timedGameBoxConstraints.anchor = GridBagConstraints.WEST;
			timedGameBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
			timedGameBoxConstraints.ipadx = 7;
			timedGameBoxConstraints.ipady = 7;
			
			final GridBagConstraints okButtonConstraints = new GridBagConstraints();
			okButtonConstraints.insets = new Insets(20, 0, 10, 12);
			okButtonConstraints.anchor = GridBagConstraints.EAST;
			
			final GridBagConstraints cancelButtonConstraints = new GridBagConstraints();
			cancelButtonConstraints.insets = new Insets(20, 0, 10, 0);
			cancelButtonConstraints.anchor = java.awt.GridBagConstraints.WEST;
			cancelButtonConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			getContentPane().add(playerOneLabel, nameLabelConstraints);
			getContentPane().add(playerOneField, nameBoxConstraints);
			getContentPane().add(playerTwoLabel, nameLabelConstraints);
			getContentPane().add(playerTwoField, nameBoxConstraints);
			getContentPane().add(timedGameBox, timedGameBoxConstraints);
			getContentPane().add(turnLengthLabel, sliderLabelConstraints);
			getContentPane().add(turnLengthField, sliderValueConstraints);
			getContentPane().add(WarningLengthLabel, sliderLabelConstraints);
			getContentPane().add(warningLengthField, sliderValueConstraints);
			getContentPane().add(okButton, okButtonConstraints);
			getContentPane().add(cancelButton, cancelButtonConstraints);
		}
	}
	
	
	/**
	 * This takes care of when an action takes place. It will check the
	 * action command of all components and then deicde what needs to be done.
	 *
	 * @param the event fired
	 */
	
	private final class ContinueToCheckerGuiActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			try {
				//if a timer is desired
				if ( timedGameBox.isEnabled() && timedGameBox.isSelected() ){
					//set the 2 timer values
					theFacade.setTimer( turnLengthField.getValue(),
								warningLengthField.getValue() );
				} else {
					//else set timer values to a no timer constant
					theFacade.setTimer( -1, -1 );
				}
				
			} catch ( Exception x ) {
					
					JOptionPane.showMessageDialog( null,
								"Invalid System.Timer value(s)",
								"Error",
								JOptionPane.INFORMATION_MESSAGE );
			}
			
			//start the game
			theFacade.startGame();
			//hide this screen, make and show the GUI
			theFirst.dispose();
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
			theFirst.setVisible(true);
		}
	}
	
	/**
	 * Upon a call to actionPerformed, enabled to disabled turnLengthField and warningLengthField,
	 * making it's enabledness match the source's selected value.
	 */
	private final class EnableTimerSettingsActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			AbstractButton src = (AbstractButton) e.getSource();
			
			turnLengthField.setEnabled( src.isSelected() );
			warningLengthField.setEnabled( src.isSelected() );
		}
	}
	
	/**
	 * Upon a stateChanged, changes toUpdate's text to indicate a JSlider's value
	 */
	private final static class UpdateLabelWithValueChangeListener implements ChangeListener {
		private final JLabel toUpdate;
		private final String caption;
		
		public UpdateLabelWithValueChangeListener(JLabel toUpdate, String caption) {
			this.toUpdate = toUpdate;
			this.caption = caption;
		}
		
		/*
		 * This changes the text on the labels
		 *
		 * @param e the change event
		 *
		 */
		public void stateChanged( ChangeEvent e ) {
			JSlider src = (JSlider) e.getSource();
			
			this.toUpdate.setText(this.caption + " ( "
					+ src.getValue() 
					+ " seconds )");
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
