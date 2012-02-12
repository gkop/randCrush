/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;

public class RunsUpRunsDown implements Test {
	
	private String name = "Runs Up, Runs Down test (alpha=0.10)";
	
	public RunsUpRunsDown() {
		
	}

	public boolean run(Generator g, PrintWriter bw) {
		int n = 2000;
		int runsUp = 0;
		int runsDown = 0;
		int state = 0; 			// 1 is down, 2 is up
		double upper = 1.64;	// from A.3 in text
		double lower = -1.64;   // from A.3 in text
		
		
		double lastDouble = g.nextDouble();
		//System.out.printf("%f ", lastDouble);
		
		for (int i = 1; i < n; i++) {
			double newDouble = g.nextDouble();
			//System.out.printf("%f ", newDouble);
			
			if (newDouble < lastDouble && state != 1) {
				runsDown++;
				state = 1;
				//System.out.printf("- ");
			}
			else if (newDouble > lastDouble && state != 2) {
				runsUp++;
				state = 2;
				//System.out.printf("+ ");
			}
			lastDouble = newDouble;
		
		}
		
		int runsTotal = runsUp + runsDown;
		
		/* see handout from old 3rd ed text */
		double mean = (double)(2 * n - 1) / 3;
		double variance = (double)(16 * n - 29) / 90;
		
		double statistic = (runsTotal - mean) / Math.sqrt(variance);
		
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
