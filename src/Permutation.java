import java.io.PrintWriter;

public class Permutation implements Test {

	private String name = "Permutation test (alpha=0.10)";
	
	public Permutation() {
		
	}
	

	
	public boolean run(Generator g, PrintWriter bw) {
		
		int N = 2000;
		double[] values = new double[N+1];
		int t = 20;
		int n = N / t;
		double statistic = 0;
		double upper = 123.225 ;
		double lower = 77.046;
		
		long k = 1;
		
		for (int i = 1; i <= t; i++) {
			k = k * i;
		}
		
		double e = (double)1 / k;
		
		System.out.printf("e: %.20f\n", e);
				
		for (int i = 1; i <= N; i++) {
			values[i] = g.nextDouble();
			//System.out.printf("%d: %f\n", i, values[i]);
		}
				
		for (int j = 0; j < n; j++) {
			
			System.out.printf("subset %d ", j);
			
			int s = 0;
			int r = t;
			double f = 0;
			
			while (r > 1) {
				
				double U_s = 0;
				
				for (int i = 1; i <= r; i++) {
					double U = values[j*t+i];
					if (U > U_s) {
						U_s = U;
						s = j*r+i;
					}
					
				}
				System.out.printf("U_s: %f\n", U_s);
				values[s] = values[j*r+r];
				values[j*r+r] = U_s;
				f = r * f + s - 1;
				System.out.printf("Swapped %d and %d; r: %d; f: %f: 1/f: %.20f ", s, j*r+r, r, f, 1/f);
				r = r-1;
				
			}
			statistic += (Math.pow((1/f)-e, 2)) / e;
			System.out.printf("statistic: %.20f\n", statistic);
		}
		
		
		
		
		System.out.printf("Upper: %f; Lower: %f; Statistic: %.20f", upper, lower, statistic);
		
		
		
		
	
		return false;
	}
	
	public String getName() {
		return name;
	}

}
