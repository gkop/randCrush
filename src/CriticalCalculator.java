// CS 1538 Fall 2009
// This program compares the predefined nextInt(n) method with a programmer-
// derived value of nextInt() % n and with a simple linear congruential generator
// provided in a CS 0445 text.  The test used is the Chi-Square test for uniformity
// (note that we are not testing independence here, but we should to determine if
// the generator is useable or not -- see Section 7.4 for other tests).

// The generator from the CS 0445 text is included for a couple reasons:
// 1) It has a smaller range than the standard Java generators.  This lowers the
//    density of the generator and can lead to poor results in some situations
//    (see Rand1.java for more details).
// 2) It has a variation that uses the lower-order bits to demonstrate their
//    decidedly non-random behavior (see Rand1.java for more details)

// An exact even distribution is also tested to show that a distribution can be
// "too good" -- in this case having the exact expected value for each number shows
// not random behavior but rather a lack thereof.  In this case, we would look at
// the "left side" of the Chi-Square distribution rather than the right tail.  See
// the "Critical Values of the Chi-Square Distribution" handout for more details.
// Look at the R4 generator below in a few of the examples to see a fit that is
// likely "too good" to be random

/* 
 * modified 11/09 by Gabe Kopley - removed everything but a single method.
 *								 - renamed from RandTest1 to CriticalCalculator
 */

public class CriticalCalculator
{
	public CriticalCalculator() {
	
	}

	// Below is a formula for estimating the critical value of Chi-Square when the
	// degrees of freedom is large.  Note that most tables in books (and online) don't
	// go above 100 so this formula can come in handy for our random number tests.
	// The formula is taken from Simulation Modeling and Analysis Third Edition by
	// Law and Kelton (McGraw-Hill).  If you compare the values (even for small k) to
	// those in the printed tables you will see that this formula is pretty good.

	// Arguments are k, the number of subintervals and alpha, the desired significance
	// level
	public double critical(int k, double alpha)
	{
		int kMinus1 = k-1;
		double oneMinusAlpha = 1 - alpha;
		double temp1 = (double)2/(9 * kMinus1);
		double temp2 = Math.sqrt(temp1);
		// To estimate our critical value we need to use the inverse normal function.
		// This takes a probability (in the CDF for N(0,1)) and returns the z value
		// (i.e. the number of standard deviations that produces that probability).  I
		// got the code from the Web (see CDF_Normal.java for more details).
		double zOneMinusAlpha = CDF_normal.xnormi(oneMinusAlpha);
		temp2 = temp2 * zOneMinusAlpha;
		temp1 = 1 - temp1 + temp2;
		temp1 = Math.pow(temp1,3);
		temp1 = temp1 * kMinus1;
		return temp1;
	}

}