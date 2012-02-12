/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;

public class SerialTriplets implements Test {
	
	private String name = "Serial triplets test (alpha=0.10)";
	
	public SerialTriplets() {
		
	}
	
	public boolean run(Generator g, PrintWriter bw) {
		CriticalCalculator calc = new CriticalCalculator();	// to find CV's
		int triplets = 80000;
		int side = 20;
		int[][][] binCounts = new int[side][side][side];
		double statistic = 0;								// test statistic
		double upper = calc.critical(side*side*side, 0.05); // upper CV
		double lower = calc.critical(side*side*side, 0.95); // lower CV
		int e = triplets / (side*side*side);				// estimate
		
		for (int i = 0; i < triplets; i++) {
			int binA = (int)Math.floor(g.nextDouble()*side);// find X bin
			int binB = (int)Math.floor(g.nextDouble()*side);// find Y bin
			int binC = (int)Math.floor(g.nextDouble()*side);// find Z bin
			//System.out.printf("bin [%d][%d]++\n", binA, binB);
			binCounts[binA][binB][binC] = binCounts[binA][binB][binC] + 1;			
		}
		
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				for (int k = 0; k < side; k++) {
					/* formula from handout, see pg 288 of old text */
					statistic += (Math.pow(binCounts[i][j][k]-e, 2)) / e;
			    	//System.out.printf("observed: %d; expected: %f; chi-squared: %f\n", binCounts[i], e, statistic);
				}
			}
		}
		
		//System.out.printf("upper tail: %f; ", upper);
		//System.out.printf("lower tail: %f; ", lower);
		//System.out.printf("test statistic: %f\n", statistic);
		
		bw.printf("upper tail: %2.2f; ", upper);
		bw.printf("lower tail: %2.2f; ", lower);
		bw.printf("test statistic: %6.2f; ", statistic);
		
		
		/* generator passes */
		if (lower < statistic && statistic < upper) return true;
		
		/* generator fails */
		else return false;
	}
	
	public String getName() {
		return name;
	}
}