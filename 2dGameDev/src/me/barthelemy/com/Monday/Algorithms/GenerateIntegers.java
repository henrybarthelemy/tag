package me.barthelemy.com.Monday.Algorithms;

public class GenerateIntegers {
	
	public static void main(String[] args){
		// sets returnedString to the output from the algrithm
		String returnedString = getIntegers(4);
		String returnedEven = getEvenIntegers(4);
		String returnedOdd = getOddIntegers(4);
		String returnedChallenge = getOddOrEvenIntegers(10, false);
		System.out.println("RETURN: " + returnedString);
		System.out.println("RETURN EVEN: " + returnedEven);
		System.out.println("RETURN ODD: " + returnedOdd);
		System.out.println("EXTRA CHALLENGE: " + returnedChallenge);
	}

	private static String getIntegers(int i) {
		// Returns a string comprised with integers of 1 until i
		String intString = "";
		for(int j = 1; j <= i; j++){
			intString = intString + j + " ";
		}
		return intString;
	}
	
	/*Daily Algorithm Challenge
	 * Try to create a function that return an "i" amount of odd numbers
	 * (extra challenge make one for even numbers then one with a boolean input for dual functionality)
	 * 
	 * 
	 * 
	 * 
	 * Answers below
	 */
	
	private static String getEvenIntegers(int i) {
		// Returns a string comprised with integers of 1 until i
		String intString = "";
		for(int j = 1; j <= i; j++){
			//Starting at 0 or 2 is personal preference
			int even = (2*j);
			intString = intString + even + " ";
		}
		return intString;
	}
	
	private static String getOddIntegers(int i) {
		// Returns a string comprised with and "i" amount of even integers (starting at 2)
		String intString = "";
		for(int j = 1; j <= i; j++){
			int odd = (2*j) - 1;
			intString = intString + odd + " ";
		}
		return intString;
	}
	
	private static String getOddOrEvenIntegers(int i, boolean e) {
		// Returns a string comprised with integers of 1 until i (if false, then odd, else even)
		String intString = "";
		for(int j = 1; j <= i; j++){
			int k = (2*j);
			if(!e) {
				k -= 1;
			}
			intString = intString + k + " ";
		}
		return intString;
	}
	
	
}
