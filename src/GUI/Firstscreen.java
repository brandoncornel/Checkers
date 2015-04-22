package GUI;

import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import System.Facade;

/**
 *
 * @author
 * @version 
 */

public class Firstscreen extends JFrame {

    private final Facade theFacade;
  
    // Variables declaration - do not modify
    private JRadioButton LocalGameButton;
    private JRadioButton HostGameButton;
    private JRadioButton JoinGameButton;
    private JTextField IPField;
    // End of variables declaration


    /** 
     * Creates new form GUI.Firstscreen
     *
     * @param facade a facade object for the GUI to interact with
     *     
     */

    public Firstscreen( Facade facade ) {

	super( "First screen" );
        theFacade = facade;
        initComponents();
        pack();
    }
    

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * 
     */

    private void initComponents() {

        LocalGameButton = new JRadioButton();
        HostGameButton = new JRadioButton();
        JoinGameButton = new JRadioButton();
        final ButtonGroup gameModes = new ButtonGroup();
        IPField = new JTextField();
        final JButton OKButton = new JButton();
        final JButton CancelButton = new JButton();
        getContentPane().setLayout(new java.awt.GridBagLayout());
        addWindowListener(new ExitProgramListener());
        
	gameModes.add(LocalGameButton);
        gameModes.add(HostGameButton);
	gameModes.add(JoinGameButton);
		
        LocalGameButton.setText("Local game");
        LocalGameButton.addActionListener(new IPFieldSetEnabled(false));
        LocalGameButton.setSelected( true );
        
        HostGameButton.setText("Host game");
        HostGameButton.addActionListener(new IPFieldSetEnabled(false));
        
        JoinGameButton.setText("Join game");
        JoinGameButton.addActionListener(new IPFieldSetEnabled(true));
        
        IPField.setText("IP address goes here");
        IPField.setEnabled( false );
        
        OKButton.setText("OK");
        OKButton.setBackground(new Color (212, 208, 200));
        OKButton.addActionListener(new ContinueToSecondScreenActionListener());
        
        CancelButton.setText("Cancel");
        CancelButton.setBackground(new Color (212, 208, 200));
        CancelButton.addActionListener(new ExitProgramListener());
        
		{
			GridBagConstraints normal = new GridBagConstraints();
			normal.fill = GridBagConstraints.BOTH;
			
			GridBagConstraints confirmButtons = new GridBagConstraints();
			confirmButtons.insets = new Insets(30, 10, 0, 10);
			confirmButtons.fill = GridBagConstraints.BOTH;
			
			GridBagConstraints radioButtons = new GridBagConstraints();
			radioButtons.gridwidth = GridBagConstraints.REMAINDER;
			radioButtons.fill = GridBagConstraints.BOTH;
			
			GridBagConstraints IPFieldConstraints = new GridBagConstraints();
			IPFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
			IPFieldConstraints.fill = GridBagConstraints.BOTH;
			IPFieldConstraints.weightx = 2;
			
			GridBagConstraints IPExampleConstraints = new GridBagConstraints();
			IPExampleConstraints = new GridBagConstraints();
			IPExampleConstraints.gridx = 1;
			IPExampleConstraints.gridy = 4;
			IPExampleConstraints.gridwidth = GridBagConstraints.REMAINDER;
			
			GridBagConstraints weightOne = new GridBagConstraints();
			weightOne.weightx = 1;
			
			
			getContentPane().add(LocalGameButton, radioButtons);
			getContentPane().add(HostGameButton, radioButtons);
			getContentPane().add(JoinGameButton, radioButtons);
			getContentPane().add(new JLabel("IP address:"), normal);
			getContentPane().add(IPField, IPFieldConstraints);
			getContentPane().add(new JLabel("Ex: 123.456.789.123"), IPExampleConstraints);
			
			
			getContentPane().add(new JPanel(), normal);
			getContentPane().add(new JPanel(), weightOne);
			getContentPane().add(OKButton, confirmButtons);
			getContentPane().add(CancelButton, confirmButtons);
		}
		
	}
	
	/**
	 * This takes care of when an action takes place. It will check the 
	 * action command of all components and then deicde what needs to be done.
	 *
	 * @param e the event that has been fired
	 * 
	 */
	
	private class ContinueToSecondScreenActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			
			int gameType;
			if (JoinGameButton.isSelected()) {
				gameType = theFacade.CLIENTGAME;
			} else if (HostGameButton.isSelected()) {
				gameType = theFacade.HOSTGAME;
			} else { // assume the last button is selected
				gameType = theFacade.LOCALGAME;
			}
			
			
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
			
			
			if (JoinGameButton.isSelected()) {
				try {
					
					//create a URL from the IP address in the IPfield
					URL address = new URL( "http://" + IPField.getText() );
					//set the host
					theFacade.setHost( address );
											
					//catch any exceptions
				} catch ( MalformedURLException x ) {
					JOptionPane.showMessageDialog( Firstscreen.this,
								"Invalid host address",
								"Error",
								JOptionPane.INFORMATION_MESSAGE );
				}
			}
			
			//hide the GUI.Firstscreen, make a GUI.Secondscreen and show it
			Firstscreen.this.setVisible(false);
			new Secondscreen( theFacade, Firstscreen.this, gameType ).setVisible(true);
			
		}//end of actionPerformed
	}

	/**
	 * Upon a call to actionPerformed, sets the enabled property 
	 */
	
	private class IPFieldSetEnabled implements ActionListener {
		private final boolean enabled;
		
		public IPFieldSetEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		
		public void actionPerformed(ActionEvent e) {
			IPField.setEnabled(enabled);
		}
	}

}
