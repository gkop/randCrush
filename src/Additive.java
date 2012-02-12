/* gdk1@pitt.edu - CS 1538 project 4 */
/* inspired by http://members.cox.net/srice1/random/additive.html */

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class Additive implements Generator {
	
	private String name = "random() additive";
	
	private int k;
	private long m;
	private long[] table;
	
	public Additive() {
		table = new long[31];
		m = (new BigInteger("2")).pow(32).longValue();
		reset();
	}
	
	public double nextDouble() {
		//System.out.printf("table[%d] = table[%d] + table[%d] : %d = %d + %d; ", k, k, (k + 29) % 32, table[k], table[k], table[(k + 29) % 32]);
		
    	/* Note that k is zero indexed, so we get use index 28 to get k-3 */
		table[k] = (table[k] + table[(k + 28) % 31]);
		
		//System.out.printf("%d; ", table[k]);
		
		/* We must ensure that our value is positive */
		double nextDouble = (double)(((table[k] >> 1) & Long.MAX_VALUE) % m) / m;
		//double nextDouble = (double)(table[k] % m) / m;
		
		//System.out.printf("%f\n", nextDouble);
		k = (k + 1) % 31;  		 /* increment / wrap */
		
		return nextDouble;
	}
	
	public void reset() {
		
		/* We will use Random() to seed the generator */
		Random R = new Random((new Date()).getTime());
		
		/* seed each member of the table			  */
		for (int i = 0; i < 31; i++) {
			table[i] = R.nextLong();
			//System.out.printf("%d\n", table[i]);
		}
		k = 0;
	}
	
	public String getName() {
		return name;
	}
}