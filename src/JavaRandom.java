import java.util.Date;


public class JavaRandom extends java.util.Random implements Generator {

	public String name = "java.util.Random";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JavaRandom() {
		reset();
	}
		
	public String getName() {
		return name;
	}
	
	public void reset() {
		long seed = (new Date()).getTime();
		this.setSeed(seed);
	}
	

}
