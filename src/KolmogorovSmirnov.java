/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;
import java.util.Arrays;

public class KolmogorovSmirnov implements Test {
	
	public String name = "Kolmogorov-Smirnov test (alpha=0.10)";
	
	public KolmogorovSmirnov() {
		
	}
	
	public boolean run(Generator g, PrintWriter bw) {
		/* this is all straight from class handouts */
		
		int n = 2000;
		double[] S = new double[n+1];
		
		for (int i = 0; i < n; i++) {
			S[i] = g.nextDouble();						
		}
		
		Arrays.sort(S);
		
		double critical = 1.22 / Math.sqrt(n);
		
		//System.out.printf("Critical value: %f; ", critical);
		
		double maxDplus = 0;
		double maxDminus = 0;
		
		for (int i = 1; i < S.length; i++) {
			if ((double)i/S.length - S[i] > maxDplus) {
				maxDplus = (double)i/S.length - S[i];
			}
			if (S[i] - (double)(i-1)/S.length > maxDminus) {
				maxDminus = S[i] - (double)(i-1)/S.length;
			}
		}
		
		double D = Math.max(maxDplus, maxDminus);
		//System.out.printf("D: %f\n", D);
		bw.printf("D: %f; critical value: %f; ", D, critical);
		
		if (D < critical) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}

}
