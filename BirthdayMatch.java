/* This program was written
 * by Jessica Devlin
 * on 4/18/19
 * This is a program written to evaluate problems such as the birthday problem using prime factorizations.
 * A prime factorization class has been written which consists of each prime being stored as a key in a hashMap and the value of the key being the exponent of the prime. 
 * The PF class also implements operational methods to be called in the formulas written in class.
 */

import java.text.DecimalFormat;

public class BirthdayMatch {
	
	//366*365*364*...(366-N+1)
	public static double diff(PF m, PF n) {
		PF fact = new PF();
		int mint = (int)m.toDouble();     // convert m to an int for the factorial loop
		for (int i = 1; i < n.toDouble()+1; i++) {
			int mm = mint - i + 1;		// (M - N + 1) series
			PF toMult = new PF(mm);
			fact = fact.multiply(toMult); // multiply by the next lower value
		}
		int x = (int)n.toDouble();
		PF diff = fact.divide(m.exp(x));
		//System.out.println(m.exp(x).toDouble());
		//double diff = fact.toDouble()/(m.exp(x)).toDouble();	// (M) * (M-1) * (M-2) * .... / M ^ N
		//System.out.println(diff);
		return diff.toDouble();
	}
	
	public static double same(PF m, PF n) {		// implements the formula given in the assignment to solve these problems
		double result = 0;
		result = (1 - diff(m,n));
		result = result * 100;			// multiply by 100 to get a percent value
		return result;
	}
	
	public static void main (String[] args) {
		PF M = new PF(366);
		PF N = new PF(23);
		double prob = 0;
		prob = same(M, N);
		DecimalFormat df = new DecimalFormat("#.#");		// format according to assignment example
		System.out.println("There is a " + df.format(prob) + "% chance that at least 2 people have the same birthday.");
		
		PF t = new PF(35);
		PF g = new PF(7);
		t.divide(g);

		M = new PF(36600);
		N = new PF(100);
		prob = same(M, N);
		System.out.println("There is a " + df.format(prob) + "% chance that at least 2 people have the same birthday in a group of people with the same name.");
		
		M = new PF(10000);
		N = new PF(100);
		prob = same(M, N);
		System.out.println("There is a " + df.format(prob) + "% chance that in the same group, at least 2 people have the same last 4 digits of their SSN.");
	}
}
