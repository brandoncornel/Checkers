package GUI;

/**
 * Upon a call to actionPerformed or windowClosing, exits the program 
 */

public final class ExitProgramListener extends java.awt.event.WindowAdapter implements java.awt.event.ActionListener {
	public void windowClosing(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}
	public void actionPerformed(java.awt.event.ActionEvent e) {
		System.exit(0);
	}
}

