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

public class Firstscreen {
	
    // Variables declaration - do not modify
    private final GameTypeSelectionPanel gameTypeSelection;
    private final JFormattedTextField IPField;
    private final JFrame frame;
    // End of variables declaration


    /** 
     * Creates new form GUI.Firstscreen
     *
     */

    public Firstscreen() {
        gameTypeSelection = new GameTypeSelectionPanel();
        IPField = new JFormattedTextField(new UrlFormat());
        frame = new JFrame("First screen");
        
        initComponents();
    }
    

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * 
     */

    private void initComponents() {

        final JButton OKButton = new JButton();
        final JButton CancelButton = new JButton();
        frame.getContentPane().setLayout(new java.awt.GridBagLayout());
        frame.addWindowListener(new ExitProgramListener());
        
		
        gameTypeSelection.addPropertyChangeListener(GameTypeSelectionPanel.GAME_MODE_PROERTY_NAME, new IPFieldSetEnabled());
        
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
			
			
			frame.getContentPane().add(gameTypeSelection, radioButtons);
			frame.getContentPane().add(new JLabel("IP address:"), normal);
			frame.getContentPane().add(IPField, IPFieldConstraints);
			frame.getContentPane().add(new JLabel("Ex: 123.456.789.123"), IPExampleConstraints);
			
			frame.getContentPane().add(new JPanel(), normal);
			frame.getContentPane().add(new JPanel(), weightOne);
			frame.getContentPane().add(OKButton, confirmButtons);
			frame.getContentPane().add(CancelButton, confirmButtons);
			
			frame.pack();
		}
	}
	
	public void setVisible(boolean newValue) {frame.setVisible(newValue);}
	public void dispose() {frame.dispose();}
	
	/**
	 * Upon a call to actionPerformed, checks component properties,
	 * sets the Facade's properties appropriately,
	 * then opens the SecondScreen
	 */
	
	private class ContinueToSecondScreenActionListener implements ActionListener {
		public void actionPerformed( ActionEvent e ){
			
			final int gameType = gameTypeSelection.getGameMode();
			
			
			final URL ipAddr = (IPField.isEnabled() ? (URL) IPField.getValue() : null);
			
			//hide the GUI.Firstscreen, make a GUI.Secondscreen and show it
			Firstscreen.this.setVisible(false);
			new Secondscreen( Firstscreen.this, gameType, ipAddr ).setVisible(true);
			
		}//end of actionPerformed
	}
	
	/**
	 * Upon a call to propertyChange, sets the enabled property depending
	 * on the event's NewValue
	 */
	private class IPFieldSetEnabled implements java.beans.PropertyChangeListener {
		
		public void propertyChange(java.beans.PropertyChangeEvent e) {
			if (GameTypeSelectionPanel.GAME_MODE_PROERTY_NAME.equals(e.getPropertyName())) {
				IPField.setEnabled(e.getNewValue().equals(Facade.CLIENTGAME));
			}
		}
	}
	
	/**
	 * A format that creates http URLs from a hostname.
	 */
	private class UrlFormat extends java.text.Format {
		private static final long serialVersionUID = 2014517376614772066L;

		public StringBuffer format(Object obj, StringBuffer toAppendTo, java.text.FieldPosition pos) {
			URL obj2 = (URL) obj;
			toAppendTo.append(obj2.getHost());
			return toAppendTo;
		}
		
		public URL parseObject(String source, java.text.ParsePosition pos) {
			try {
				URL retVal = new URL("http", source.substring(pos.getIndex()), "/");
				pos.setIndex(source.length());
				return retVal;
			} catch (MalformedURLException e) {
				pos.setErrorIndex(pos.getIndex());
				return null;
			}
		}
	}

}
