/* gdk1@pitt.edu - CS 1538 project 4 */

import java.util.Date;

public class MiddleSquare implements Generator {
	
	private String name = "\"Middle square method\"";
	
	private int z;

	public MiddleSquare() {
		reset();				
	}
	
	public double nextDouble() {
		int z2 = z * z;						// square z
		String middle = Integer.toString(z2);
		
		while (middle.length() < 8) {
			middle = "0"+middle;			// append '0'
		}
		
		middle = middle.substring(2, 6);	// take middle four digits		
		z = Integer.parseInt(middle);		// new z
				
		return (double)Integer.parseInt(middle)/10000;
	}
	
	public String getName() {
		return name;
	}
	
	public void reset() {
		long seed = (new Date()).getTime();
		z = (int)(seed % 10000);
	}
}