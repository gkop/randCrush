/* gdk1@pitt.edu - CS 1538 project 4 */

import java.awt.Checkbox;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandMenu extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5117022208247584807L;
	private JPanel thePanel ;
	private JButton ready;
	private ArrayList<Checkbox> theCheckboxes;
	private ArrayList<Generator> theGenerators;
	private ArrayList<Test> theTests;
	
	public RandMenu(ArrayList<Generator> newGenerators, ArrayList<Test> newTests)  {
		
		
		thePanel = new JPanel() ;
		thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.PAGE_AXIS));
		
	    
		
		theGenerators = newGenerators;
		theTests = newTests;
		
		theCheckboxes = new ArrayList<Checkbox>();
		
		/* create a checkbox for each test and each generator */
		theCheckboxes.add(new Checkbox("Middle Square"));
		theCheckboxes.add(new Checkbox("java.util.Random"));
		theCheckboxes.add(new Checkbox("LCG #3"));
		theCheckboxes.add(new Checkbox("RANDU LCG"));
		theCheckboxes.add(new Checkbox("Fibonacci"));
		theCheckboxes.add(new Checkbox("random() additive"));
		theCheckboxes.add(new Checkbox("Chi-square"));
		theCheckboxes.add(new Checkbox("Kolmogorov-Smirnov"));
		theCheckboxes.add(new Checkbox("Runs Up Runs Down"));
		theCheckboxes.add(new Checkbox("Serial Pairs"));
		theCheckboxes.add(new Checkbox("Serial Triplets"));
		theCheckboxes.add(new Checkbox("Autocorrelation"));
		
		
		JLabel gensLabel = new JLabel("Generators");
		JLabel testsLabel = new JLabel("Tests");
		gensLabel.setAlignmentX((float)0.5);	// center text
		testsLabel.setAlignmentX((float)0.5);	// center text
		
		thePanel.add(gensLabel);
		
		/* add checkboxes to menu */
		for (int i = 0; i < theCheckboxes.size(); i++) {
			if (i == 6) thePanel.add(testsLabel);
			
			thePanel.add(theCheckboxes.get(i));
		}
		
		JButton allGenerators = new JButton("All generators");
		JButton allTests = new JButton("All tests");
		JButton clear = new JButton("Clear");
		ready = new JButton("GO");
		allGenerators.setAlignmentX((float)0.5);
		allTests.setAlignmentX((float)0.5);
		clear.setAlignmentX((float)0.5);
		ready.setAlignmentX((float)0.5);
		
		MenuListener listener = new MenuListener(ready, theCheckboxes);
		allGenerators.addActionListener(listener);
		allTests.addActionListener(listener);
		clear.addActionListener(listener);
		ready.addActionListener(listener);
				
		thePanel.add(allGenerators);
		thePanel.add(allTests);
		thePanel.add(clear);
		thePanel.add(ready);
	}
	
	public void run() {

		
		Container c = getContentPane() ;
		c.add(thePanel) ;
		pack() ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setVisible(true) ;
		
		/* center menu on screen */
		this.setLocationRelativeTo(null);
		
		/* wait for the user to press GO */
		while (ready.isEnabled()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/* iterate check boxes for checked ones */
		for (Checkbox box : theCheckboxes) {
			if (box.getState()) {
				/* Add the selected generators and tests */			
				if (box.getLabel().compareTo("Middle Square")==0) {
					theGenerators.add(new MiddleSquare());
				}
				else if (box.getLabel().compareTo("java.util.Random")==0) {
					theGenerators.add(new JavaRandom());	
				}
				else if (box.getLabel().compareTo("LCG #3")==0) {
					theGenerators.add(new LCG3());	
				}
				else if (box.getLabel().compareTo("RANDU LCG")==0) {
					theGenerators.add(new Randu());	
				}
				else if (box.getLabel().compareTo("Fibonacci")==0) {
					theGenerators.add(new Fibonacci());	
				}
				else if (box.getLabel().compareTo("random() additive")==0) {
					theGenerators.add(new Additive());	
				}
				else if (box.getLabel().compareTo("Chi-square")==0) {
					theTests.add(new ChiSquare());	
				}
				else if (box.getLabel().compareTo("Kolmogorov-Smirnov")==0) {
					theTests.add(new KolmogorovSmirnov());	
				}
				else if (box.getLabel().compareTo("Runs Up Runs Down")==0) {
					theTests.add(new RunsUpRunsDown());	
				}
				else if (box.getLabel().compareTo("Serial Pairs")==0) {
					theTests.add(new SerialPairs());	
				}
				else if (box.getLabel().compareTo("Serial Triplets")==0) {
					theTests.add(new SerialTriplets());	
				}
				else if (box.getLabel().compareTo("Autocorrelation")==0) {
					theTests.add(new Autocorrelation());	
				}
			}
		}
		
		this.dispose();		/* close menu */
		
	}
}
