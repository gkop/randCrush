/* gdk1@pitt.edu - CS 1538 project 4 */

import java.io.PrintWriter;

public class Autocorrelation implements Test {
	
	private String name = "Autocorrelation (alpha=0.10, M>35, must pass 90% of sub-tests) ";
	
	public Autocorrelation() {
		
	}

	public boolean run(Generator g, PrintWriter bw) {
		
		double upper = 1.64;	// from A.3 in text
		double lower = -1.64;   // from A.3 in text
	
		int N = 2000;
		int numMisses = 0;
		int count = 0;
		double[] values = new double[N];
		
		/* fill our values array */
		for (int i = 0; i < 2000; i++) {
			values[i] = g.nextDouble();
		}
		
		/* subtest every gap from 1 to 50 */
		for (int l = 1; l <= 50; l++) {
			
			/* subtest every index from 1 to N - l */
			for (int i = 1; i < N - l; i++) {
				double sum = 0;
				
				/* obtain M by inverting formula from pg. 292 */
				int M = (int)Math.floor((N-i)/(double)l - 1);
				
				/* take summation of gap values */
				for (int k = 1; k <= M; k++) {
					sum += values[i+k*l-1] * values[i+(k+1)*l-1];
				}
				
				/* math from pg. 292 */
				double hat = ((double)1/(M+1)) * (sum) - .25;
				double sigma = Math.sqrt(13*M +7)/(12*(M+1));
				double z0 = hat/sigma;
						
				/* subtest failed.  is M > 35 though? */
				if ((z0 < lower || z0 > upper) && M > 35) numMisses++;
				count++;							
			}	
		}
		
		/* get integer percentage */
		int pct = (int)Math.floor((1-(float)numMisses/count)*100);
		bw.printf("Passed %d%s of subtests; ", pct, "%");
		
		/* generator passes */
		if (pct >= 90) return true; 
		
		/* generator fails */
		else return false;
	}

	public String getName() {
		return name;
	}
	
}