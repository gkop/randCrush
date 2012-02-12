/* gdk1@pitt.edu - CS 1538 project 4 */

import java.math.BigInteger;
import java.util.Date;

public class Fibonacci implements Generator {
	
	private String name = "Fibonacci additive";
	
	private int a;
	private int c;
	
	private long x_previous;
	private long x;
	private long m;
		
	public Fibonacci() {
		a = 1194211693;
		c = 12345;
		m = (new BigInteger("2")).pow(32).longValue();
		reset();
	}
	
	public double nextDouble() {

		//System.out.printf("%d; ", x);
		double nextDouble = (double) x / m;
		
		long oldX = x;
		
		x = (x + x_previous) % m;
		//System.out.printf("nextDouble: %f\n", nextDouble);
		
		x_previous = oldX;
		
		return nextDouble;
	}
	
	public String getName() {
		return name;
	}
	
	public void reset() {
		x_previous = (new Date()).getTime();
		//System.out.printf("x = (%d*%d + %d) mod %d = ", x, a, c, m);
		x = (x_previous*a + c) % m;
		
	}
}