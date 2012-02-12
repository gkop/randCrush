/* gdk1@pitt.edu - CS 1538 project 4 */

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MenuListener implements ActionListener {

	private JButton ready;
	private ArrayList<Checkbox> theCheckboxes;
	
	public MenuListener(JButton newReady, ArrayList<Checkbox> newCheckboxes) {
		ready = newReady;
		theCheckboxes = newCheckboxes;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().toString().contains("generators")) {
			for (int i=0; i < 6; i++) {
				theCheckboxes.get(i).setState(true);
			}
		}
		else if (e.getSource().toString().contains("tests")) {
			for (int i=6; i < theCheckboxes.size(); i++) {
				theCheckboxes.get(i).setState(true);
			}
		}
		else if (e.getSource().toString().contains("Clear")){
			for (int i=0; i < theCheckboxes.size(); i++) {
				theCheckboxes.get(i).setState(false);
			}
		}
		else {
			boolean oneGenerator = false;
			boolean oneTest = false;
			for (int i=0; i < 6; i++) {
				if (theCheckboxes.get(i).getState()) {
					oneGenerator = true;
				}
			}
			for (int i=6; i < theCheckboxes.size(); i++) {
				if (theCheckboxes.get(i).getState()) {
					oneTest = true;
				}
			}
			
			if (!oneGenerator) {
				JOptionPane.showMessageDialog(new JFrame(), "Please select at least one generator", "Error.", 1);
			}
			else if (!oneTest) {
				JOptionPane.showMessageDialog(new JFrame(), "Please select at least one test", "Error.", 1);
			}
			else {
				ready.setEnabled(false);
			}
		}
	}
}
