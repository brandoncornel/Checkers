package GUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

public class SetNamePanel extends JPanel {
	private static final long serialVersionUID = 8740527957526033268L;
	
	private final JTextField field;
	
	public SetNamePanel(int playerNumber) {
		super(new GridLayout(1,2));
		
		field = new JTextField();
		
		field.setText("Enter name");
		
		this.add(new JLabel("Model.Player " + playerNumber + ":"));
		this.add(field);
	}
	
	public void addDocumentListener(DocumentListener l) {
		field.getDocument().addDocumentListener(l);
	}
}
