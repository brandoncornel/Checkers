package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Allows a user to modify timer settings
 */
public class TimerDataPanel extends JPanel {
	private static final long serialVersionUID = -1955723149161984574L;
	
	private final BoundedRangeModel turnLengthRangeModel;
	private final BoundedRangeModel warnLengthRangeModel;
	
	public TimerDataPanel() {
		this.setLayout(new GridBagLayout());
		
		turnLengthRangeModel = new DefaultBoundedRangeModel(120, 0, 10, 300);
		warnLengthRangeModel = new DefaultBoundedRangeModel(120, 0, 10, 300);
		
		final JSlider turnLengthField = new JSlider(turnLengthRangeModel);
		final JSlider warnLengthField = new JSlider(warnLengthRangeModel);
		final JLabel turnLengthLabel = new JLabel();
		final JLabel warnLengthLabel = new JLabel();
		
		final ChangeListener turnChangeListener = new UpdateLabelWithValueChangeListener(turnLengthLabel, "Turn Length");
		final ChangeListener warnChangeListener = new UpdateLabelWithValueChangeListener(warnLengthLabel, "Warning Length");
		
		turnLengthField.addChangeListener(turnChangeListener);
		warnLengthField.addChangeListener(warnChangeListener);
		turnChangeListener.stateChanged(new ChangeEvent(turnLengthField));
		warnChangeListener.stateChanged(new ChangeEvent(warnLengthField));
		
		final GridBagConstraints sliderLabelConstraints = new GridBagConstraints();
		sliderLabelConstraints.fill = GridBagConstraints.BOTH;
		
		final GridBagConstraints sliderValueConstraints = new GridBagConstraints();
		sliderValueConstraints.gridwidth = GridBagConstraints.REMAINDER;
		
		this.add(turnLengthLabel, sliderLabelConstraints);
		this.add(turnLengthField, sliderValueConstraints);
		this.add(warnLengthLabel, sliderLabelConstraints);
		this.add(warnLengthField, sliderValueConstraints);
	}
	
	public int getTurnLength() {
		return (this.isEnabled() ? turnLengthRangeModel.getValue() : -1);
	}
	public int getWarningLength() {
		return (this.isEnabled() ? warnLengthRangeModel.getValue() : -1);
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
		 * @param e the change event
		 */
		public void stateChanged( ChangeEvent e ) {
			JSlider src = (JSlider) e.getSource();
			
			this.toUpdate.setText(this.caption + " ( "
					+ src.getValue() 
					+ " seconds )");
		}
	}
}
