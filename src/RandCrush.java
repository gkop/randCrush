/*
 * Gabe Kopley
 * gdk1@pitt.edu
 * CS 1538 Programming Assignment #2
 * 2009-11-12
 * No known issues. 
 */

import java.util.ArrayList;
import java.io.*;
 
public class RandCrush {

	public static void main(String[] args) throws FileNotFoundException {
		
		/* ArrayLists will control which generators are tested and how */
		ArrayList<Generator> theGenerators = new ArrayList<Generator>();		
		ArrayList<Test> theTests = new ArrayList<Test>();
		
		RandMenu menu = new RandMenu(theGenerators, theTests);
		menu.run();		/* get input from user */
		
		PrintWriter bw ;
		for (Generator G : theGenerators) {  /* test these Generators */
			bw = new PrintWriter(new FileOutputStream(G.getName()+"_results"),true);
			for (Test T : theTests) {		 /*		 with these Tests */
				int trials = 10;	
				bw.printf("%s\n", T.getName());
				
				for (int i = 0; i < trials; i++) {
					bw.printf("(%2d/%d) ", i+1, trials);
										
					if(T.run(G, bw))			 /* run test */
						bw.printf("%s PASSES\n", G.getName());
					else 
						bw.printf("%s FAILS\n", G.getName());
											
				}
				G.reset();
			}
		  
		}
	
	}

}