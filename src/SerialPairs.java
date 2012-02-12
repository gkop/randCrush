/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;

public class SerialPairs implements Test {
	
	private String name = "Serial pairs test (alpha=0.10)";
	
	public SerialPairs() {
		
	}
	
	public boolean run(Generator g, PrintWriter bw) {
		CriticalCalculator calc = new CriticalCalculator();  // to find CV's
		int pairs = 400;
		int side = 20;
		int[][] binCounts = new int[side][side];
		
		double statistic = 0;								 // test statistic
		double upper = calc.critical(side*side, 0.05);  	 // upper CV
		double lower = calc.critical(side*side, 0.95);  	 // lower CV
		int e = pairs / (side*side);						 // estimate
		
		for (int i = 0; i < pairs; i++) {
			int X = (int)Math.floor(g.nextDouble()*side);    // find X bin
			int Y = (int)Math.floor(g.nextDouble()*side);    // find Y bin
			//System.out.printf("bin [%d][%d]++\n", binA, binB);
			binCounts[X][Y] = binCounts[X][Y] + 1;	
		}
		
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				/* formula from handout, see pg 288 of old text */
				statistic += (Math.pow(binCounts[i][j]-e, 2)) / e;
			    //System.out.printf("observed: %d; expected: %f; chi-squared: %f\n", binCounts[i], e, statistic);	
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