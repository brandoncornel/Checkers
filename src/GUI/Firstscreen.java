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
        final JLabel IPLabel = new JLabel();
        final JButton OKButton = new JButton();
        final JButton CancelButton = new JButton();
        final JLabel IPExampleLabel = new JLabel();
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        addWindowListener(new ExitProgramListener());
        
	gameModes.add(LocalGameButton);
        gameModes.add(HostGameButton);
	gameModes.add(JoinGameButton);
		
	LocalGameButton.setActionCommand("local");
        LocalGameButton.setText("Local game");
        LocalGameButton.addActionListener(new IPFieldSetEnabled(false));
        LocalGameButton.setSelected( true );
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        getContentPane().add(LocalGameButton, gridBagConstraints1);
        
        
        HostGameButton.setActionCommand("host");
        HostGameButton.setText("Host game");
        HostGameButton.addActionListener(new IPFieldSetEnabled(false));
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        getContentPane().add(HostGameButton, gridBagConstraints1);
        
        
        JoinGameButton.setActionCommand("join");
        JoinGameButton.setText("Join game");
        JoinGameButton.addActionListener(new IPFieldSetEnabled(true));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        getContentPane().add(JoinGameButton, gridBagConstraints1);
        
        
        IPField.setBackground( Color.white );
        IPField.setName("textfield5");
        IPField.setForeground( Color.black);
        IPField.setText("IP address goes here");
        IPField.setEnabled( false );
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 3;
        getContentPane().add(IPField, gridBagConstraints1);
        
        IPLabel.setName("label10");
        IPLabel.setBackground(new Color (204, 204, 204));
        IPLabel.setForeground(Color.black);
        IPLabel.setText("IP address:");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        getContentPane().add(IPLabel, gridBagConstraints1);
        
        OKButton.setText("OK");
        OKButton.setActionCommand("ok");
        OKButton.setName("button6");
        OKButton.setBackground(new Color (212, 208, 200));
        OKButton.setForeground(Color.black);
        OKButton.addActionListener(new ContinueToSecondScreenActionListener());
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        getContentPane().add(OKButton, gridBagConstraints1);
        
        CancelButton.setText("Cancel");
        CancelButton.setActionCommand("cancel");
        CancelButton.setName("button7");
        CancelButton.setBackground(new Color (212, 208, 200));
        CancelButton.setForeground(Color.black);
        CancelButton.addActionListener(new ExitProgramListener());
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        getContentPane().add(CancelButton, gridBagConstraints1);
        
        IPExampleLabel.setName("label11");
        IPExampleLabel.setBackground(new Color (204, 204, 204));
        IPExampleLabel.setForeground(Color.black);
        IPExampleLabel.setText("Ex: 123.456.789.123");
        
        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 4;
        getContentPane().add(IPExampleLabel, gridBagConstraints1);
        
        
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
