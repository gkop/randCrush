/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;

public class ChiSquare implements Test {
	
	public String name = "2-sided chi-square test (alpha=0.10)";
	
	public ChiSquare() {
		
	}
	
	public boolean run(Generator g, PrintWriter bw) {
	
		int n = 2000;
		int bins = 20;
		int[] binCounts = new int[bins];
		double statistic = 0;				// test statistic
		double e = n/bins;					// estimate
		double lower = 10.117;				// (from web site)
		double upper = 30.144;				// (from web site) 
		
		for (int i = 0; i < n; i++) {
			int bin = (int)Math.floor(g.nextDouble()*bins); // find bin
			binCounts[bin] = binCounts[bin] + 1;
		    //System.out.printf("bin %d++\n", bin);
		}
		
		for (int i = 0; i < bins; i++) {
			/* formula from handout, see pg 288 of old text */
			statistic += (Math.pow(binCounts[i]-e, 2)) / e;
		    //System.out.printf("observed: %d; expected: %f; chi-squared: %f\n", binCounts[i], e, statistic);
		}
		
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
