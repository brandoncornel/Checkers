package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
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
    
    private Facade theFacade;
    private Firstscreen theFirst;
    private int gameType;
    
    // Variables declaration
    private JCheckBox timedGameBox;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JTextField playerOneField;
    private JTextField playerTwoField;
    private JLabel turnLengthLabel;
    private JLabel WarningLengthLabel;
    private JButton okButton;
    private JButton cancelButton;
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
        gameType = type;
        
        initComponents ();
        pack ();
    }


    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
	
    private void initComponents() {

        timedGameBox = new JCheckBox();
        playerOneLabel = new JLabel();
        playerTwoLabel = new JLabel();
        playerOneField = new JTextField();
        playerTwoField = new JTextField();
        turnLengthLabel = new JLabel();
        WarningLengthLabel = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();
        turnLengthField = new JSlider( 10, 300, 120 );
        warningLengthField = new JSlider( 10, 300, 120 );
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;
        addWindowListener(new ExitProgramListener());

        
        timedGameBox.setBackground(new Color (204, 204, 204));
        timedGameBox.setName("timedGameBox");
        timedGameBox.setForeground(Color.black);
        timedGameBox.setText("Timed game");
        timedGameBox.setSelected( true );
        timedGameBox.addActionListener(new EnableTimerSettingsActionListener());

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.ipadx = 7;
        gridBagConstraints1.ipady = 7;
        gridBagConstraints1.insets = new Insets(31, 0, 1, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(timedGameBox, gridBagConstraints1);

	
        playerOneLabel.setName("playerOneLabel");
        playerOneLabel.setBackground(new Color (204, 204, 204));
        playerOneLabel.setForeground(Color.black);
        playerOneLabel.setText("Model.Player 1:");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(playerOneLabel, gridBagConstraints1);
     
        playerTwoLabel.setName("playerTwoLabel");
        playerTwoLabel.setBackground(new Color (204, 204, 204));
        playerTwoLabel.setForeground(Color.black);
        playerTwoLabel.setText("Model.Player 2:");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new Insets(4, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(playerTwoLabel, gridBagConstraints1);
        
        playerOneField.setBackground(Color.white);
        playerOneField.setName("textfield1");
        playerOneField.setForeground(Color.black);
        playerOneField.setText("Enter name");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(playerOneField, gridBagConstraints1);
        
        playerTwoField.setBackground(Color.white);
        playerTwoField.setName("textfield2");
        playerTwoField.setForeground(Color.black);
        playerTwoField.setText("Enter name");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new Insets(4, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(playerTwoField, gridBagConstraints1);
        
        turnLengthLabel.setName("label3");
        turnLengthLabel.setBackground(new Color (204, 204, 204));
        turnLengthLabel.setForeground(Color.black);
        turnLengthLabel.setText("Turn Length ( " + turnLengthField.getValue() + " seconds )");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(turnLengthLabel, gridBagConstraints1);
     
        WarningLengthLabel.setName("label4");
        WarningLengthLabel.setBackground(new Color (204, 204, 204));
        WarningLengthLabel.setForeground(Color.black);
        WarningLengthLabel.setText("Warning Length ( " + warningLengthField.getValue() + " seconds )");
                
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 8;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(WarningLengthLabel, gridBagConstraints1);
        
        okButton.setText("OK");
        okButton.setName("button1");
        okButton.setBackground(new Color (212, 208, 200));
        okButton.setForeground(Color.black);
        okButton.setActionCommand("ok");
        okButton.addActionListener( new ContinueToCheckerGuiActionListener() );
		
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 11;
        gridBagConstraints1.insets = new Insets(20, 0, 10, 12);
        gridBagConstraints1.anchor = GridBagConstraints.EAST;
        getContentPane().add(okButton, gridBagConstraints1);
       
        cancelButton.setText("Cancel");
        cancelButton.setName("button2");
        cancelButton.setBackground(new Color (212, 208, 200));
        cancelButton.setForeground(Color.black);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener( new ReturnToFirstScreenActionListener() );
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 11;
        gridBagConstraints1.insets = new Insets(20, 0, 10, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(cancelButton, gridBagConstraints1);
        
        turnLengthField.setName("textfield3");
        turnLengthField.addChangeListener( new UpdateLabelWithValueChangeListener(turnLengthLabel, "Turn Length") );
     
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 6;
        getContentPane().add(turnLengthField, gridBagConstraints1);
       
        warningLengthField.setName("textfield4");
        warningLengthField.addChangeListener( new UpdateLabelWithValueChangeListener(WarningLengthLabel, "Warning Length") );
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 8;
        getContentPane().add(warningLengthField, gridBagConstraints1);
       
	//determine what components should be disabled
	//depending on the game mode
	if ( gameType == theFacade.LOCALGAME ) {
	} else if ( gameType == theFacade.HOSTGAME ) {
	    playerTwoLabel.setEnabled( false );
	    playerTwoField.setEnabled( false );
	} else if ( gameType == theFacade.CLIENTGAME ) {
	    playerOneLabel.setEnabled( false );
	    playerOneField.setEnabled( false );
	    
	    timedGameBox.setEnabled( false );
	    turnLengthLabel.setEnabled( false );
	    WarningLengthLabel.setEnabled( false );
	    turnLengthField.setEnabled( false );
	    warningLengthField.setEnabled( false );
	}
    }
    
    
    /**
     * This takes care of when an action takes place. It will check the 
     * action command of all components and then deicde what needs to be done.
     * 
     * @param the event fired
     */
    
	private class ContinueToCheckerGuiActionListener implements ActionListener {
    public void actionPerformed( ActionEvent e ){
	try{
	    
	    if ( (e.getActionCommand()).equals( "ok" ) ) {
		
		//take note of all selections and go to game startup
		if ( playerOneField.isEnabled() ) {
		    if ( ( playerOneField.getText() ).equalsIgnoreCase( "" ) ) {
			playerOneField.setText( "player1" );
		    }
		}
		
		if ( playerTwoField.isEnabled() ) {
		    if ( ( playerTwoField.getText() ).equalsIgnoreCase( "" ) ) {
			playerTwoField.setText( "player2" );
		    }
		}
		
		theFacade.setPlayerName( 1, playerOneField.getText() );
		theFacade.setPlayerName( 2, playerTwoField.getText() );
		
		//if a timer is desired
		if ( timedGameBox.isEnabled() ) {
		    if( timedGameBox.isSelected() ){
			
			//set the 2 timer values
			try {
			   
			    theFacade.setTimer( turnLengthField.getValue(),
						warningLengthField.getValue() );
			    
			} catch ( Exception x ) {
			    
			    JOptionPane.showMessageDialog( null,
							   "Invalid System.Timer value(s)",
							   "Error",
							   JOptionPane.INFORMATION_MESSAGE );
			}
			//else set timer values to a no timer constant
		    } else {
			theFacade.setTimer( -1, -1 );
			
		    }
		} else {
		    theFacade.setTimer( -1, -1 );
		   
		}
		
		//start the game
		theFacade.startGame();
		//hide this screen, make and show the GUI
		Secondscreen.this.dispose();
		CheckerGUI GUI = new CheckerGUI( theFacade, theFacade.getPlayerName( 1 ),
						 theFacade.getPlayerName( 2 ) );
		GUI.setVisible(true);
		
		//if they hit cancel go to the previous screen
		
		//handle whether or not a timer is desired
	    }
	    
	} catch( Exception x ){
	    x.printStackTrace();
	}	
	
    }//end of actionPerformed
    }
    
    
	private class ReturnToFirstScreenActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			Secondscreen.this.dispose();
			theFirst.setVisible(true);
		}
	}
	
	private class EnableTimerSettingsActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			AbstractButton src = (AbstractButton) e.getSource();
			
			turnLengthField.setEnabled( src.isSelected() );
			warningLengthField.setEnabled( src.isSelected() );
		}
	}
	
	private class UpdateLabelWithValueChangeListener implements ChangeListener {
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
	
}//GUI.Secondscreen
