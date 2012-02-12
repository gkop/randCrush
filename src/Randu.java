/* gdk1@pitt.edu - CS 1538 project 4 */

import java.math.BigInteger;


public class Randu implements Generator {

	private String name = "RANDU LCG";
	
	private int a;
	private int c;
	
	private long x;
	private long m;

	public Randu() {
		a = 65539;
		c = 0;
		m = (new BigInteger("2")).pow(31).longValue();
		reset();
	}
	
	public double nextDouble() {
		//System.out.printf("x = (%d*%d + %d) mod %d = ", x, a, c, m);
		x = (x*a + c) % m;
		//System.out.printf("%d; ", x);
		double nextDouble = (double)x / m;
		//System.out.printf("nextDouble: %f\n", nextDouble);
		
		
		return nextDouble;
	}
	
	public String getName() {
		return name;
	}
	
	public void reset() {
		x = 123456789;
	}
}