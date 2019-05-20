import java.util.HashMap;

public class PF {		// class to construct prime factorization and 
	
	  public HashMap<Integer, Integer> factors;		// instantiate hashMap
	  
	  public PF() { // creates an empty prime factorization which represents 1
		  factors = new HashMap<Integer, Integer>();
	  }
	  public PF(int n) { // creates the prime factorization of n
		  factors = new HashMap<Integer, Integer>();
		  //System.out.println("The prime factors of " + n + " are: ");
		  int i = 2;		// first number to try to divide n by
		  int count = 0;
		  int init = n;
		  while(i <= init/2) {		// factors will be stored until end of divison, even if n divides evenly
			  if (n % i == 0) {
				  count++;		// inc exponent of factor
				  n = n / i; // update value of n to continue factoring
			  }
			  else if (n % i != 0 && count > 0) {		// if streak is broken
				  //System.out.println(i + " ^ " + count);
				  factors.put(i, count);
				  count = 0;
				  i++; // if n is no longer divisible by i, increment to next factor
			  }
			  else {	// if no streak, do not store, just increment factor
				  i++;
			  }
		  }
		  if(factors.size() == 0) {				// if no factors have been found
			  //System.out.println(n + " ^ 1");	
			  factors.put(init, 1);				// number is only divisible by itself ^ 1
		  }
	  }
	  
	  // multiplies pf by combining the primes of both and modifying those that overlap by adding the exponents
	  public PF multiply(PF a) { // returns this * a.  Should not change this or a.
		  PF product = new PF();	// set result equal to pf we are performing the operation on
		  int multiplier = 0;
		  int result = 0;
		  int adding = 0;
		  for (int key : a.factors.keySet()) {		// add each key in the parameter pf to the empty pf
			  product.factors.put(key, a.factors.get(key));
		  }
		  for (int key : this.factors.keySet()) {			// for each key in multplier set
		  product.factors.put(key, this.factors.get(key));		// add all values from base to new PF
	      //System.out.println("The key is " + key);
			  if (a.factors.containsKey(key)) {	// if other multiplier also contains the same prime
				  //System.out.println("The key " + key + " was found in both.");
				  multiplier = a.factors.get(key);		// multiplier = exponent to which prime is raised
				  adding = this.factors.get(key);
				  result = adding + multiplier;			// add exponents of primes that are in both pfs
				  product.factors.put(key, result);		// replace edited key  
			  }
		  }
		  //System.out.println(product.toDouble());
		  return product;
	  }
	  
	  // divides by converting both to a double, dividing, and refactoring
	  // I had some ideas of how to mirror the multiply method without converting to a double, but nothing seemed to work right
	  public PF divide(PF a) { // returns this / a. Should not change this or a.
		  PF quotient = new PF();	// set result equal to pf we are performing the operation on
		  int divisor = 0;
		  int result = 0;
		  int dividend = 0;
		  for (int key : a.factors.keySet()) {		// add each key in the parameter pf to the empty pf
			  quotient.factors.put(key, a.factors.get(key));
		  }
		  for (int key : this.factors.keySet()) {			// for each key in multplier set
		  quotient.factors.put(key, this.factors.get(key));		// add all values from base to new PF
	      //System.out.println("The key is " + key);
			  if (a.factors.containsKey(key)) {	// if other multiplier also contains the same prime
				  //System.out.println("The key " + key + " was found in both.");
				  divisor = a.factors.get(key);		// multiplier = exponent to which prime is raised
				  dividend = this.factors.get(key);
				  result = dividend - divisor;			// add exponents of primes that are in both pfs
				  quotient.factors.put(key, result);		// replace edited key  
			  }
		  }
		  //System.out.println(quotient.toDouble());
		  return quotient;
	  }
	  
	  public PF exp(int x) { // return this ^ x. Should not change this.    
		  PF exp = new PF ();	// empty pf to return
		  for (int i = 0; i < x; i++) {		// for loop using the degree of the exponent
			  exp = exp.multiply(this);		// multiply this by itself x number of times
		  }
		  return exp;
	  }
	  
	  public double toDouble() { // returns this as a double.
		  int exponent = 0;
		  double product = 0;		// inital values
		  double result = 1;
		  for (int key : this.factors.keySet()) {		// for each prime in factors hashmap
			 exponent = this.factors.get(key);		// exponent of prime equals value of key
			 product = Math.pow(key, exponent);
			 //System.out.println("The factor is " + key + " raised to the " + exponent + " = " + product);
			 result *= product;
		  }
		  //System.out.println(result);
		  return result;
	  }
}
	  
