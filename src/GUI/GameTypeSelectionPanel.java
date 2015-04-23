package GUI;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import static System.Facade.CLIENTGAME;
import static System.Facade.LOCALGAME;
import static System.Facade.HOSTGAME;

/**
 * A user interface component that lets a user select a game type
 * 
 * The currently selected game type can be gotten from #getGameMode()
 * 
 * changes to the gameMode are reported through a PropertyChangeEvent,
 * using  GameTypeSelectionPanel.GAME_MODE_PROERTY_NAME as the property name
 */
public final class GameTypeSelectionPanel extends JPanel {
	public static final String GAME_MODE_PROERTY_NAME = "GUI.GameTypeSelectionPanel.game_mode";
	private int gameMode = LOCALGAME;
	
	public GameTypeSelectionPanel() {
		super(new GridLayout(3,1));
		final ButtonGroup buttonGroup = new ButtonGroup();
		
    	final JRadioButton localGameButton = new JRadioButton("Local game");
    	final JRadioButton hostGameButton = new JRadioButton("Host game");
    	final JRadioButton joinGameButton = new JRadioButton("Join game");
		
    	buttonGroup.add(localGameButton);
    	buttonGroup.add(hostGameButton);
    	buttonGroup.add(joinGameButton);
    	
    	localGameButton.addActionListener(new SetGameModeActionListener(LOCALGAME));
    	hostGameButton.addActionListener(new SetGameModeActionListener(HOSTGAME));
    	joinGameButton.addActionListener(new SetGameModeActionListener(CLIENTGAME));
    	
    	localGameButton.setSelected(true);
    	
    	this.add(localGameButton);
    	this.add(hostGameButton);
    	this.add(joinGameButton);
	}
	
	public final int getGameMode() {
		return this.gameMode;
	}
	
	
	/**
	 * Upon an actionPerformed, this will set the outer class's gameMode property,
	 * including firing off an event to notify listeners of the change.
	 */
	private class SetGameModeActionListener implements ActionListener {
		private final int value;
		
		public SetGameModeActionListener(int value) {
			this.value = value;
		}
		
		public void actionPerformed(ActionEvent e) {
			GameTypeSelectionPanel.this.firePropertyChange(
					GameTypeSelectionPanel.GAME_MODE_PROERTY_NAME,
					GameTypeSelectionPanel.this.gameMode,
					value
			);
			GameTypeSelectionPanel.this.gameMode = value;
		}
	}
}
