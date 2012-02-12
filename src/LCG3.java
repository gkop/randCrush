import java.math.BigInteger;


public class LCG3 implements Generator {
	
	private String name = "LCG #3";
	
	private int a;
	private int c;
	
	private long x;
	private long m;
	
	
		
	public LCG3() {
		a = (new BigInteger("2")).pow(18).intValue() + 1;
		c = 1;
		m = (new BigInteger("2")).pow(35).longValue();
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
		x = 314159265;
	}

}
